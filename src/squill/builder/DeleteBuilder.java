package squill.builder;

import squill.db.Database;
import squill.query.QueryContext;
import squill.query.select.WritableTable;
import squill.query.where.WhereExpression;


public class DeleteBuilder<TABLE> extends BaseBuilder {

    /**
     * @param table Table to insert data into
     */
    public DeleteBuilder(QueryContext ctx, Database dataSource, WritableTable<?> table) {
        super(ctx, dataSource);
        addTable(table);
    }

    /**
     * Delete all rows in table
     */
    public long all() {
        return executeCUD();
    }

    public long where(WhereExpression whereClause) {
        wherePart.addWhereClause(whereClause);
        return executeCUD();
    }

    @Override
    public String getSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append(fromPart.getDefaultSql());
        sb.append(wherePart.getDefaultSql()); // WHERE (if added any)
        return sb.toString();
	}
	

}
