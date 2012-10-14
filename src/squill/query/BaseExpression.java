package squill.query;

import java.util.Collections;
import java.util.List;


/**
 * Base implementation of the {@link Expression}.
 * 
 * @see Expression
 */
public abstract class BaseExpression<T> implements Expression<T>{
  private QueryContext ctx;
  
  public void setQueryContext(QueryContext ctx) {
    this.ctx = ctx;
  }

  protected QueryContext getContext() {
    return ctx;
  }
  
	public String getDefaultSql() {
		return null;
	}

	public List<Object> getSqlArguments() {
		return Collections.EMPTY_LIST;
	}

}
