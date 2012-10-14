package squill.query;

import java.util.ArrayList;
import java.util.List;

import squill.format.Sql92Format;
import squill.query.orderby.OrderByElement;
import squill.tree.QueryPartHandler;



public class OrderByPart implements QueryPart {
  private List<OrderByElement<?>> orderByList = new ArrayList<OrderByElement<?>>();
  private final QueryContext ctx;

  public OrderByPart(QueryContext ctx) {
    this.ctx = ctx;
  }

  public void setOrderByList(final List<OrderByElement<?>> orderByElements) {
    this.orderByList.clear();
    this.orderByList.addAll(orderByElements);
    setQueryContext(ctx);
  }

  public String getDefaultSql() {
    if (orderByList.isEmpty()) {
      return "";
    }
    StringBuilder sql = new StringBuilder();
    sql.append(" ORDER BY ");
    sql.append(squill.util.StringUtil.join(orderByList, Sql92Format.GET_SQL, ", "));
    return sql.toString();
  }


  public void checkOrderByOccurrence(final FromPart tablePart, final SelectPart selectPart) {
    for (OrderByElement<?> orderby : orderByList) {
      checkOrderByOccurrence(orderby, tablePart, selectPart);
    }
  }

  private void checkOrderByOccurrence(OrderByElement<?> orderby, final FromPart tablePart, final SelectPart selectPart) {
    selectPart.checkOrderByOccurrence(orderby);
    tablePart.checkOrderByOccurrence(orderby);
  }

  public <T> void traverse(final QueryPartHandler<T> handler, final T collectingParameter) {
    handler.handle(this,collectingParameter);
    for (OrderByElement<?> orderByElement : orderByList) {
      handler.handle(orderByElement, collectingParameter);
    }
  }
  public List<Object> getSqlArguments() {
    return null;
  }

  public List<OrderByElement<?>> getOrderByList() {
    return orderByList;
  }
  
  public void setQueryContext(QueryContext ctx) {
    for (OrderByElement<?> orderByElement : orderByList) {
      orderByElement.setQueryContext(ctx);
    }
  }
}
