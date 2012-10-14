/**
 *
 */
package squill.builder;

import static squill.util.StringUtil.join;

import java.util.Arrays;
import java.util.List;

import squill.db.Database;
import squill.query.QueryContext;
import squill.query.cud.InsertCruElement;
import squill.query.cud.InsertFieldElement;
import squill.query.select.WritableTable;
import squill.query.select.Column;
import squill.util.ToString;
import squill.tuple.Tuple;


/**
 * @param <TABLE>
 * Table type
 */
public class InsertBuilder<TABLE> extends BaseBuilder {

    private static final ToString<InsertFieldElement<?>> GET_SQL = new ToString<InsertFieldElement<?>>() {
        public String toString(InsertFieldElement<?> insertFieldElement) {
            return insertFieldElement.getField().getColumnName(); // getSql();
        }
    };
    private static final ToString<InsertFieldElement<?>> GET_FIELD_SQL = new ToString<InsertFieldElement<?>>() {
        public String toString(InsertFieldElement<?> insertFieldElement) {
            return insertFieldElement.getValueSql();
        }
    };

    // list of fields to select from
    private List<InsertFieldElement<TABLE>> fieldValueList;
    private InsertCruElement<TABLE> cruElement;

    /**
     * @param table Table to insert data into
     */
    public InsertBuilder(QueryContext ctx, Database dataSource, WritableTable<TABLE> table) {
        super(ctx, dataSource);
        addTable(table);
    }

    // TODO when is this called in the lifecycle ?
    // should not execute the stuff
    //thesis:label=valuesMeetod
    public long values(InsertFieldElement<TABLE>... insertFieldElements) {
        for (InsertFieldElement<TABLE> insertElement : insertFieldElements) {
            insertElement.setQueryContext(ctx);
            addArgs(insertElement.getSqlArguments());
        }

        fieldValueList = Arrays.asList(insertFieldElements);

        return executeCUD();
    }

    public long values(InsertCruElement<TABLE> cruElement) {
        cruElement.setQueryContext(ctx);
      
        addArgs(cruElement.getSqlArguments());
        this.cruElement = cruElement;               

        return executeCUD();
    }

    public String getSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(fromPart.getTablesSql());
        sb.append(getTablesAndFieldsSql());
        return sb.toString();
    }

  private String getTablesAndFieldsSql() {
        // single CRU object
        if (cruElement != null) {
            return cruElement.getDefaultSql();
        }
        if (fieldValueList == null) {
            return "";
        }
        return " (" + join(fieldValueList, GET_SQL, ", ") + ") " +
                " VALUES ( " + join(fieldValueList, GET_FIELD_SQL, ", ") + ")";
    }

    public <T extends Tuple> InsertBuilder<TABLE> returnId() {
        this.ctx.setReturnId();
        return this;
    }
}
