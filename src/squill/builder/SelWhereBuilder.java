package squill.builder;

import squill.db.Database;
import squill.query.QueryContext;
import squill.query.orderby.OrderByElement;


/**
 * WHERE Builder object used in SELECT clause.
 * NB! For UPDATE clause there is separate WHERE-clause
 *
 * @see squill.builder.UpWhereBuilder
 */
public class SelWhereBuilder extends OrderByBuilder {

    protected SelWhereBuilder(QueryContext ctx, Database database) {
        super(ctx, database);
    }

    public OrderByBuilder orderBy(OrderByElement... elements) {
        super.setOrderbyList(elements);
        return this;
    }

}
