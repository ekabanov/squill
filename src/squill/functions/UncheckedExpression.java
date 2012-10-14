/**
 * 
 */
package squill.functions;

import static java.util.Arrays.asList;

import java.util.List;

import squill.query.select.SimpleSelectExpression;



public final class UncheckedExpression<T> extends SimpleSelectExpression<T> {
  private final String sql;
  private final Object[] args;

  UncheckedExpression(String alias, Class<T> type, String sql, Object[] args) {
    super(alias, type);
    this.sql = sql;
    this.args = args;
  }

  @Override
  public List<Object> getSqlArguments() {
    return asList(args);
  }

  @Override
  public String getDefaultSql() {
    return "(" + sql + ")";
  }
}