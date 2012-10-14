package squill.query.select;

import squill.alias.Alias;

/**
 * Implementation of the named select expression.
 */
public class SimpleSelectExpression<T> extends BaseSelectExpression<T> {

	private final Alias alias;
	private final Class<T> type;

  public SimpleSelectExpression(String alias, Class<T> type) {
    if (alias == null || alias.trim().length() == 0) {
      this.alias = Alias.newExpressionAlias();
    } else {
      this.alias = Alias.newAlias(alias);
    }
    this.type = type;
  }

  public SimpleSelectExpression(Class<T> type) {
    this(null,type);
	}

	public String getAlias() {
		return alias.resolve(getContext());
	}

	public String getAsAliasSql() {
		return getDefaultSql() + " AS " + getAlias();
	}

  public Class<T> getTableType() {
		return type;
	}

}
