package squill.mgen;

import static java.lang.String.format;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.resources.FileResource;

import squill.mgen.ClassInspectionUtil.ClassInfo;
import squill.mgen.naming.CamelCaseNaming;
import squill.mgen.naming.NamingStrategy;
import squill.mgen.naming.PropertyOverrideNamingStrategy;
import squill.util.FileUtil;

public class SquillMappingsTask extends Task implements MessageLogger {
	
	private static final String NAMING_STRATEGY_LOOKUP_PACKAGE = "squill.mgen.naming.";
	
	/**
	 * Database JDBC URL.
	 */
	private String url;
	
	/**
	 * Database driver.
	 */
	private String driver;
	
	/**
	 * Database user name.
	 */
	private String user;
	
	/**
	 * Database password
	 */
	private String password;
	
	/**
	 * Database schema
	 */
	private String schema;
	
	/**
	 * Package name for model objects
	 */
	private String packageName;
	
	/**
	 * Path to directory where to output files into
	 */
	private String outputPath;
	
	/**
	 * Path to the Velocity template file used for generating classes
	 */
	private String templateFile;
	
	/**
	 * The strategy for translating database table, view or column names into
	 * names of corresponding Squill mappings.
	 */
	private String namingStrategy;
	
	/**
	 * Path to a properties file which contains an optional mapping from the
	 * table/column names to class/filed names.
	 */
	private File namingOverride;
	/**
	 * Path to a properties file which contains exclude rules to exclude some
	 * tables/columns from generating mappings to them
	 */
	private File excludeRules;
	
	private Map<String, File> modelClassFiles = new HashMap<String, File>();
	
	public static URL[] classpathURLs;
	private static final String DEFAULT_STRATEGY_CLASS = CamelCaseNaming.class.getName();
	
	public void addConfiguredFileSet(FileSet fs) {
		for (Iterator<FileResource> i = fs.iterator(); i.hasNext();) {
			File file = i.next().getFile();
			if (file.getName().endsWith(".class")) {
				modelClassFiles.put(file.getName().substring(0, file.getName().length() - 6), file);
			}
		}
	}
	
	public static void main(String[] args) {
		SquillMappingsTask task = new SquillMappingsTask();
		task.setDriver("org.hsqldb.jdbcDriver");
		task.setUrl("jdbc:hsqldb:hsql://localhost/squilldemodb");
		task.setSchema("PUBLIC");
		task.setUser("SA");
		task.setPackageName("simple.data");
		task.setOutputPath("src");
		task.perform();
	}
	
	@Override
	public void execute() throws BuildException {
		info("Starting to generate Squill mappings out of database");
		
		try {
			long time = trace(null, 0L);
			DatabaseInfoReader databaseInfoReader = new DatabaseInfoReader(driver, url, user, password, schema, this);
			Collection<DbTable> tables = databaseInfoReader.gatherDatabaseInfo();
			time = trace("Gathering database information", time);
			if (tables.size() > 0) {
				generateMappings(tables, loadNamingStrategy(), loadExcludeRules());
				trace("Generating mappings", time);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException("Error creating mapping files..." + e, e);
		}
		
		info("Finished mappings task");
	}
	
	private Set<String> loadExcludeRules() {
		if (getExcludeRules() == null) {
			return null;
		}
		FileInputStream inStream = null;
		try {
			Properties exludeRulesProperties = new Properties();
			inStream = new FileInputStream(getExcludeRules());
			exludeRulesProperties.load(inStream);
			
			// Properties.stringPropertyNames() is not used to provide
			// compatibility with Java5
			Set<String> stringPropertyNames = new HashSet<String>();
			Enumeration<?> propertyNames = exludeRulesProperties.propertyNames();
			
			while (propertyNames.hasMoreElements()) {
				String name = (String) propertyNames.nextElement();
				stringPropertyNames.add(name.toLowerCase());
			}
			
			return stringPropertyNames;
		} catch (IOException e) {
			throw new RuntimeException("Could not load exclude rules properties from '" + getExcludeRules() + "'", e);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				throw new IllegalStateException("Error closing stream ", e);
			}
		}
	}
	
	private long trace(final String msg, final long time) {
		if (msg != null && time != 0L) {
			final long delta = System.nanoTime() - time;
			log(format("%s took %.2f ms.", msg, (double) delta / (1000 * 1000)), Project.MSG_INFO);
		}
		return System.nanoTime();
	}
	
	/**
	 * @return Class that provides conversion between database names and type
	 *         names
	 */
	@SuppressWarnings("unchecked")
	private NamingStrategy loadNamingStrategy() {
		String strategyClassName = getStrategyClassName();
		return strategyInstance(strategyClassName);
	}
	
