package squill.query.cud;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import squill.db.TypedSQLException;
import squill.query.QueryContext;
import squill.query.select.Column;
import squill.query.select.ReadableTable;



/**
 * @author Michael Hunger
 * @since 24.08.2008
 */
public abstract class AbstractCUDElement<OBJ> implements CUDElement {
  protected final ReadableTable<OBJ> table;
  protected final OBJ updateObj;
  private String sql;
  private List<Object> args;

  public AbstractCUDElement(ReadableTable<OBJ> table, OBJ updateObj) {
    this.table = table;
    this.updateObj = updateObj;
  }

  public String getDefaultSql() {
    if (sql == null) {
      generateSqlAndArgs();
    }
    return sql;

  }

  public List<Object> getSqlArguments() {
    if (args == null) {
      generateSqlAndArgs();
    }
    return args;
  }

  private void generateSqlAndArgs() {
    Map<Column, Object> columnValueMap = table.getColumnValueMap(updateObj, true);
    if (columnValueMap.isEmpty()) {
      throw new TypedSQLException("Model object to update has no fields/getters!"); // TODO
    }

    List<Object> argsList = new ArrayList<Object>();
    this.sql = createSqlAndArgs(columnValueMap, argsList);
    args = argsList;
  }

  public void setQueryContext(QueryContext ctx) {
    table.setQueryContext(ctx);    
  }

  protected abstract String createSqlAndArgs(Map<Column, Object> columnValueMap, List<Object> argsList);
}
