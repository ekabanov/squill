package squill.mgen;

import java.sql.Types;

public class DbColumn {
	private String name;
	private Integer sqlType;
	private Integer size;
	private Integer scale; // number of decimal digits
	private boolean required;
	private boolean primary;
	private String defaultValue;
	private DbTable table;
	
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSqlType() {
		return sqlType;
	}
	
	public void setSqlType(Integer sqlType) {
		this.sqlType = sqlType;
	}
	
	public Integer getSize() {
		return size;
	}
	
	public void setSize(Integer size) {
		this.size = size;
	}
	
	public boolean hasSize() {
		return sqlType == Types.CHAR
				|| sqlType == Types.VARCHAR
				|| sqlType == Types.LONGVARCHAR
				|| sqlType == Types.NUMERIC
				|| sqlType == Types.DECIMAL;
	}
	
	public Integer getScale() {
		return scale;
	}
	
	public void setScale(Integer decimalDigits) {
		this.scale = decimalDigits;
	}
	
	public boolean hasScale() {
		return scale > 0 && (sqlType == Types.DECIMAL || sqlType == Types.NUMERIC);
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}
	
	public void setDefaultValue(String defValue) {
		this.defaultValue = defValue;
	}
	
	public DbColumn(DbTable table, String name) {
		this.setTable(table);
		this.name = name;
	}
	
	public boolean isRequired() {
		return required;
	}
	
	public void setRequired(boolean required) {
		this.required = required;
	}
	
	public void setNullType(Integer nullType) {
		this.required = (nullType == 0); // other values mean no or unknown
	}
	
	public void setTable(DbTable table) {
		this.table = table;
	}
	
	public DbTable getTable() {
		return table;
	}
	
	public String getLowerCaseName() {
		return getName().toLowerCase();
	}
}