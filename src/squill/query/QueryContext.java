package squill.query;

public interface QueryContext {
    void setReturnId();

    boolean shallReturnId();

    enum QueryType {
        SELECT, INSERT, UPDATE, UNKNOWN, DELETE
    }
    String uniqueAlias(String prefix);
    QueryType type();
}
