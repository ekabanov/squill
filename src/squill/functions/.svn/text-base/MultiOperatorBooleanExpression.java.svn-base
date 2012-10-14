/**
 * 
 */
package squill.functions;

import static java.util.Arrays.asList;
import static squill.util.StringUtil.join;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import squill.query.BaseExpression;
import squill.query.Expression;
import squill.query.QueryContext;
import squill.query.where.WhereExpression;

public class MultiOperatorBooleanExpression<T> extends BaseExpression<Boolean> implements WhereExpression {
	private final List<Expression<T>> expressions;
	private final String operator;

	public MultiOperatorBooleanExpression(final String operator, final Expression<T>... expressions) {
		this.operator = operator;
		this.expressions = asList(expressions);
	}

	public MultiOperatorBooleanExpression(final String operator, final Collection<Expression<T>> expressions) {
		this.operator = operator;
		this.expressions = new ArrayList<Expression<T>>(expressions);
	}

	public List<? extends Expression<?>> getParts() {
		return expressions;
	}

	public String getOperator() {
		return operator;
	}

	@Override
	public String getDefaultSql() {
		return "(" + join(expressions, Expression.GET_SQL_STRING, operator) + ")";
	}

	@Override
	public List<Object> getSqlArguments() {
		List<Object> result = new ArrayList<Object>();
		for (Expression<T> expression : expressions) {
			result.addAll(expression.getSqlArguments());
		}
		return result;
	}

	@Override
	public void setQueryContext(QueryContext ctx) {
		super.setQueryContext(ctx);
		for (Expression<T> expression : expressions) {
			expression.setQueryContext(ctx);
		}
	}

	@Override
	public WhereExpression and(WhereExpression... exps) {
		WhereExpression[] newExps = new WhereExpression[exps.length + 1];
		newExps[0] = this;
		System.arraycopy(exps, 0, newExps, 1, exps.length);
		return Operations.and(newExps);
	}

	@Override
	public WhereExpression or(WhereExpression... exps) {
		WhereExpression[] newExps = new WhereExpression[exps.length + 1];
		newExps[0] = this;
		System.arraycopy(exps, 0, newExps, 1, exps.length);
		return Operations.or(newExps);
	}
}