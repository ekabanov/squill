package squill.tests;

import static junit.framework.Assert.assertEquals;
import static squill.Squill.squill;
import static squill.functions.Operations.concat;
import static squill.functions.Operations.eq;
import static squill.functions.Operations.param;
import static squill.tuple.Tuple2._;
import static squill.tests.TestUtil.date;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import squill.db.Database;
import squill.tuple.Tuple2;
import squill.model.CustomerData.CustomerTable;

public class ParameterTest { 
  private Database database;

  @Test
  public void simpleParam() {
    CustomerTable cust = new CustomerTable();

    Tuple2<String, Date> clientTuple =
        squill(database)
            .from(cust)
            .where(eq(cust.id, param("id",Integer.class)))
            .selectAs(concat(cust.firstName, " ", cust.lastName), cust.birthdate)
            .resolve("id", 1)
            .tuple();

    assertEquals("result", _("Tooth Fairy", date("0003-02-18")), clientTuple);
  }

  @Test
  public void multiParam() {
    CustomerTable cust = new CustomerTable();

    Tuple2<String, Date> clientTuple =
        squill(database)
            .from(cust)
            .where(eq(cust.id, param("id",Integer.class)),
                eq(cust.firstName,param("name",String.class)))
            .selectAs(concat(cust.firstName, " ", cust.lastName), cust.birthdate)
            .resolve(_("id", 1),_("name","Tooth"))
            .tuple();

    assertEquals("result", _("Tooth Fairy", date("0003-02-18")), clientTuple);
  }

  @After
  public void tearDown() {
    TestUtil.shutDownHsql();
  }

  @Before
  public void setUp() throws Exception {
    database = TestUtil.getDefaultHsqlDatabase();
  }
}