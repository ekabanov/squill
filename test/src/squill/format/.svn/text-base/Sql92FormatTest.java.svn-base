package squill.format;

import org.junit.Test;import static org.junit.Assert.assertEquals;
import static squill.Squill.*;
import squill.builder.ResultBuilder;
import squill.format.Sql92Format;
import squill.format.SqlFormat;
import squill.tuple.Tuple1;
import squill.Squill;
import static squill.functions.Operations.asc;
import static squill.functions.Operations.eq;
import static squill.tests.TestUtil.assertRegexp;
import squill.model.CustomerData;

import javax.sql.DataSource;

public class Sql92FormatTest {
  private static final SqlFormat FORMAT = new Sql92Format();
  private final Squill squill = squill((DataSource) null);
  private final CustomerData.CustomerTable cust = new CustomerData.CustomerTable();

  @Test public void testFormatSimpleSql() {
    final ResultBuilder<Tuple1<Integer>> result = squill.from(cust).selectAs(cust.id);
    assertRegexp("simple select", "SELECT t(\\d+)\\.ID AS t\\1_c\\d+ FROM customer t\\1", result.getSql(FORMAT).trim());
    assertEqualsIgnoreWhiteSpace("simple select",result.getSql(),result.getSql(FORMAT));
  }

  private static void assertEqualsIgnoreWhiteSpace(final String msg, final String expected, final String actual) {
    assertEquals(msg,expected,actual.trim().replaceAll("\\s+"," "));
  }

  @Test public void testFormatWhereSql() {
    final ResultBuilder<Tuple1<Integer>> result = squill.from(cust).where(eq(cust.id,10)).selectAs(cust.id);
    assertRegexp("simple select", "SELECT t(\\d+)\\.ID AS t\\1_c\\d+ FROM customer t\\1 WHERE \\(t\\1.ID = \\?\\)", result.getSql(FORMAT).trim());
  }
  @Test public void testFormatOrderedSql() {
    final ResultBuilder<Tuple1<Integer>> result = squill.from(cust).orderBy(asc(cust.code)).selectAs(cust.id);
    assertRegexp("simple select", "SELECT t(\\d+)\\.ID AS t\\1_c\\d+ FROM customer t\\1 ORDER BY t\\1.CODE ASC", result.getSql(FORMAT).trim());
  }
}
