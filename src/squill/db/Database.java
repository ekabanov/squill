package squill.db;

import static java.lang.String.format;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import squill.callback.ResultCallback;
import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.query.select.SelectExpression;
import squill.query.select.SimpleSelectExpression;
import squill.query.QueryContext;
import squill.query.QueryContextImpl;
import static squill.query.QueryContext.QueryType.UNKNOWN;
import squill.tuple.Tuple;
import squill.util.ExceptionUtil;



public class Database {
  protected static final Log log = LogFactory.getLog(Database.class);
  private final DataSource dataSource;
    // todo remove and add as param to execute !!!
    private Map<String, Object> ctx = new HashMap<String, Object>();

  public Database(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public DataSource getDataSource() {
    return dataSource;
  }

  /**
   * Only a single tuple is expected at most. If database returns more than one
   * row,
   *
   * @param query      SQL query
   * @param args       Arguments to replace question marks in query
   * @param selectList list of select expressions
   * @param tupleClass A tuple to fit data into
   * @return Tuple with data or null if no data
   * @see {@link TooManyResultsException}
   */
  public  Tuple queryTuple(final String query, final List<?> args,
      final List<SelectExpression<?>> selectList, final Class<? extends Tuple> tupleClass) {
    return execute(query, args, new StatementCallback<Tuple>() {
      public Tuple execute(final PreparedStatement stmt) throws Exception {
        final ResultSet rset = stmt.executeQuery();
        final List<Tuple> result = readValues(selectList, tupleClass, rset, true);
        return result.isEmpty() ? null : result.get(0);
      }
    });
  }

  public  List queryList(final String query, final List<?> args,
      final List<SelectExpression<?>> selectList, final Class tupleClass) {
    return execute(query, args, new StatementCallback<List<Tuple>>() {
      public List<Tuple> execute(final PreparedStatement stmt) throws Exception {
        final ResultSet rset = stmt.executeQuery();
        return readValues(selectList, (Class<?>) tupleClass, rset, false);
      }
    });
  }
  public  <R> List<R> queryCallback(final String query, final List<?> args,
      final List<SelectExpression<?>> selectList, final Class<? extends Tuple> tupleClass,
      final ResultCallback<Tuple, R> callback) {
    return execute(query, args, new StatementCallback<List<R>>() {
      @SuppressWarnings({"unchecked"})
      public List<R> execute(final PreparedStatement stmt) throws Exception {
        final ResultSet rset = stmt.executeQuery();
        ResultCallback.Status fetchStatus = new ResultSetFetchStatus(rset);
        final Constructor tupleConstructor = getConstructor(tupleClass, selectList.size());
        final List<R> resultList = new ArrayList<R>();
        while (rset.next() && !fetchStatus.isAborted()) {
          final Tuple tuple = createTuple(rset, tupleConstructor, selectList);
          resultList.add(callback.handle(tuple,fetchStatus));
        }

        return resultList;
      }
    });
  }

  private  Tuple createTuple(final ResultSet rset, final Constructor tupleConstructor, final List<SelectExpression<?>> selectList) throws SQLException, InstantiationException, IllegalAccessException, InvocationTargetException {
    final Object[] values = extractResultValues(selectList, rset);
    return newTuple(tupleConstructor, values);
  }


    /**
   * Execute a query that does not return any data (insert,update,delete)
     *
     * @param query SQL query to execute
     * @param args
     * @param queryContext
     */
    public int executeCUD(final String query, final List<?> args, QueryContext queryContext) {
        return execute(query, args, new StatementCallback<Integer>() {
            public Integer execute(final PreparedStatement stmt) throws Exception {
                return stmt.executeUpdate();
            }
        },queryContext);
    }

    public <V> V  executeUncheckedQuery(final String query, Class<V> expectedType) {
  	
  	if (expectedType == null) {
  		throw new IllegalArgumentException("type to expect is missing");
  	}
  	
  	if (expectedType.isInstance(Tuple.class)) {
  		throw new IllegalArgumentException("You cannot expect a tuple - only a real type");
  	}
  	
  	Connection con = null;
  	PreparedStatement stmt = null;
  	try {
  		con = dataSource.getConnection();
  		stmt = con.prepareStatement(query);
  		DataTypeUtil.setStatementParams(stmt, null, ctx);
  		
  		ResultSet rset = stmt.executeQuery();

  		if (rset.next()) {
           Object value = rset.getObject(1);
           return DataTypeUtil.convertIntoExpectedType(value, expectedType);
  		}
    	throw new RuntimeException("database returned no results for query "+query);
  	} catch (Exception e) {
  		throw ExceptionUtil.rethrow(e);
  	} finally {
  		closeIt(con, stmt, null);
  	}
  }

  public long count(final String sql, final List<?> args) {
    return execute(sql, args, new StatementCallback<Long>() {
        public Long execute(PreparedStatement stmt) throws Exception {
            final ResultSet rs = stmt.executeQuery();
            if (!rs.next()) throw new SQLException("Expected one row, got none ");
            return rs.getLong(1);
        }
    });
  }


    public  <T> T execute(final String query, final List<?> args, final StatementCallback<T> statementCallback) {
        return execute(query, args, statementCallback, new QueryContextImpl(UNKNOWN));
    }

    public  <T> T execute(final String query, final List<?> args, final StatementCallback<T> statementCallback, final QueryContext queryContext) {
    if (log.isInfoEnabled()) log.info(format("SQL: %s ARGS: %s",query,args));
    long time = 0;
    if (log.isTraceEnabled()) time=System.nanoTime();
    Connection con = null;
    try {
        con = dataSource.getConnection();
        DatabaseDialect dialect=DatabaseDialect.from(con);
        return dialect.execute(con, query, args, ctx, queryContext, statementCallback);
    } catch (Exception e) {
      throw ExceptionUtil.rethrow(e);
    } finally {
      closeIt(con, null, null);
      if (log.isTraceEnabled()) {
        time = (System.nanoTime() - time)/(1000*1000);
        log.trace("took "+time+" ms");
      }
    }
  }

  @SuppressWarnings("unchecked")
  private  List<Tuple> readValues(final List<SelectExpression<?>> selectList, final Class tupleClass, final ResultSet rset, final boolean expectOne) throws SQLException,
  IllegalAccessException, InvocationTargetException, InstantiationException {

    final Constructor<Tuple> tupleConstructor = getConstructor(tupleClass, selectList.size());
    final List<Tuple> resultList = new ArrayList<Tuple>();
    for (int rowNum = 0; rset.next(); rowNum++) {
      // user wanted a tuple, but database is about to return second row...
      if (expectOne && rowNum > 0) {
        throw new TooManyResultsException();
      }
      final Tuple tuple = createTuple(rset,tupleConstructor,selectList);
      resultList.add(tuple);
    }

    return resultList;
  }

  public  <P extends Tuple> P newTuple(final Constructor<P> tupleConstructor, final Object[] values) throws InstantiationException, IllegalAccessException, InvocationTargetException {
    return tupleConstructor.newInstance(values);
  }

  public  Object[] extractResultValues(final List<SelectExpression<?>> selectList, final ResultSet rset) throws SQLException {
    final Collection<Object> result = new ArrayList<Object>(selectList.size());

    for (final SelectExpression<?> expr : selectList) {
        final Object value;
        if (expr instanceof SimpleSelectExpression) {
          value = DataTypeUtil.readFromResultSet(rset, ((SimpleSelectExpression) expr).getAlias(), expr.getTableType());
        } else if (expr instanceof ReadableTable) { 
          value = DataTypeUtil.readFromResultSet(rset, ((ReadableTable)expr).getColumns(), expr.getTableType());
        } else /*if (expr instanceof Column)*/ {
          value = DataTypeUtil.readFromResultSet(rset, ((Column) expr).getAlias(), expr.getTableType());
        }
        result.add(value);
    }
    return result.toArray();
  }

  @SuppressWarnings({"unchecked"})
  public  <P extends Tuple> Constructor<P> getConstructor(final Class<P> tupleClass, final int argCount) {
    // Get all the constructors of corresponding tuple
    final Constructor[] cts = tupleClass.getDeclaredConstructors();

    // VALIDATION - not completely necessary as it validates Tuples (TypeSafeSQL code)

    // All tuples may have exactly one constructor
    if (cts.length != 1) {
      throw new RuntimeException("A tuple may have only one constructor, not "+cts.length);
    }

    if (argCount != cts[0].getGenericParameterTypes().length) {
      throw new RuntimeException("Constructor of tuple has wrong number of arguments!");
    }

    return cts[0];
  }

  public static void closeIt(final Connection con, final Statement stmt, ResultSet rs) {
    if (rs==null && stmt!=null) try {
      rs=stmt.getResultSet();
    } catch (SQLException e) {
      if (log.isErrorEnabled()) log.error("Error accessing ResultSet", e);
    }
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException e) {
        if (log.isErrorEnabled()) log.error("Error closing ResultSet", e);
      }
    }
    if (stmt != null) {
      try {
        stmt.close();
      } catch (SQLException e) {
        if (log.isErrorEnabled()) log.error("Error closing Statement", e);
      }
    }
    if (con != null) {
      try {
        con.close();
      } catch (SQLException e) {
        if (log.isErrorEnabled()) log.error("Error closing Connection", e);
      }
    }
  }

  public void addParam(String name, Object value) {
    ctx.put(name,value);
  }
  private static class ResultSetFetchStatus implements ResultCallback.Status {
    public boolean abort;
    private final ResultSet rset;

    public ResultSetFetchStatus(final ResultSet rset) {
      this.rset = rset;
    }

    public int getRow() {
      try {
        return rset.getRow() - 1;
      } catch (SQLException e) {
        throw new RuntimeException("Error accessing row count", e);
      }
    }

    public void abort() {
      this.abort=true;
    }

    public boolean isAborted() {
      return abort;
    }
  }

}
