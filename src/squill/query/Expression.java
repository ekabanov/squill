package squill.query;

import squill.query.select.SelectExpression;
import squill.query.where.WhereExpression;
import squill.util.ToString;

/**
 * Expression is everything that goes behind SELECT and WHERE.
 * 
 * @param <T> Type that will be returned if this expression is evaluated.
 * WHERE only takes expressions where T = Boolean.
 * SELECT should only take Boolean expressions if it is supported by dialect.
 * 
 * @see SelectExpression
 * @see WhereExpression
 */
public interface Expression<T> extends QueryPart {
  ToString<Expression> GET_SQL_STRING = new ToString<Expression>() {
    public String toString(final Expression expression) {
      return expression.getDefaultSql();
    }
  };
}
