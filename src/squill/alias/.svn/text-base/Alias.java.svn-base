package squill.alias;

import squill.query.QueryContext;

/**
 * Manage table aliases.
 */
public class Alias {
  private static final String TABLE_ALIAS_PREFIX = "t";
  private static final String EXPRESSION_ALIAS_PREFIX = "e";
  private static final String COLUMN_ALIAS_PREFIX = "_c";  
  private static final int MAX_ALIAS_LENGTH = 26;
  
  private String prefix;
  private String resolved;
  
  private Alias(String alias, boolean unique) {
    if (unique)
      this.resolved = alias;      
    else
      this.prefix = alias;
  }
  
  public String resolve(QueryContext ctx) {
    if (resolved == null)
      resolved = ctx.uniqueAlias(prefix);
    return resolved;
  }
  
  public String toString() {
    throw new IllegalStateException("Aliases must be resolved first!");
  }

  public static Alias newAlias(String alias) {
    validateAlias(alias);
    return new Alias(alias, true);
  }
  
  public static Alias newTableAlias() {
    return new Alias(TABLE_ALIAS_PREFIX, false);
  }

  public static Alias newExpressionAlias() {
    return new Alias(EXPRESSION_ALIAS_PREFIX, false);
  }
  
  public static Alias newColumnAlias() {
    return new Alias(COLUMN_ALIAS_PREFIX, false);
  }

  public static void validateAlias(String alias) {
    if (alias == null || alias.length() == 0) {
      throw new IllegalArgumentException("Alias can not have zero-length!");
    }
    if (alias.matches("["+TABLE_ALIAS_PREFIX+EXPRESSION_ALIAS_PREFIX+"]\\d+")) {
      throw new IllegalArgumentException("Alias (" + alias + ") is a system alias.");
    }
    if (alias.length() > MAX_ALIAS_LENGTH) {
      throw new IllegalArgumentException("Alias (" + alias + ") too long, max 26 chars allowed!");
    }
    if (alias.matches("[a-zA-Z0-9_]*")) {
      if (!alias.matches("[a-zA-Z].*")) {
        throw new IllegalArgumentException("Alias (" + alias + ") should have character as first letter");
      }
    } else {
      throw new IllegalArgumentException("Alias (" + alias + ") should only consist of letters, numbers and underscore.");
    }
  }
  
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
    result = prime * result + ((resolved == null) ? 0 : resolved.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Alias other = (Alias) obj;
    if (prefix == null) {
      if (other.prefix != null)
        return false;
    } else if (!prefix.equals(other.prefix))
      return false;
    if (resolved == null) {
      if (other.resolved != null)
        return false;
    } else if (!resolved.equals(other.resolved))
      return false;
    return true;
  }
}

