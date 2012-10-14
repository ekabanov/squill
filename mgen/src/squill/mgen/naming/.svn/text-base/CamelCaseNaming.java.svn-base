package squill.mgen.naming;

import squill.util.StringUtil;

/**
 * CamelCase naming where words are separated with underscores in
 * database and upper case letters in Java mappings.
 * 
 * @author Juhan Aasaru
 * @since 31.08.2008
 */
public class CamelCaseNaming implements NamingStrategy {

  public String getTypeName(String dbTableName) {
    return StringUtil.toUpperCamelCase(dbTableName);
  }

  public String getFieldName(String dbTableName, String dbColumnName) {
    return StringUtil.toLowerCamelCase(dbColumnName);
  }
}
