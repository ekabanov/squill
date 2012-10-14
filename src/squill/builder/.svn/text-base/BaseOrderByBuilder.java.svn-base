package squill.builder;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import squill.callback.ResultCallback;
import squill.db.Database;
import squill.format.SqlFormat;
import squill.query.*;
import squill.query.orderby.OrderByElement;
import squill.query.select.SelectExpression;
import squill.query.select.SimpleSelectExpression;
import squill.tree.QueryPartHandler;
import squill.tuple.Tuple;
import squill.tuple.Tuple1;

public class BaseOrderByBuilder extends BaseBuilder {
	protected final SelectPart selectPart;
	protected final OrderByPart orderByPart;
	protected static final Log log = LogFactory.getLog(BaseOrderByBuilder.class);
	
	public BaseOrderByBuilder(QueryContext ctx, Database dataSource) {
		super(ctx, dataSource);
		selectPart = new SelectPart(ctx);
		orderByPart = new OrderByPart(ctx);
	}
	
	protected void addSelects(SelectExpression<?>... selects) {
		selectPart.addSelects(selects);
		addArgs(selects);
	}
	
	private void addArgs(final Expression<?>... expressions) {
		for (Expression expression : expressions) {
			addArgs(expression.getSqlArguments());
		}
	}
	
	protected void setOrderbyList(OrderByElement<?>[] orderbyArray) {
		orderByPart.setOrderByList(asList(orderbyArray));
	}
	
	public <FT> SelectExpression<FT> subSelect(final SelectExpression<FT> select) {
		addSelects(select);
		SelectExpression<FT> exp = new SimpleSelectExpression<FT>(select.getTableType()) {
			@Override
			public String getDefaultSql() {
				return "(" + getSql() + ")";
			}
			
			@Override
			public List<Object> getSqlArguments() {
				return select.getSqlArguments();
			}
			
			@Override
			public void setQueryContext(QueryContext ctx) {
				super.setQueryContext(ctx);
				BaseOrderByBuilder.this.setQueryContext(ctx);
			}
		};
		
		return exp;
	}
	
	@Override
	protected void setQueryContext(QueryContext ctx) {
		super.setQueryContext(ctx);
		selectPart.setQueryContext(ctx);
		orderByPart.setQueryContext(ctx);
	}
	
	Tuple queryTuple(Class<? extends Tuple> tupleType, SelectExpression<?>... selects) {
		addSelects(selects);
		return database.queryTuple(getSql(), getArgs(), selectPart.getSelectList(), tupleType);
	}
	
	List queryTuples(Class<? extends Tuple> tupleType, SelectExpression<?>... selects) {
		addSelects(selects);
		return database.queryList(getSql(), getArgs(), selectPart.getSelectList(), tupleType);
	}
	
	@SuppressWarnings( { "unchecked" })
	<P extends Tuple, R> List<R> queryCallback(Class<P> tupleType, ResultCallback<P, R> callback, SelectExpression<?>... selects) {
		addSelects(selects);
		return database.queryCallback(getSql(), getArgs(), selectPart.getSelectList(), tupleType, (ResultCallback<Tuple, R>) callback);
	}
	
	@Override
	// Select full, tbl objects (each object selects all its known fields)
	public String getSql() {
		// orderByPart.checkOrderByOccurrence(fromPart, selectPart);
		
		StringBuilder sql = new StringBuilder();
		sql.append(selectPart.getDefaultSql());
		sql.append(fromPart.getDefaultSql());
		sql.append(wherePart.getDefaultSql());
		sql.append(orderByPart.getDefaultSql());
		return sql.toString();
	}
	
	public String getSql(final SqlFormat format) {
		final StringBuilder sql = new StringBuilder();
		handle(new QueryPartHandler<StringBuilder>() {
			public void handle(final QueryPart queryPart, final StringBuilder sql) {
				if (queryPart == null) {
					return;
				}
				sql.append(format.format(queryPart));
			}
		}, sql);
		return sql.toString();
	}
	
	public String getCountSql() {
		StringBuilder sql = new StringBuilder("SELECT count(*) ");
		sql.append(fromPart.getDefaultSql());
		sql.append(wherePart.getDefaultSql());
		return sql.toString();
	}
	
	public long count() {
		return database.count(getCountSql(), getArgs());
	}
	
	public <T> T traverse(QueryPartHandler<T> handler, T collectingParameter) {
		selectPart.traverse(handler, collectingParameter);
		fromPart.traverse(handler, collectingParameter);
		wherePart.traverse(handler, collectingParameter);
		orderByPart.traverse(handler, collectingParameter);
		return collectingParameter;
	}
	
	public <T> T handle(QueryPartHandler<T> handler, T collectingParameter) {
		handler.handle(selectPart, collectingParameter);
		handler.handle(fromPart, collectingParameter);
		handler.handle(wherePart, collectingParameter);
		handler.handle(orderByPart, collectingParameter);
		return collectingParameter;
	}
	
	/**
	 * Select 1 value(s) as single object (not a list)
	 */
	public <F1> F1 select(SelectExpression<F1> select1) {
		Tuple1<F1> tuple1 = (Tuple1<F1>) queryTuple(Tuple1.class, select1);
		return tuple1 == null ? null : tuple1.v1;
	}
	
	/**
	 * Select 1 value(s) as a list
	 */
	public <F1> List<F1> selectList(SelectExpression<F1> select1) {
		List<F1> result = new ArrayList<F1>();
		List<Tuple1<F1>> tupled = queryTuples(Tuple1.class, select1);
		for (Tuple1<F1> tuple1 : tupled) {
			result.add(tuple1.v1);
		}
		return result;
	}
	
	public <F1, R> List<R> selectCallback(SelectExpression<F1> select1, final ResultCallback<F1, R> callback) {
		final ResultCallback<Tuple1<F1>, R> tupleCallback = new ResultCallback<Tuple1<F1>, R>() {
			public R handle(final Tuple1<F1> tuple, final Status status) {
				return callback.handle(tuple.v1, status);
			}
		};
		return queryCallback(Tuple1.class, (ResultCallback<Tuple1, R>) (Object) tupleCallback, select1);
	}
	
	public <F1> ResultBuilder<Tuple1<F1>> selectAs(SelectExpression<F1> select1) {
		addSelects(select1);
		return new ResultBuilder<Tuple1<F1>>(this, Tuple1.class);
	}
}
