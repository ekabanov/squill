/**
 *
 */
package squill.builder;

import squill.db.Database;
import squill.query.QueryContext;
import squill.query.select.WritableTable;
import squill.query.where.WhereExpression;


/**
 * For building up a query that updates fields of a table.
 */
public class UpdateBuilder<TABLE> extends UpWhereBuilder<TABLE> {

    public UpdateBuilder(QueryContext ctx, Database database, WritableTable<TABLE> baseTables) {
        super(ctx, database);
        addTable(baseTables);
    }

    /**
     * Place holder for empty where clause, does not add any restrictions,
     * can be omitted from query
     */
    public UpWhereBuilder<TABLE> where() {
        return this;
    }

    /**
     * Comma-separated list of restrictions for the query, combined with AND-operator.
     */
    public UpWhereBuilder<TABLE> where(WhereExpression whereClause) {
        wherePart.addWhereClause(whereClause);
        return this;
    }

}
