package squill.mgen.naming;

/**
 * Type names are converted into lowercase and names of database tables and
 * columns are held out in upper case.
 * 
 * @author Juhan Aasaru
 * @since 31.08.2008
 */
public class LowerCaseNaming implements NamingStrategy {
	public String getTypeName(String dbTableName) {
		return dbTableName.toLowerCase();
	}
	
	public String getFieldName(String dbTableName, String dbColumnName) {
		return dbColumnName.toLowerCase();
	}
}