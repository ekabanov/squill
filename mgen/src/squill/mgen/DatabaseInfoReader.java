package squill.mgen;

import java.sql.*;
import java.util.*;

/**
 * @author Michael Hunger
 * @since 27.08.2008
 */
public class DatabaseInfoReader {
	private final String driver;
	private final String url;
	private final String user;
	private final String password;
	private final String schema;
	private final MessageLogger logger;
	
	public DatabaseInfoReader(final String driver, final String url, final String user, final String password, final String schema, final MessageLogger logger) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
		this.schema = schema;
		this.logger = logger;
	}
	
	/**
	 * Read in the information about the underlying database.
	 * 
	 * @return List of tables
	 * @throws Exception
	 */
	public Collection<DbTable> gatherDatabaseInfo() throws Exception {
		
		Class.forName(driver); // Load Database driver
		info("Database driver init - OK");
		
		Collection<DbTable> dbTableList;
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
			info("Database connection - OK to " + url + " with user " + user);
			
			// Get the database Metadata.
			final DatabaseMetaData dbMetaData = con.getMetaData();
			
			// Get list of database tables and views
			dbTableList = getTables(dbMetaData);
			
			if (dbTableList.size() > 0) {
				info("Reading in columns, primary keys, foreign keys...");
				for (final DbTable curTable : dbTableList) {
					readTableColumns(dbMetaData, curTable);
					readTablePrimaryKeys(dbMetaData, curTable);
					readTableForeignKeys(dbMetaData, curTable);
				}
			} else {
				info("Skipping table processing because none was found");
			}
			
		} catch (SQLException e) {
			logger.error("Unable to connect to database URL=" + url + " with user=" + user + " and password=" + password, e);
			throw e;
		} finally {
			if (con != null) {
				con.close();
				con = null;
			}
		}
		return dbTableList;
	}
	
	private void info(final String message) {
		logger.info(message);
	}
	
	/**
	 * Get all the table names in the current database that are not system
	 * tables.
	 * 
	 * @param metaData JDBC metadata about database
	 * @return The list of all the tables in a database.
	 * @throws SQLException
	 */
	public List<DbTable> getTables(final DatabaseMetaData metaData) throws SQLException {
		info("Getting table list...");
		final List<DbTable> tables = new ArrayList<DbTable>();
		ResultSet tableNamesRs = null;
		// these are the entity types we want from the database
		final String[] types = { "TABLE", "VIEW" };
		try {
			tableNamesRs = metaData.getTables(null, schema, "%", types);
			
			while (tableNamesRs.next()) {
				final String name = tableNamesRs.getString("TABLE_NAME");
				final String type = tableNamesRs.getString("TABLE_TYPE");
				
				if (name.matches(".*[\\$=/].*")) { // skip temporary tables
					info("Skipping temporary table:" + name);
					continue;
				} else {
					info("Found table " + name);
				}
				final DbTable table = new DbTable(name, "VIEW".equalsIgnoreCase(type));
				tables.add(table);
			}
			info("Found " + tables.size() + " tables.");
		} finally {
			if (tableNamesRs != null) {
				tableNamesRs.close();
			}
		}
		return tables;
	}
	
	/**
	 * Read table column information
	 * 
	 * @param metaData Database Metadata
	 * @param table Table to obtain information about
	 * @throws SQLException
	 */
	public void readTableColumns(final DatabaseMetaData metaData, final DbTable table) throws SQLException {
		ResultSet columnRs = null;
		try {
			columnRs = metaData.getColumns(null, schema, table.getName(), null);
			while (columnRs.next()) {
				final String name = columnRs.getString("COLUMN_NAME");
				final Integer sqlType = columnRs.getInt("DATA_TYPE");
				final Integer size = columnRs.getInt("COLUMN_SIZE");
				final Integer decimalDigits = columnRs.getInt("DECIMAL_DIGITS");
				final Integer nullType = columnRs.getInt("NULLABLE");
				final String defValue = columnRs.getString("COLUMN_DEF");
				
				final DbColumn col = new DbColumn(table, name);
				col.setSqlType(sqlType);
				col.setSize(size);
				col.setScale(decimalDigits);
				col.setNullType(nullType);
				col.setDefaultValue(defValue);
				
				table.addColumn(col);
			}
		} finally {
			if (columnRs != null) {
				columnRs.close();
			}
		}
	}
	
	/**
	 * Retrieves a list of the columns composing the primary key of table
	 * 
	 * @param metaData Database metadata
	 * @param table Table
	 * @throws SQLException
	 */
	public void readTablePrimaryKeys(final DatabaseMetaData metaData, final DbTable table) throws SQLException {
		ResultSet primaryKeyRs = null;
		try {
			primaryKeyRs = metaData.getPrimaryKeys(null, schema, table.getName());
			while (primaryKeyRs.next()) {
				final String prk = (primaryKeyRs.getString("COLUMN_NAME"));
				info("Table " + table.getName() + " has primary key: " + prk);
				table.setPrimaryKey(prk);
			}
		} finally {
			if (primaryKeyRs != null) {
				primaryKeyRs.close();
			}
		}
	}
	
	/**
	 * Read in foreign keys of table
	 * 
	 * @param metaData Database metadata
	 * @param table Table object
	 * @throws SQLException
	 */
	public void readTableForeignKeys(final DatabaseMetaData metaData, final DbTable table) throws SQLException {
		final Collection<DbForeignKey> keys = new HashSet<DbForeignKey>();
		ResultSet foreignKeyRs = null;
		try {
			foreignKeyRs = metaData.getImportedKeys(null, schema, table.getName());
			while (foreignKeyRs.next()) {
				
				final DbForeignKey fKey = new DbForeignKey();
				
				fKey.setRefTableName(foreignKeyRs.getString("PKTABLE_NAME"));
				fKey.setKeyName(foreignKeyRs.getString("FK_NAME"));
				
				// if FK has no name - make it up (use refered table name
				// instead)
				if (fKey.getKeyName() == null) {
					fKey.setKeyName(fKey.getRefTableName());
				}
				
				fKey.setRefColName(foreignKeyRs.getString("PKCOLUMN_NAME"));
				fKey.setColName(foreignKeyRs.getString("FKCOLUMN_NAME"));
				keys.add(fKey);
				
				info("Table " + table.getName() + " has foreign key: " + fKey.getColName());
			}
		} catch (SQLException e) {
			logger.error("Could not table " + table.getName() + " foreign keys: ", e);
		} finally {
			if (foreignKeyRs != null) {
				foreignKeyRs.close();
			}
		}
		table.setForeignKeys(keys);
	}
}
