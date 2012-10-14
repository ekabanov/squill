/**
 * Parts of SQL query as objects.
 */
package squill.query.cud;

import static squill.functions.Operations.constant;

import java.util.List;

import squill.query.QueryContext;
import squill.query.select.Column;
import squill.query.select.SelectExpression;



/**
 * One FIELD AND VALUE pair of insert. For example "name, 'John Smith'" in:
 * INSERT INTO customer (id,name,address) VALUES (17, 'John Smith', 'London 1, England')
 */
public class InsertFieldElement<OBJ> implements InsertElement {

    private final Column<?, OBJ> field;
    private final SelectExpression<?> insertExpr;

    public <FIELD> InsertFieldElement(Column<FIELD, OBJ> field, SelectExpression<FIELD> insertExpr) {
        this.field = field;
        this.insertExpr = insertExpr;
    }

    public <FIELD> InsertFieldElement(Column<FIELD, OBJ> field, FIELD insertField) {
        this(field, constant(insertField));
    }


    public Column<?, OBJ> getField() {
        return field;
    }
    
    public String getDefaultSql() {
        return field.getDefaultSql();
    }

    public String getValueSql() {
        return insertExpr.getDefaultSql();
    }
    
    public List<Object> getSqlArguments() {
        return insertExpr.getSqlArguments();
    }
    
    public void setQueryContext(QueryContext ctx) {
      field.setQueryContext(ctx);
      insertExpr.setQueryContext(ctx);
    }
}
