package squill.query;

import java.util.List;


public interface QueryPart {
  /**
	 * Returns SQL String (containing "?" marks as argument place-holders).
	 *
	 * @return SQL String.
	 */
	String getDefaultSql();

	/**
	 * Returns SQL arguments.
	 *
	 * @return SQL arguments.
	 */
	List<Object> getSqlArguments();
	
	void setQueryContext(QueryContext ctx);
}
