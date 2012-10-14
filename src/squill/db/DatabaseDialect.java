package squill.db;

import squill.query.QueryContext;
import static squill.db.Database.closeIt;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author Michael Hunger
 * @since 07.04.2009
 */
public class DatabaseDialect {
    public static DatabaseDialect from(final Connection con) {
        final String className = con.getClass().getName();
        if (className.contains("hsqldb")) return new DatabaseDialect() {
            @Override
            protected <T> T executeAndReturnId(final Connection con, final String query, final List<?> args, final Map<String, Object> ctx, final StatementCallback<T> statementCallback) throws Exception {
                super.execute(con, query, args, ctx, statementCallback);
                Statement stmt = null;
                ResultSet keys = null;
                try {
                    stmt = con.createStatement();
                    keys = stmt.executeQuery("{ call IDENTITY() }");
                    if (keys.next()) {
                        return (T) keys.getObject(1);
                    } else {
                        throw new SQLException("Could not retrieve generated keys " + query + " " + args);
                    }
                } finally {
                    closeIt(null, stmt, keys);
                }
            }
        };
        if (className.contains("postgresql"))
            return new DatabaseDialect() {
                @Override
                protected <T> T executeAndReturnId(Connection con, String query, List<?> args, Map<String, Object> ctx, StatementCallback<T> statementCallback) throws Exception {
                    return super.execute(con, query+" RETURNING id", args, ctx, new SingleResultStatementCallback<T>());
                }
            };
        return new DatabaseDialect();
    }

    public <T> T execute(final Connection con, final String query, final List<?> args, final Map<String, Object> ctx, final QueryContext queryContext, final StatementCallback<T> statementCallback) throws Exception {
        if (queryContext.shallReturnId()) {
            return executeAndReturnId(con, query, args, ctx, statementCallback);
        }
        return execute(con, query, args, ctx, statementCallback);
    }

    protected <T> T execute(final Connection con, final String query, final List<?> args, final Map<String, Object> ctx, final StatementCallback<T> statementCallback) throws Exception {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(query);
            DataTypeUtil.setStatementParams(stmt, args, ctx);
            return statementCallback.execute(stmt);
        } finally {
            closeIt(null, stmt, null);
        }
    }

    protected <T> T executeAndReturnId(final Connection con, final String query, final List<?> args, final Map<String, Object> ctx, final StatementCallback<T> statementCallback) throws Exception {
        PreparedStatement stmt = null;
        ResultSet keys = null;
        try {
            stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            DataTypeUtil.setStatementParams(stmt, args, ctx);
            statementCallback.execute(stmt);
            keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return (T) keys.getObject(1);
            } else {
                throw new SQLException("Could not retrieve generated keys " + query + " " + args);
            }
        } finally {
            closeIt(null, stmt, keys);
        }
    }
}
