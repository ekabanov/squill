/**
 * Parts of SQL query as objects.
 */
package squill.query.orderby;

import java.util.List;

import squill.format.FormatUtil;
import squill.query.Expression;
import squill.query.QueryContext;
import squill.query.select.Column;
import squill.query.select.SelectExpression;



/**
 * One FIELD AND ASC/DESC pair of orderby. For example "name" and "DESC" in following query:
 * SELECT * FROM customer ORDER BY name DESC
 */
public class OrderByElement<OBJ> implements Expression<OBJ> {

  private SelectExpression<OBJ> selectExpr;
  private boolean ascending = true; // default order
  private boolean mustOccurInSelect = false; // must this expression be present in select?

  public OrderByElement(Column<OBJ, ?> selectExpr, boolean ascendingly) {
    this.selectExpr = selectExpr;
    this.ascending = ascendingly;
  }

  public OrderByElement(SelectExpression<OBJ> selectExpr, boolean ascending) {
    this.selectExpr = selectExpr;
    this.ascending = ascending;
    mustOccurInSelect = true; // this custom expression must occur in select
  }

  public boolean isMustOccurInSelect() {
    return mustOccurInSelect;
  }

  public SelectExpression<OBJ> getSelectExpr() {
    return selectExpr;
  }

  public String getDefaultSql() {
    if (ascending)
      return FormatUtil.formatOrderBy(selectExpr) + " ASC";
    return FormatUtil.formatOrderBy(selectExpr) + " DESC";
  }

  public List<Object> getSqlArguments() {
    return getSelectExpr().getSqlArguments();
  }
  
  public void setQueryContext(QueryContext ctx) {
    selectExpr.setQueryContext(ctx);
  }
}