package squill.query.where;

import squill.query.Expression;
import squill.query.MultiPartExpression;

/**
 * Expression that can be used in WHERE-clause.
 * <p>
 * Where expression is always of Boolean type
 * representing a logical expression.
 * 
 * @see Expression
 */
public interface WhereExpression extends Expression<Boolean>, MultiPartExpression<Boolean> {
	
	WhereExpression or(WhereExpression... exps);

	WhereExpression and(WhereExpression... exps);
}
