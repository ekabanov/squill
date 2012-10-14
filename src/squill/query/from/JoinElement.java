/**
 * Parts of SQL query as objects.
 */
package squill.query.from;

import static java.lang.String.format;

import java.util.Collections;
import java.util.List;

import squill.query.JoinType;
import squill.query.QueryContext;
import squill.query.QueryPart;
import squill.query.select.Column;
import squill.query.select.ReadableTable;



/**
 * join expression, TODO sql arguments, whole where expressions !!
 */
public class JoinElement<FIELD, OBJ> implements FromExpression, QueryPart {

  private final ReadableTable<OBJ> table;
  private final Column<FIELD, OBJ> field1;
  private final Column<FIELD, ?> field2;
  private final JoinType type;

  public JoinElement(OrmJoin ojoin) {
    this((ReadableTable) ojoin.getTable(), (Column) ojoin.getSource(), (Column) ojoin.getTarget(), ojoin.getJoinType());
  }
  
  public JoinElement(ReadableTable<OBJ> table, Column<FIELD, OBJ> field1, Column<FIELD, ?> field2, JoinType type) {
    this.table = table;
    this.field1 = field1;
    this.field2 = field2;
    this.type = type;
  }

  // TODO neccesary?
  public ReadableTable<OBJ> getTable() {
    return table;
  }

  public String getDefaultSql() {
    return getFromSql();
  }

  public List<Object> getSqlArguments() {
    return Collections.emptyList();
  }

  public String getFromSql() {
    return format(" %s JOIN %s ON (%s = %s) ", type,
        table.getTableWithAliasSql(), field1.getDefaultSql(), field2.getDefaultSql());
  }
  
  public boolean isJoin() {    
    return true;
  }
  
  public void setQueryContext(QueryContext ctx) {
    table.setQueryContext(ctx);
    field1.setQueryContext(ctx);
    field2.setQueryContext(ctx);
  }
}

