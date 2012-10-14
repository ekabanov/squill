package squill.mgen;

import squill.mgen.naming.Conventions;
import squill.mgen.naming.NamingStrategy;
import squill.util.StringUtil;

public class MapForeignKey {
  private final NamingStrategy namingStrategy;
  private final DbTable table;
  private final DbForeignKey dbForeignKey;
  private final boolean unique;

  public MapForeignKey(NamingStrategy namingStrategy, DbTable table, DbForeignKey dbForeignKey, boolean unique) {
    this.namingStrategy = namingStrategy;
    this.table = table;
    this.dbForeignKey = dbForeignKey;
    this.unique = unique;
  }  
  
  public String getJoinInnerClassName() {
    return Conventions.javaTableName(namingStrategy, dbForeignKey.getRefTableName()) + "By" + getSourceColumnPropertyName();
  }
  
  public String getJoinFieldName() {
    return unique ? getTargetTableJavaName() : getTargetTableJavaName() + "By" + getSourceColumnPropertyName();
  }
  
  public String getTargetTableJavaName() {
    return StringUtil.decapitalize(namingStrategy.getTypeName(dbForeignKey.getRefTableName()));
  }
  
  public String getTargetTableJavaClassName() {
    return 
      Conventions.javaBeanName(namingStrategy, dbForeignKey.getRefTableName()) + 
      "." + 
      Conventions.javaTableName(namingStrategy, dbForeignKey.getRefTableName());
  }
  
  public String getSourceColumnPropertyName() {
    return StringUtil.capitalize(getSourceColumnName());
  }
  
  public String getSourceColumnName() {
    return namingStrategy.getFieldName(table.getName(), dbForeignKey.getColName());
  }
  
  public String getTargetColumnName() {
    return namingStrategy.getFieldName(table.getName(), dbForeignKey.getRefColName());
  }  
}
