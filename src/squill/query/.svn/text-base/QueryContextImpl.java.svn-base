package squill.query;

import squill.alias.AliasResolver;

public class QueryContextImpl implements QueryContext {
    private final AliasResolver ar = new AliasResolver();
    private final QueryType queryType;
    private boolean returnId;

    public QueryContextImpl(QueryType queryType) {
        this.queryType = queryType;
    }

    public String uniqueAlias(String prefix) {
        return ar.uniqueAlias(prefix);
    }

    public QueryType type() {
        return queryType;
    }

    public void setReturnId() {
        returnId = true;
    }

    public boolean shallReturnId() {
        return returnId;
    }
}
