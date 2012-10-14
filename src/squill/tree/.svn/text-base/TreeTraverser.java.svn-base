package squill.tree;

import squill.query.Expression;
import squill.query.MultiPartExpression;
import squill.query.QueryPart;
import squill.query.select.Column;
import squill.query.select.ReadableTable;

public class TreeTraverser {
  public <T> T traverse(QueryPart queryPart, QueryPartHandler<T> handler,T initial) {
    handler.handle(queryPart,initial);
    if (queryPart instanceof ReadableTable<?>) {
      for (Column column : ((ReadableTable<?>) queryPart).getColumns()) {
        traverse(column,handler, initial);
      }
    }
    if (queryPart instanceof MultiPartExpression) {
      for (Expression<?> expression : ((MultiPartExpression<?>) queryPart).getParts()) {
        traverse(expression, handler, initial);
      }
    }
    return initial;
  }
}
