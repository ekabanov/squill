package squill.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Michael Hunger
* @since 08.04.2009
*/
class SingleResultStatementCallback<T> implements StatementCallback<T> {
    public T execute(final PreparedStatement stmt) throws Exception {
      final ResultSet rs = stmt.executeQuery();
      rs.next();
      return (T)rs.getObject(1);
    }
}
