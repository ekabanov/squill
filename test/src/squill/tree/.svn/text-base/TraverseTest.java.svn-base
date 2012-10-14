package squill.tree;

import static junit.framework.Assert.*;
import org.junit.Test;
import static squill.functions.Operations.*;
import squill.tuple.Tuple2;
import squill.query.QueryPart;
import squill.tree.QueryPartHandler;
import squill.builder.ResultBuilder;
import static squill.Squill.squill;
import squill.db.Database;
import squill.model.CustomerData;
import squill.model.CustomerData.CustomerTable;

import java.util.Date;

public class TraverseTest {
  @Test public void testSimpleTraverse() {
    CustomerTable cust = new CustomerTable();

    ResultBuilder<Tuple2<String,Date>> result =
        squill((Database)null)
            .from(cust)
            .where(eq(cust.id, 1))
            .selectAs(concat(cust.firstName, " ", cust.lastName), cust.birthdate);

    final StringBuilder sb = new StringBuilder();
    result.traverse(new QueryPartHandler<StringBuilder>() {
      public void handle(final QueryPart queryPart, final StringBuilder sb) {
        sb.append(queryPart.getClass().getSimpleName()).append("\n");
      }
    }, sb);
    assertEquals("sql fragments","SelectPart\n" +
        "BinaryOperatorSelectExpression\n" +
        "Column\n" +
        "BinaryOperatorSelectExpression\n" +
        "\n" +
        "Column\n" +
        "Column\n" +
        "FromPart\n" +
        "CustomerTable\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "Column\n" +
        "WherePart\n" +
        "MultiOperatorBooleanExpression\n" +
        "Column\n" +
        "\n" +
        "OrderByPart\n",sb.toString());
  }
}
