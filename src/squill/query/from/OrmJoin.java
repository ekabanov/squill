package squill.query.from;

import squill.query.JoinType;
import squill.query.select.Column;
import squill.query.select.ReadableTable;

public interface OrmJoin {
  ReadableTable getTable();
  Column<?, ?> getSource();
  Column<?, ?> getTarget();
  JoinType getJoinType();
  String getAlias();
}
