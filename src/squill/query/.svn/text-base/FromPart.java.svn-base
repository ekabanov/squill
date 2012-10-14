package squill.query;

import static squill.util.StringUtil.join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import squill.alias.Alias;
import squill.db.TypedSQLException;
import squill.format.Sql92Format;
import squill.query.from.FromExpression;
import squill.query.from.JoinElement;
import squill.query.from.OrmJoin;
import squill.query.orderby.OrderByElement;
import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.tree.QueryPartHandler;
import squill.tree.TreeTraverser;
import squill.util.StringUtil;



public class FromPart implements QueryPart {
  // list of tables to select from
  private final List<ReadableTable> tableList = new ArrayList<ReadableTable>();
  private final List<JoinElement> joinList = new ArrayList<JoinElement>();

  // list of all used table aliases
  protected final Collection<String> tableAliases = new HashSet<String>();
  private final QueryContext ctx;


  public FromPart(QueryContext ctx) {
    this.ctx = ctx;
  }

  protected void addTable(ReadableTable... tables) {
    this.tableList.addAll(Arrays.asList(tables));

    // register all used table aliases for validating purposes
    for (ReadableTable table : tables) {
      table.setQueryContext(ctx);
      //registerTableAlias(table.getAlias());
    }
  }

  /**
   * Register using of alias in order to check the uniqueness of all aliases
   *
   * @param alias Alias that is being used in query.
   */
//  protected void registerTableAlias(String alias) {
//    tableAliases.add(alias);
//  }


  /**
   * Return comma separated list of tablenames. Select has to owerwrite this to
   * include JOINs
   */
  public String getTablesWithAliasesSql() {
    return StringUtil.join(tableList, Sql92Format.GET_FROM_SQL, ", ");
  }

  public String getTablesSql() {
    return join(getTableList(), ReadableTable.GET_NAME, ", ");
  }

  public List<ReadableTable> getTableList() {
    return Collections.unmodifiableList(tableList);
  }

  protected void addJoin(FromExpression join) {
    if (join instanceof OrmJoin)
      join = new JoinElement((OrmJoin) join);
    
    ((JoinElement) join).setQueryContext(ctx);
    this.joinList.add((JoinElement) join);
    //registerTableAlias(((JoinElement) join).getTable().getAlias());
  }

  public String getJoinSql() {
    return StringUtil.join(joinList, Sql92Format.GET_FROM_SQL, "");
  }

  public <T> void traverse(final QueryPartHandler<T> handler, final T collectingParameter) {
    TreeTraverser traverser = new TreeTraverser();
    traverser.traverse(this, handler,  collectingParameter);
    for (ReadableTable table : getTableList()) {
      traverser.traverse(table, handler, collectingParameter);
    }

    for (JoinElement joinElement : joinList) {
      traverser.traverse(joinElement, handler, collectingParameter);
    }
  }

  public void checkOrderByOccurrence(final OrderByElement<?> orderby) {
    if (orderby.getSelectExpr() instanceof Column) {
      Column field = (Column) orderby.getSelectExpr();
      if (!this.tableAliases.contains(field.getTable().getAlias())) {
        // Order by against object not present in from/join
        throw new TypedSQLException("Orderby expression '" + orderby.getDefaultSql() + "' not found inside from/join");
      }
    }
  }

  public String getDefaultSql() {
    return " FROM " + getTablesWithAliasesSql() + getJoinSql();
  }

  public List<Object> getSqlArguments() {
    return null;
  }

  public void addFromExpressions(FromExpression... fromExprs) {
    for (FromExpression fromExpr : fromExprs) {
      // TODO Maybe keep all in one List and sort out types during creating of SQL
      if (fromExpr.isJoin()) {
        addJoin(fromExpr);
      } else {
        addTable((ReadableTable) fromExpr);
      }
    }
  }

  public List<JoinElement> getJoinList() {
    return joinList;
  }
  
  public void setQueryContext(QueryContext ctx) {
    for (ReadableTable table : getTableList()) {
      table.setQueryContext(ctx);
    }

    for (JoinElement joinElement : joinList) {
      joinElement.setQueryContext(ctx);
    }
  }
}
