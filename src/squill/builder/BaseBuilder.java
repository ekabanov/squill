/**
 * Objects being passed inside fluent interface calls.
 */
package squill.builder;

import java.util.ArrayList;
import java.util.List;

import squill.db.Database;
import squill.query.FromPart;
import squill.query.QueryContext;
import squill.query.WherePart;
import squill.query.from.FromExpression;
import squill.query.select.ReadableTable;



/**
 * Transfers in fluent interface implement this interface.
 */
public abstract class BaseBuilder {
  protected final Database database;
  protected QueryContext ctx;
  protected final FromPart fromPart;
  protected final WherePart wherePart;
  
  protected BaseBuilder(QueryContext ctx, Database database) {
    this.ctx = ctx;
    this.database = database;
    
    fromPart = new FromPart(ctx);
    wherePart = new WherePart(ctx);
  }

  protected void addTables(FromExpression... fromExpr) {
    fromPart.addFromExpressions(fromExpr);
  }

  protected void addTable(ReadableTable table) {
    fromPart.addFromExpressions(table);
  }

  private final List<Object> argsList = new ArrayList<Object>();

  protected void addArg(Object arg) {
    argsList.add(arg);
  }

  protected void addArgs(List<Object> args) {
    argsList.addAll(args);
  }

  protected FromPart getTablePart() {
    return fromPart;
  }

  protected List<Object> getArgs() {
    List<Object> args = new ArrayList<Object>(argsList);
    args.addAll(wherePart.getSqlArguments());
    return args;
  }

  /**
   * Glue the final SQL out of fragments, some of them are implemented by
   * extending classes
   */
  public abstract String getSql();

  protected long executeCUD() {
    return database.executeCUD(getSql(), getArgs(), ctx);
  }

  public void addParam(String name, Object value) {
    database.addParam(name, value);
  }

  protected void setQueryContext(QueryContext ctx) {
    this.ctx = ctx;
    fromPart.setQueryContext(ctx);
    wherePart.setQueryContext(ctx);
  }
}
