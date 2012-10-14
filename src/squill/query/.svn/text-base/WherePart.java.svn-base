package squill.query;

import static squill.functions.Operations.and;

import java.util.Collections;
import java.util.List;

import squill.query.where.WhereExpression;
import squill.tree.QueryPartHandler;
import squill.tree.TreeTraverser;



public class WherePart implements QueryPart {
  private WhereExpression whereClause;
  private final QueryContext ctx;

  public WherePart(QueryContext ctx) {
    this.ctx = ctx;
  }

  public List<Object> getSqlArguments() {
    if (whereClause == null) {
      return Collections.emptyList();
    }
    return whereClause.getSqlArguments();
  }

  public String getDefaultSql() {
    if (whereClause == null) {
      return ""; // if there is no where clauses or when it is INSERT going on
    }
    return " WHERE " + whereClause.getDefaultSql();
  }

  public <T> T traverse(final QueryPartHandler<T> handler, final T collectingParameter) {
    final TreeTraverser traverser = new TreeTraverser();
    traverser.traverse(this, handler, collectingParameter);
    traverser.traverse(whereClause, handler, collectingParameter);
    return collectingParameter;
  }

  public void addWhereClause(final WhereExpression whereClause) {
    whereClause.setQueryContext(ctx);
    if (this.whereClause==null) this.whereClause=whereClause;
    else this.whereClause=and(this.whereClause,whereClause);
  }

  public WhereExpression getWhereClause() {
    return whereClause;
  }
  
  public void setQueryContext(QueryContext ctx) {
    whereClause.setQueryContext(ctx); 
  }
}
