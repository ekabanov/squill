package squill.query;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import squill.db.TypedSQLException;
import squill.format.Sql92Format;
import squill.query.orderby.OrderByElement;
import squill.query.select.SelectExpression;
import squill.tree.QueryPartHandler;
import squill.tree.TreeTraverser;



public class SelectPart implements QueryPart {
  private final List<SelectExpression<?>> selectList = new ArrayList<SelectExpression<?>>();
  private final QueryContext ctx;

  public SelectPart(QueryContext ctx) {
    this.ctx = ctx;
  }

  public void addSelects(final SelectExpression<?>...selects) {
    selectList.addAll(asList(selects));
    setQueryContext(ctx);
  }

  public String getDefaultSql() {
    return "SELECT " + squill.util.StringUtil.join(selectList, Sql92Format.GET_SELECT_SQL, ", ");
  }

  public <T> T traverse(final QueryPartHandler<T> handler, final T collectingParameter) {
    handler.handle(this, collectingParameter);
    final TreeTraverser traverser=new TreeTraverser();
    for (SelectExpression<?> selectExpression : selectList) {
      traverser.traverse(selectExpression,handler, collectingParameter);
    }
    return collectingParameter;
  }

  public List<SelectExpression<?>> getSelectList() {
    return selectList;
  }

  public void checkOrderByOccurrence(final OrderByElement<?> orderby) {
    final List<SelectExpression<?>> selectList = getSelectList();
    if (orderby.isMustOccurInSelect() && !selectList.contains(orderby.getSelectExpr())) {
      throw new TypedSQLException("Orderby expression '" + orderby.getSelectExpr().getDefaultSql() + "' not found in select!");
    }
  }

  public List<Object> getSqlArguments() {
    List<Object> result = new ArrayList<Object>();
    
    for (SelectExpression<?> expr : selectList) {
      result.addAll(expr.getSqlArguments());
    }
    
    return result;
  }  
  
  public void setQueryContext(QueryContext ctx) {
    for (SelectExpression<?> expr : selectList) {
      expr.setQueryContext(ctx);
    }
  }
}
