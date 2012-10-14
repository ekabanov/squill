package squill.mgen.naming;

/**
 * Interface for translating database table, view or column names into names of
 * corresponding Squill mappings Implementations may replace underscores with
 * CamelCase and plural table names with singular names.
 * 
 * @author Juhan Aasaru
 * @since 31.08.2008
 */
public interface NamingStrategy {
	
	/**
	 * @param tableName Name of table in database
	 * @return Name of Java type to represent this table
	 */
	String getTypeName(String dbTableName);
	
	/**
	 * @param dbTableName Table name in case field name should depend on it
	 * @param dbColumnName Column name in database.
	 * @return Name of column in Squill mapping
	 */
	String getFieldName(String dbTableName, String dbColumnName);
	
}
