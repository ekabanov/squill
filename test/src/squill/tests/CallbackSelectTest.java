package squill.tests;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static squill.functions.Operations.concat;
import static squill.functions.Operations.eq;
import static squill.tuple.Tuple2._;
import static squill.tests.TestUtil.date;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import squill.Squill;
import squill.builder.ResultBuilder;
import squill.callback.ResultCallback;
import squill.db.Database;
import squill.tuple.Tuple1;
import squill.tuple.Tuple2;
import squill.model.CustomerData.CustomerTable;

public class CallbackSelectTest {
  private Squill squill;
  private static final Tuple2<String, Date> EXPECTED = _("Tooth Fairy", date("0003-02-18"));
  private final CustomerTable customer = new CustomerTable();

  @Test
  public void callbackSelect2() {
    List<Void> result =
        squill
            .from(customer)
            .where(eq(customer.id, 1))
            .selectCallback(concat(customer.firstName, " ", customer.lastName), customer.birthdate,
                new ResultCallback<Tuple2<String, Date>, Void>() {

                  public Void handle(Tuple2<String, Date> tuple, Status status) {
                    assertEquals(EXPECTED, tuple);
                    assertEquals(0, status.getRow());
                    return null;
                  }
                });

    assertEquals("result", 1, result.size());
  }

  @Test
  public void callbackSelect1() {
    List<Void> result =
        squill
            .from(customer)
            .where(eq(customer.id, 1))
            .selectCallback(concat(customer.firstName, " ", customer.lastName),
                new ResultCallback<String, Void>() {

                  public Void handle(String name, Status status) {
                    assertEquals(EXPECTED.v1, name);
                    assertEquals(0, status.getRow());
                    return null;
                  }
                });

    assertEquals("result", 1, result.size());
  }

  @Test
  public void selectAs() {
    List<Void> result =
        squill
            .from(customer)
            .where(eq(customer.id, 1))
            .selectAs(concat(customer.firstName, " ", customer.lastName),
                customer.birthdate).map(
            new ResultCallback<Tuple2<String, Date>, Void>() {
              public Void handle(Tuple2<String, Date> tuple, Status status) {
                assertEquals(EXPECTED, tuple);
                assertEquals(0, status.getRow());
                return null;
              }
            });

    assertEquals("result", 1, result.size());
  }

  @Test
  public void multipleSelectAs() {
    final ResultBuilder<Tuple2<String,Date>> select = squill
        .from(customer)
        .where(eq(customer.id, 1))
        .selectAs(concat(customer.firstName, " ", customer.lastName), customer.birthdate);

    // multiple usages
    assertEquals("count",1,select.count());
    assertEquals("tuple", EXPECTED,select.tuple());
    assertEquals("list",asList(EXPECTED),select.list());
    TestUtil.assertRegexp("sql","SELECT t0.FIRST_NAME \\|\\| ' ' \\|\\| t0.LAST_NAME AS e0, t0.BIRTHDATE AS t0_c0 FROM customer t0 WHERE \\(t0.ID = \\?\\)",select.toString());

    List<Void> callbackResult =
        select.map(
            new ResultCallback<Tuple2<String, Date>, Void>() {
              public Void handle(Tuple2<String, Date> tuple, Status status) {
                assertEquals(EXPECTED, tuple);
                assertEquals(0, status.getRow());
                return null;
              }
            });
    assertEquals("callback", 1, callbackResult.size());
  }


  @Test
  public void abortFetch() {
    final ResultBuilder<Tuple1<String>> select = squill
        .from(customer)
        .where()
        .selectAs(customer.lastName);
    final List<String> result = select.map(new ResultCallback<Tuple1<String>, String>() {
      public String handle(final Tuple1<String> tuple, final Status status) {
        if (status.getRow()==1) status.abort();
        return tuple.v1;
      }
    });
    assertEquals("count not 4",2,result.size());
    assertEquals(asList("Fairy","Devil"),result);
    assertEquals("count ",4,select.count());
  }


  @Before
  public void setUp() throws Exception {
    final Database database = TestUtil.getDefaultHsqlDatabase();
    squill = new Squill(database);
  }

  @After
  public void tearDown() {
    TestUtil.shutDownHsql();
  }
}