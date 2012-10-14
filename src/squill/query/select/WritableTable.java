/**
 * 
 */
package squill.query.select;

import squill.query.from.FromExpression;


/**
 * Table that the user has besides select ability to update/insert/delete data
 */
public abstract class WritableTable<OBJ> extends ReadableTable<OBJ> implements FromExpression {

	public WritableTable() {}
	
	public WritableTable(String alias) {
		super(alias);
	}

}
