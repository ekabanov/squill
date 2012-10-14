/**
 * 
 */
package squill.functions;

import static java.util.Arrays.asList;

import java.util.List;

import squill.query.Expression;
import squill.query.MultiPartExpression;
import squill.query.QueryContext;
import squill.query.select.SimpleSelectExpression;



public class BinaryOperatorSelectExpression<RETURN_TYPE, T1, T2> extends SimpleSelectExpression<RETURN_TYPE> implements MultiPartExpression<RETURN_TYPE> {
  private final Expression<T1> exp1;
  private final String operator;
  private final Expression<T2> exp2;

  public BinaryOperatorSelectExpression(final Class<RETURN_TYPE> type, final Expression<T1> exp1, final String operator, final Expression<T2> exp2) {
    super(type);
    this.exp1 = exp1;
    this.operator = operator;
    this.exp2 = exp2;
  }

  @Override
  public String getDefaultSql() {
    StringBuilder sql = new StringBuilder();
    sql.append(exp1.getDefaultSql());
    sql.append(operator);
    sql.append(exp2.getDefaultSql());
    return sql.toString();
  }

  @Override
  public List<Object> getSqlArguments() {
    return OperationsHelper.concat(exp1.getSqlArguments(), exp2.getSqlArguments());
  }

  public List<? extends Expression<?>> getParts() {
    return asList(exp1,exp2);
  }

  public String getOperator() {
    return operator;
  }
  
  @Override
  public void setQueryContext(QueryContext ctx) {
    super.setQueryContext(ctx);
    exp1.setQueryContext(ctx);
    exp2.setQueryContext(ctx);
  }
}