package squill.mgen.naming;

/**
 * Naming strategy that does not change table / column names.
 * 
 * @author Juhan Aasaru
 * @since 31.08.2008
 */
public class SameNaming implements NamingStrategy {
	
	public String getTypeName(String dbTableName) {
		return dbTableName;
	}
	
	public String getFieldName(String dbTableName, String dbColumnName) {
		return dbColumnName;
	}
}
