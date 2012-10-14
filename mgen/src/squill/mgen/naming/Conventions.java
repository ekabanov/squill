package squill.mgen.naming;

public class Conventions {
  public static String javaTableName(NamingStrategy namingStrategy, String tableName) {
    return namingStrategy.getTypeName(tableName) + "Table";
  }
  
  public static String javaBeanName(NamingStrategy namingStrategy, String tableName) {
    return namingStrategy.getTypeName(tableName) + "Data";
  }
}
