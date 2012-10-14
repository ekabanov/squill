package squill.mgen;

import java.util.*;

import squill.mgen.ClassInspectionUtil.ClassInfo;
import squill.mgen.naming.Conventions;
import squill.mgen.naming.NamingStrategy;

public class MapTable {
	
	private final DbTable table;
	private Map<String, MapColumn> mapColumns;
	private NamingStrategy namingStrategy;
	private Set<String> excludeRules;
	private ClassInfo modelClass;
	private List<MapForeignKey> foreignKeys = new ArrayList<MapForeignKey>();
	private final MessageLogger logger;
	
	public MapTable(DbTable table, NamingStrategy namingStrategy, ClassInfo modelClass, Set<String> excludeRules, MessageLogger logger) {
		this.table = table;
		this.namingStrategy = namingStrategy;
		this.modelClass = modelClass;
		this.excludeRules = excludeRules;
		this.logger = logger;
		
		if (table.getForeignKeys() != null) {
			// Determine unique keys
			Set<String> targetTablesOnce = new HashSet<String>();
			Set<String> targetTablesMany = new HashSet<String>();
			
			for (DbForeignKey fk : table.getForeignKeys()) {
				if (targetTablesOnce.contains(fk.getRefTableName())) {
					targetTablesMany.add(fk.getRefTableName());
				}
				
				if (!targetTablesMany.contains(fk.getRefTableName())) {
					targetTablesOnce.add(fk.getRefTableName());
				} else {
					targetTablesOnce.remove(fk.getRefTableName());
				}
			}
			
			for (DbForeignKey fk : table.getForeignKeys()) {
				if (excludeRules == null || !excludeRules.contains("." + fk.getColName().toLowerCase())
						&& !excludeRules.contains("." + fk.getRefColName().toLowerCase())
						&& !excludeRules.contains(table.getName().toLowerCase() + "." + fk.getColName().toLowerCase())
						&& !excludeRules.contains(fk.getRefTableName().toLowerCase() + "." + fk.getRefColName().toLowerCase())
						&& !excludeRules.contains(fk.getRefTableName().toLowerCase())) {
					foreignKeys.add(new MapForeignKey(namingStrategy, table, fk, targetTablesOnce.contains(fk.getRefTableName())));
				}
			}
		}
	}
	
	public String getJavaName() {
		return Conventions.javaBeanName(namingStrategy, table.getName());
	}
	
	/** @return Name of reference object */
	public String getRefName() {
		return Conventions.javaTableName(namingStrategy, table.getName());
	}
	
	public boolean hasModelClass() {
		return modelClass != null;
	}
	
	public String getModelClassName() {
		return modelClass.getName();
	}
	
	public boolean isReadOnly() {
		return table.isReadOnly();
	}
	
	public String getName() {
		return table.getName();
	}
	
	public String getTableName() {
		return getName().toLowerCase();
	}
	
	public boolean hasPrimaryKey() {
		return table.getPrimaryKeyColnames().size() == 1;
	}
	
	public MapColumn getPrimaryKey() {
		return mapColumns.get(table.getPrimaryKeyColnames().iterator().next());
	}
	
	public List<MapColumn> getNonPrimaryColumns() {
		List<MapColumn> result = new ArrayList<MapColumn>();
		
		for (MapColumn mapColumn : getColumns().values()) {
			if (hasPrimaryKey() && getPrimaryKey() != mapColumn) {
				result.add(mapColumn);
			}
		}
		
		return result;
	}
	
	public List<MapForeignKey> getForeignKeys() {
		return foreignKeys;
	}
	
	public Map<String, MapColumn> getColumns() {
		try {
			if (mapColumns != null) {
				return mapColumns;
			}
			mapColumns = createMapColumns();
			return mapColumns;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	private Map<String, MapColumn> createMapColumns() {
		final Collection<DbColumn> tableColumns = table.getColumns();
		Map<String, MapColumn> columns = new HashMap<String, MapColumn>();
		for (DbColumn tableColumn : tableColumns) {
			if (excludeRules == null || !excludeRules.contains("." + tableColumn.getLowerCaseName())
					&& !excludeRules.contains(getTableName() + "." + tableColumn.getLowerCaseName())) {
				columns.put(tableColumn.getName(), new MapColumn(tableColumn, namingStrategy, modelClass));
			} else {
				logger.info("Skipping column " + tableColumn.getName() + " because of the exclude rules.");
			}
		}
		return columns;
	}
	
	public void setExcludeRules(Set<String> excludeRules) {
		this.excludeRules = excludeRules;
	}
	
	public Set<String> getExcludeRules() {
		return excludeRules;
	}
}
