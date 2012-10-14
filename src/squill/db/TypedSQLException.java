package squill.db;

import java.sql.SQLException;

/**
 * Thrown when Database raises SQLException in TypedSQL code
 */
public class TypedSQLException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TypedSQLException(String message) {
		super(message);
	}

	public TypedSQLException(SQLException sqlEx) {
		super(sqlEx.getMessage());
	}

}
