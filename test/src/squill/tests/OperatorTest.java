package squill.tests;

import static junit.framework.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import squill.Squill;
import squill.builder.ResultBuilder;
import squill.db.Database;
import static squill.functions.Operations.*;
import squill.tuple.Tuple2;
import static squill.tuple.Tuple2.*;
import squill.model.CustomerData.CustomerTable;
import static squill.tests.TestUtil.*;

import static java.util.Arrays.*;
import java.util.Date;
import java.util.List;

public class OperatorTest {
  private Database database;
  private Squill squill;

  @Test
  public void simpleSelect() {
    CustomerTable cust = new CustomerTable();

    final ResultBuilder<Tuple2<String, Date>> result = squill
        .from(cust)
        .where(in(cust.id, 1, 2, 3))
        .orderBy(asc(cust.birthdate))
        .selectAs(concat(cust.firstName, " ", cust.lastName),
            cust.birthdate);
    result.getSql();
    List<Tuple2<String, Date>> clientTuple = result.list();

    assertEquals("result", asList(
        _("Tooth Fairy", date("0003-02-18")),
        _("The Devil", date("0666-06-06")),
        _("Sandy Claws", date("1888-08-08"))
    ), clientTuple);
  }

  @Before
  public void setUp() throws Exception {
    database = TestUtil.getDefaultHsqlDatabase();
    squill = new Squill(database);
  }

  @After
  public void tearDown() {
    TestUtil.shutDownHsql();
  }
}