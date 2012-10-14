package squill.db;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.lang.reflect.Method;

import squill.query.Declaration;
import squill.query.select.Column;
import squill.util.BeanUtil;
import squill.util.ReflectUtils;

import static java.lang.String.format;

/**
 * Data conversion helper methods.
 * 
 * @author Juhan Aasaru
 * @author Rein Raudjärv
 */
public class DataTypeUtil {

	// Writing JDBC statement
	
	/**
	 * Add arguments (represented as "?" in query) to prepared statement.
	 * 
	 * @param stmt
	 *          Statement.
   * @param params
   * @param ctx
   */
	public static void setStatementParams(PreparedStatement stmt, List params, final Map<String, ? extends Object> ctx) throws SQLException {
    if (params==null || params.isEmpty()) return;
		int i = 0;
		for (Object param : params) {
			i++;
      if (param instanceof Declaration) {
        param=((Declaration)param).resolve(ctx);
      }
      setStatementParam(stmt, i, param);
		}
	}
	
	private static void setStatementParam(PreparedStatement stmt, int i, Object param) throws SQLException {
		if (param instanceof BigDecimal) {
			stmt.setBigDecimal(i, (BigDecimal) param);
		} else if (param instanceof Long) {
			stmt.setLong(i, (Long) param);
		} else if (param instanceof Integer) {
			stmt.setInt(i, (Integer) param);
		} else if (param instanceof Date) {
			stmt.setDate(i, new java.sql.Date(((Date) param).getTime()));
		} else if (param instanceof String) {
			stmt.setString(i, param.toString());
		} else {
			stmt.setObject(i, param);
		}
	}

	// Reading the result set

  /**
   * Change the datatype to reflect expected format.
   *
   * @param valueFromDb  Value to convert
   * @param expectedType Expected type
   * @return Value converted into expected datatype
   */
  public static <T> T convertIntoExpectedType(Object valueFromDb, Class<T> expectedType) {
    if (valueFromDb == null || expectedType.isAssignableFrom(valueFromDb.getClass())) {
      return expectedType.cast(valueFromDb);
    }
    if (Long.class.equals(expectedType) && valueFromDb instanceof Number) {
      return expectedType.cast( ((Number) valueFromDb).longValue());
    }
    if (Integer.class.equals(expectedType) && valueFromDb instanceof Number) {
      return expectedType.cast(((Number) valueFromDb).intValue());
    }
    if (Date.class.equals(expectedType) && valueFromDb instanceof java.sql.Date) {
      return expectedType.cast( new Date(((Date) valueFromDb).getTime()));
    }
    if (String.class.equals(expectedType)) {
      return expectedType.cast(valueFromDb.toString());
    }
    System.out.printf("Warning! Could not convert from %s into %s.",valueFromDb.getClass().getName(),expectedType.getClass().getName());
    return expectedType.cast(valueFromDb);
  }

  /**
   * Reads the corresponding value from the result set.
   * <p>
   * The value could be read from any number of result set columns.
   *
   * @param rs JDBC result set.
   * @return value read from the result set.
   */
  public static <T> T readFromResultSet(ResultSet rs, final String columnName, final Class<T> expectedType) throws SQLException {
    Object result = rs.getObject(columnName);
    if (result == null || expectedType.isInstance(result)) {
      return expectedType.cast(result);
    }
    return convertIntoExpectedType(result, expectedType);
  }

  /**
   * Reads the corresponding value from the result set.
   * <p>
   * The value could be read from any number of result set columns.
   *
   * @param rset JDBC result set.
   * @return value read from the result set.
   */
  @SuppressWarnings({"unchecked"})
  public static <T> T readFromResultSet(ResultSet rset, List<Column<?, T>> columns, Class<T> type) throws SQLException {
    Map<String, Method> setMethods = BeanUtil.getSetters(type);

    Object result = ReflectUtils.typeInstance(type);

    for (Column column : columns) {
      final Class tableType = column.getTableType();
      final Object value = readFromResultSet(rset, column.getAlias(), tableType);
      column.set(value);
      final String setterName = column.getModelSetterName();
      Method setter = setMethods.get(setterName);

      if (setter == null) {
        throw new SQLException(format("Column %s setter '%s' not found!!!",column,setterName));
      } else {
        ReflectUtils.setValue(result, setter, value);
      }
    }

    return type.cast(result);
  }
}