	@SuppressWarnings("unchecked")
	private NamingStrategy strategyInstance(final String strategyClassName) {
		try {
			final Class<? extends NamingStrategy> strategyClass = (Class<? extends NamingStrategy>) Class.forName(strategyClassName);
			return addOverrides(strategyClass.newInstance());
		} catch (Exception e) {
			error("Error", e);
			throw new BuildException(format("Problem loading naming strategy '%s' from class '%s'.", getNamingStrategy(), strategyClassName), e);
		}
	}
	
	private String getStrategyClassName() {
		final String strategyName = getNamingStrategy();
		String strategyClassName = DEFAULT_STRATEGY_CLASS;
		if (strategyName != null && strategyName.trim().length() > 0) {
			strategyClassName = strategyName.contains(".") ? strategyName : NAMING_STRATEGY_LOOKUP_PACKAGE + strategyName;
		}
		return strategyClassName;
	}
	
	private NamingStrategy addOverrides(NamingStrategy strategy) {
		if (getNamingOverride() == null) {
			return strategy;
		}
		FileInputStream inStream = null;
		try {
			Properties namingOverrideProperties = new Properties();
			inStream = new FileInputStream(getNamingOverride());
			namingOverrideProperties.load(inStream);
			return new PropertyOverrideNamingStrategy(strategy, namingOverrideProperties);
		} catch (IOException e) {
			throw new RuntimeException("Could not load naming override properties from '" + getNamingOverride() + "'", e);
		} finally {
			try {
				if (inStream != null) {
					inStream.close();
				}
			} catch (IOException e) {
				throw new IllegalStateException("Error closing stream ", e);
			}
		}
	}
	
	/**
	 * Generate mapping files based on the database information
	 * 
	 * @param dbTableList List of database tables/views
	 * @param excludeRules Set of exclude rules
	 */
	public void generateMappings(Collection<DbTable> dbTableList, NamingStrategy namingStrategy, Set<String> excludeRules) {
		info("Using naming strategy: " + namingStrategy.getClass());
		TableCodeGenerator codeGenerator = new VelocityTableCodeGenerator(getTemplateFile());
		final String packageName = getPackageName();
		
		for (DbTable curTable : dbTableList) {
			
			info("Processing table: " + curTable.getName());
			
			if (excludeRules == null || !excludeRules.contains(curTable.getName().toLowerCase())) {
				long time = trace("generate java code", 0L);
				
				ClassInfo modelClass = null;
				String modelTypeName = namingStrategy.getTypeName(curTable.getName());
				info("Searching for the model " + modelTypeName);
				if (modelClassFiles.containsKey(modelTypeName)) {
					modelClass = ClassInspectionUtil.inspectClass(modelClassFiles, modelTypeName);
					info("Found corresponding model file: " + modelClass.getName());
				}
				
				MapTable mapTable = new MapTable(curTable, namingStrategy, modelClass, excludeRules, this); // Mappings
				// container
				
				String tableJavaCode = codeGenerator.generateJavaTableCode(packageName, mapTable);
				time = trace("generate java code", time);
				FileUtil.writeFile(tableJavaCode, FileUtil.javaFile(getOutputPath(), getPackageName(), mapTable.getJavaName()));
				trace("write file", time);
			} else {
				info("Skipping table " + curTable.getName() + " because of the exclude rules.");
			}
		}
	}
	
	public String getTemplateFile() {
		if (templateFile != null) {
			return templateFile;
		}
		return FileUtil.path(getClass()) + "Model";
	}
	
	public void setTemplateFile(final String templateFile) {
		this.templateFile = templateFile;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public String getOutputPath() {
		String lastChar = outputPath.substring(outputPath.length() - 1, outputPath.length());
		
		if ("${output.path}".equalsIgnoreCase(outputPath)) {
			return "output/";
		} else if ("/".equals(lastChar) || "\\".equals(lastChar)) {
			return outputPath;
		}
		return outputPath + "/";
		
	}
	
	public String getPackageName() {
		if ("${java.package}".equals(packageName) || (this.packageName != null && this.packageName.trim().length() == 0)) {
			return null;
		} else {
			return packageName;
		}
	}
	
	public void warning(String message) {
		log(message, Project.MSG_WARN);
	}
	
	public void info(String message) {
		log(message, Project.MSG_INFO);
	}
	
	public void error(String message, Throwable t) {
		log(message, t, Project.MSG_ERR);
	}
	
	public String getNamingStrategy() {
		return namingStrategy;
	}
	
	public void setNamingStrategy(String namingStrategy) {
		this.namingStrategy = namingStrategy;
	}
	
	public File getNamingOverride() {
		return namingOverride;
	}
	
	public void setNamingOverride(File namingOverride) {
		this.namingOverride = namingOverride;
	}
	
	public void setExcludeRules(File excludeRules) {
		this.excludeRules = excludeRules;
	}
	
	public File getExcludeRules() {
		return excludeRules;
	}
}
