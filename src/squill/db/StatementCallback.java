package squill.db;

import java.sql.PreparedStatement;

public interface StatementCallback<T> {
  T execute(PreparedStatement stmt) throws Exception;
}
