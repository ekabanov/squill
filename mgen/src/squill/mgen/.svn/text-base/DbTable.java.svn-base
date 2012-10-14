package squill.mgen;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DbTable {

	private Map<String,DbColumn> columns = new HashMap<String,DbColumn>();
	private String name;
	private boolean readOnly; // ie is it a view?
	
	private Set<String> primaryKeyColnames = new HashSet<String>();
	
	Collection<DbForeignKey> foreignKeys;

	public DbTable(String name, boolean readOnly) {
		this.name = name;
		this.readOnly = readOnly;
	}

	public void addColumn(DbColumn column) {
		columns.put(column.getName(),column);
	}

	public String getName() {
		return name;
	}

	public DbColumn getColumn(String name) {
		return columns.get(name);
	}
	
	public Collection<DbColumn> getColumns() {
		return columns.values();
	}
	
	public void setPrimaryKey(String columnName) {
		primaryKeyColnames.add(columnName);
	}

	public Collection<DbForeignKey> getForeignKeys() {
		return foreignKeys;
	}

	public void setForeignKeys(Collection<DbForeignKey> foreignKeys) {
		this.foreignKeys = foreignKeys;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

  public Set<String> getPrimaryKeyColnames() {
    return primaryKeyColnames;
  }
 
}
