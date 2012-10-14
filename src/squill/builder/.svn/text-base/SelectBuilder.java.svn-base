/**
 *
 */
package squill.builder;

import static squill.functions.Operations.and;
import squill.db.Database;
import squill.functions.Operations;
import squill.query.QueryContext;
import squill.query.from.FromExpression;
import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.query.where.WhereExpression;

/**
 * This transfer is output by from() method. NB! Extending WhereBuilder has
 * point here
 */
public class SelectBuilder extends SelWhereBuilder {

	public SelectBuilder(QueryContext ctx, Database database, ReadableTable baseTable, FromExpression... fromExprs) {
		super(ctx, database);
		addTable(baseTable);
		addTables(fromExprs);
	}

	public SelWhereBuilder where(WhereExpression whereClause) {
		wherePart.addWhereClause(whereClause);
		return this;
	}

	/**
	 * Placeholder for where() clause, does not add any restrictions
	 */
	public SelWhereBuilder where() {
		return this;
	}

	/**
	 * Takes comma separated array of restrictions and combines them with AND
	 * operator
	 */
	public SelWhereBuilder where(WhereExpression... whereClauses) {
		if (whereClauses == null || whereClauses.length == 0) {
			return this;
		}
		// By default - having multiple WHERE clauses means that they are
		// combined with AND
		return where(and(whereClauses));
	}

	// Different types of joins

	public <FIELD, TABLE> SelectBuilder join(ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1,
			Column<FIELD, ?> onField2) {
		super.addTables(Operations.join(table, onField1, onField2));
		return this;
	}

	public <FIELD, TABLE> SelectBuilder rightJoin(ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1,
			Column<FIELD, ?> onField2) {
		super.addTables(Operations.rightJoin(table, onField1, onField2));
		return this;
	}

	public <FIELD, TABLE> SelectBuilder leftJoin(ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1,
			Column<FIELD, ?> onField2) {
		super.addTables(Operations.leftJoin(table, onField1, onField2));
		return this;
	}
}
