package squill.db;

import java.sql.PreparedStatement;

/**
 * @author Michael Hunger
* @since 27.08.2008
*/
public class UpdateStatementCallback<T> implements StatementCallback<T> {
  public T execute(final PreparedStatement stmt) throws Exception {
    return (T)Integer.valueOf(stmt.executeUpdate());
  }
}
