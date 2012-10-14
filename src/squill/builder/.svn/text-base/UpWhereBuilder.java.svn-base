package squill.builder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import squill.db.Database;
import squill.query.QueryContext;
import squill.query.cud.UpdateCruElement;
import squill.query.cud.UpdateElement;
import squill.query.cud.UpdateFieldElement;
import squill.util.StringUtil;




/**
 * WHERE Builder object used in UPDATE clause.
 * NB! For SELECT clause there is separate WHERE-clause
 *
 * @see squill.builder.SelWhereBuilder
 */
public class UpWhereBuilder<TABLE> extends BaseBuilder {

    // list of fields to select from

    private List<UpdateElement<TABLE>> setList;

    protected UpWhereBuilder(QueryContext ctx, Database database) {
        super(ctx, database);
    }

    public long set(UpdateFieldElement<TABLE>... elements) {
        setList = Arrays.<UpdateElement<TABLE>>asList(elements);

        for (UpdateFieldElement<TABLE> updateFieldElement : elements) {
            updateFieldElement.setQueryContext(ctx);
            addArgs(updateFieldElement.getSqlArguments());
        }

        return executeCUD();

        // TODO - validate that elements belong to table (if not type safe!)
    }

    public long set(UpdateCruElement<TABLE> cruElement) {
        cruElement.setQueryContext(ctx);
        
        addArgs(cruElement.getSqlArguments());
        setList = Collections.<UpdateElement<TABLE>>singletonList(cruElement);

        return executeCUD();
    }

    @Override
    public String getSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(fromPart.getTablesWithAliasesSql());
        sql.append(" SET ");
        sql.append(getSetValuesSql());
        sql.append(wherePart.getDefaultSql());
        return sql.toString();
    }

  protected String getSetValuesSql() {
    return StringUtil.join(setList, UpdateElement.GET_SQL, ", ");
  }

}
