package squill;

import static squill.query.QueryContext.QueryType.*;

import javax.sql.DataSource;

import squill.builder.*;
import squill.db.Database;
import squill.query.QueryContextImpl;
import squill.query.from.FromExpression;
import squill.query.select.ReadableTable;
import squill.query.select.WritableTable;

/**
 * Base class, all Squill calls to database start from here.
 */
public class Squill {
	private final Database database;
	
	public Squill(Database database) {
		this.database = database;
	}
	
	public Squill(final DataSource dataSource) {
		this(new Database(dataSource));
	}
	
	public static Squill squill(final DataSource dataSource) {
		return new Squill(dataSource);
	}
	
	public static Squill squill(final Database database) {
		return new Squill(database);
	}
	
	// SELECT
	public SelectBuilder from(ReadableTable<?> baseTable, FromExpression... fromExprs) {
		return new SelectBuilder(new QueryContextImpl(SELECT), database, baseTable, fromExprs);
	}
	
	// TODO we could verify that all fields belong to this table
	/** UPDATE fields (not full object) */
	public <TBL> UpdateBuilder<TBL> update(WritableTable<TBL> table) {
		return new UpdateBuilder<TBL>(new QueryContextImpl(UPDATE), database, table);
	}
	
	/** INSERT fields (not full object) */
	public <TBL> InsertBuilder<TBL> insert(WritableTable<TBL> table) {
		return new InsertBuilder<TBL>(new QueryContextImpl(INSERT), database, table);
	}
	
	/** DELETE query */
	public <TBL> DeleteBuilder<TBL> delete(WritableTable<TBL> table) {
		return new DeleteBuilder<TBL>(new QueryContextImpl(DELETE), database, table);
	}
	
	/**
	 * Convenience method for updating full object
	 * 
	 * @param writableDataObject Object to be updated
	 */
	public void updateDataObject(WritableDataObject writableDataObject) {
		writableDataObject.update(this);
	}
	
	/**
	 * Convenience method for inserting full object
	 * 
	 * @param writableDataObject Object to be inserted
	 */
	public void insertDataObject(WritableDataObject writableDataObject) {
		writableDataObject.insert(this);
	}
	
	/**
	 * Convenience method for deleting full object
	 * 
	 * @param writableDataObject Object to be deleted
	 */
	public void deleteDataObject(WritableDataObject writableDataObject) {
		writableDataObject.delete(this);
	}
	
	/**
	 * Convenience method for selecting full object
	 * 
	 * @param clazz Class of the object to be selected
	 * @param id ID of the object to be selected
	 */
	public <T extends ReadableDataObject, V> T get(Class<T> clazz, V id) {
		try {
			return (T) clazz.getMethod("get", Squill.class, id.getClass()).invoke(null, this, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Squill addParam(String name, Object value) {
		database.addParam(name, value);
		return this;
	}
	
	/**
	 * A single value returning query without arguments that calls a procedure
	 * or selects from nonmapped table.
	 * 
	 * @param <T> Return type
	 * @param sql Full SQL
	 * @param expectedReturnType Expected returntype class
	 * @return Value of query
	 */
	public <T> T uncheckedQuery(String sql, Class<T> expectedReturnType) {
		return database.executeUncheckedQuery(sql, expectedReturnType);
	}
}
