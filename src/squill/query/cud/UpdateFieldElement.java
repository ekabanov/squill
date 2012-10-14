/**
 * Parts of SQL query as objects.
 */
package squill.query.cud;

import java.util.List;

import squill.query.QueryContext;
import squill.query.select.Column;
import squill.query.select.SelectExpression;

/**
 * One SET declaration of update. For example "viewed = viewed+1" in: UPDATE ad
 * SET viewed = viewed+1, lastviewed=sysdate WHERE id=17
 */
public class UpdateFieldElement<OBJ> implements UpdateElement<OBJ> {
	
	Column<?, OBJ> field;
	SelectExpression<?> updateExpr;
	
	public UpdateFieldElement(Column<?, OBJ> field, SelectExpression<?> updateExpr) {
		this.field = field;
		this.updateExpr = updateExpr;
	}
	
	public String getDefaultSql() {
		// Here we do not use aliases before column names
		// to provide compatibility with Postgre
		return field.getColumnName() + "=" + updateExpr.getDefaultSql();
	}
	
	public List<Object> getSqlArguments() {
		return updateExpr.getSqlArguments();
	}
	
	public void setQueryContext(QueryContext ctx) {
		field.setQueryContext(ctx);
		updateExpr.setQueryContext(ctx);
	}
}
