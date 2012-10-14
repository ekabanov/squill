package squill.mgen.naming;

import java.util.Properties;

/**
 * Naming strategy to be used when an optional namingoverride property file is
 * defined. It looks to the property file first and in case nothing was found it
 * delegates the naming to another strategy
 */
public class PropertyOverrideNamingStrategy implements NamingStrategy {
	private final NamingStrategy delegate;
	private final Properties dbToJavaProps;
	
	public PropertyOverrideNamingStrategy(NamingStrategy result, Properties dbToJavaProps) {
		this.delegate = result;
		this.dbToJavaProps = dbToJavaProps;
	}
	
	public String getFieldName(String dbTableName, String dbColumnName) {
		String dbFullColumnName = key(dbTableName, dbColumnName);
		if (dbToJavaProps.containsKey(dbFullColumnName)) {
			return dbToJavaProps.getProperty(dbFullColumnName);
		}
		
		return delegate.getFieldName(dbTableName, dbColumnName);
	}
	
	private String key(final String dbTableName, final String dbColumnName) {
		return dbTableName.concat(".").concat(dbColumnName);
	}
	
	public String getTypeName(String dbTableName) {
		if (dbToJavaProps.containsKey(dbTableName)) {
			return dbToJavaProps.getProperty(dbTableName);
		}
		
		return delegate.getTypeName(dbTableName);
	}
}
