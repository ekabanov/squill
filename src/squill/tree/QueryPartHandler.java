package squill.tree;

import squill.query.QueryPart;

public interface QueryPartHandler<T> {
  void handle(final QueryPart queryPart, final T initial);
}
