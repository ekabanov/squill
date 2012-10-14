package squill.mgen.naming;

/**
 * Naming strategy where Java type names are in singular, but database table
 * names are in plural. Conversion from plural to singular is made by Inflector
 * from JBoss DNA.
 * 
 * @author Juhan Aasaru
 * @author Jevgeni Martjushev
 * @since 31.08.2008
 */
public class PluralCamelCaseNaming implements NamingStrategy {
	private CamelCaseNaming delegate = new CamelCaseNaming();
	
	public String getFieldName(String dbTableName, String dbColumnName) {
		return delegate.getFieldName(dbTableName, dbColumnName);
	}
	
	public String getTypeName(String dbTableName) {
		Inflector inflector = Inflector.getInstance();
		String singularDbTableName = inflector.singularize(dbTableName);
		return delegate.getTypeName(singularDbTableName);
	}
}
