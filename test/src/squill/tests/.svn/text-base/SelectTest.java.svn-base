package squill.tests;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static squill.functions.Operations.and;
import static squill.functions.Operations.asc;
import static squill.functions.Operations.concat;
import static squill.functions.Operations.desc;
import static squill.functions.Operations.eq;
import static squill.functions.Operations.ge;
import static squill.functions.Operations.gt;
import static squill.functions.Operations.join;
import static squill.functions.Operations.notNull;
import static squill.functions.Operations.nvl;
import static squill.functions.Operations.unchecked;
import static squill.tests.TestUtil.DAY_FORMAT;
import static squill.tests.TestUtil.date;
import static squill.tuple.Tuple2._;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import squill.Squill;
import squill.db.Database;
import squill.model.CustomerData;
import squill.model.ComplaintData.ComplaintTable;
import squill.model.CustomerData.CustomerTable;
import squill.query.select.SelectExpression;
import squill.query.where.WhereExpression;
import squill.tuple.Mapper;
import squill.tuple.Tuple2;

public class SelectTest {
  private Database database;
  private Squill squill;

  @Test
  public void simpleSelect() {
    CustomerTable cust = new CustomerTable();

    Tuple2<String, Date> clientTuple =
        squill
            .from(cust)
            .where(eq(cust.id, 1))
            .orderBy(asc(cust.birthdate))
            .select(concat(cust.firstName, " ", cust.lastName),
                cust.birthdate);

    assertEquals("result", _("Tooth Fairy", date("0003-02-18")), clientTuple);
  }

  @Test public void dynamicSelect() {
    CustomerTable cust = new CustomerTable("customer");

    WhereExpression whereClause = ge(cust.discount, 20);

    whereClause = and(whereClause,
        eq(cust.isActive, 1));

    List<String> clientList = squill
        .from(cust)
        .where(whereClause)
        .orderBy(desc(cust.firstName))
        .selectList(cust.firstName);
    assertEquals("active clients with discounts >= 20",asList("The", "Sandy", "North"),clientList);
  }

  @Test public void likeOrderBy() throws ParseException {
        CustomerTable cust = new CustomerTable("customer");

		List<Tuple2<String, Date>> clients =
        squill
            .from(cust)
            .where(
                cust.firstName.like("T%"))
            .orderBy(cust.lastName.asc(),
                desc(cust.birthdate))
            .selectList(
                cust.firstName,
                cust.birthdate);

     assertEquals("sorted and like T%",asList(_("The",DAY_FORMAT.parse("0666-06-06")),_("Tooth", DAY_FORMAT.parse("0003-02-18"))),clients);
  }
  @Test
  public void joins() {
    ComplaintTable comp = new ComplaintTable();
    CustomerTable cust = new CustomerTable();

    List<Tuple2<String, Integer>> resultList =
        squill
            .from(comp).join(cust, cust.id, comp.customerId)
            .where(gt(cust.isActive, 0), notNull(comp.percentSolved), notNull(comp.refoundSum))
            .orderBy(desc(cust.id))
            .selectList(
                cust.lastName,
                comp.percentSolved);

    assertEquals("Result list size", 6, resultList.size());
    assertEquals("Result",
        asList(_("Elf", 55), _("Claws", 85), _("Devil", 10), _("Devil", 75), _("Fairy", 15), _("Fairy", 100)),
        resultList);
  }

  @Test
  public void tableAliases() {
    // Table aliases example

    CustomerTable adviser = new CustomerTable();
    CustomerTable newcomer = new CustomerTable();

    List<Tuple2<Integer, String>> resultList = squill
        .from(newcomer,
            join(adviser, adviser.id, newcomer.parentCustomerId))
        .orderBy(asc(newcomer.firstName))
        .selectList(
            newcomer.code,
            concat(adviser.firstName, " ", adviser.lastName));

    assertEquals("Result list size", 1, resultList.size());
    assertEquals("Result", asList(_(757, "Sandy Claws")), resultList);
  }

  @Test
  public void cruAndOrder() {

    ComplaintTable compl = new ComplaintTable();
    CustomerTable cust = new CustomerTable();

    SelectExpression<Integer> solved =
        nvl(compl.percentSolved, 0);

    List<Tuple2<CustomerData, Integer>> res =
        squill
            .from(compl,
                join(cust, cust.id, compl.customerId))
            .orderBy(asc(solved))
            .selectList(
                cust,
                solved);

    assertEquals("Result list size", 6, res.size());

    Integer[] expected = {10, 15, 55, 75, 85, 100};

    List<Integer> mapped = Mapper.transform(res, new Mapper<Tuple2<CustomerData, Integer>, Integer>() {
      @Override
	public Integer map(Tuple2<CustomerData, Integer> tuple) {
        return tuple.v2;
      }
    });
    assertEquals("result", asList(expected), mapped);
  }

  @Test
  public void selectCountAndSubquery() {
    ComplaintTable compl = new ComplaintTable();
    CustomerTable cust = new CustomerTable();

    SelectExpression<Integer> subQuery =
        new Squill(database)
            .from(compl)
            .where(eq(compl.customerId, cust.id))
            .subSelect(unchecked(Integer.class, "count(*)"));

    List<Tuple2<CustomerData, Integer>> res =
        squill
            .from(cust)
            .orderBy(desc(subQuery), asc(cust.id))
            .selectList(cust, subQuery);

    assertEquals("Result list size", 4, res.size());
    List<Tuple2<Integer, Integer>> mapped = Mapper.transform(res, new Mapper<Tuple2<CustomerData, Integer>, Tuple2<Integer, Integer>>() {
      @Override
	public Tuple2<Integer, Integer> map(Tuple2<CustomerData, Integer> tuple) {
        return _(tuple.v1.getId(), tuple.v2);
      }
    });
    assertEquals("Result", asList(_(1, 2), _(2, 2), _(3, 1), _(4, 1)), mapped);
  }
  
  @Test
  public void selectCountAndSubqueryInWhere() {
    ComplaintTable compl = new ComplaintTable();
    CustomerTable cust = new CustomerTable();

    SelectExpression<Integer> subQuery =
        new Squill(database)
            .from(compl)
            .where(eq(compl.customerId, cust.id))
            .subSelect(unchecked(Integer.class, "count(*)"));

    List<Tuple2<CustomerData, Integer>> res =
        squill
            .from(cust)
            .where(ge(subQuery, 2))
            .orderBy(desc(subQuery), asc(cust.id))
            .selectList(cust, subQuery);

    assertEquals("Result list size", 2, res.size());
    List<Tuple2<Integer, Integer>> mapped = Mapper.transform(res, new Mapper<Tuple2<CustomerData, Integer>, Tuple2<Integer, Integer>>() {
      @Override
	public Tuple2<Integer, Integer> map(Tuple2<CustomerData, Integer> tuple) {
        return _(tuple.v1.getId(), tuple.v2);
      }
    });
    assertEquals("Result", asList(_(1, 2), _(2, 2)), mapped);
  }

  @Test public void criteriaQuery() {
    CustomerTable cust = new CustomerTable("customer");

    List<CustomerData> res =
        squill
      .from(cust)
      .where(
        gt(cust.discount, 5).and(
            eq(cust.isActive, 1)))
      .orderBy(desc(cust.birthdate),
                asc(cust.firstName))
      .selectList(cust);
    assertEquals("count",3,res.size());
    List<String> mapped = Mapper.transform(res, new Mapper<CustomerData, String>() {
      @Override
	public String map(CustomerData cust) {
        return cust.getLastName();
      }
    });
    assertEquals("Result", asList("Claws", "Elf", "Devil"), mapped);
  }

  @Test public void subQueryAndFunction() {
    ComplaintTable compl = new ComplaintTable("complaint");
    CustomerTable cust = new CustomerTable("customer");

    SelectExpression<Integer> subQuery =
      new Squill(database)
      .from(compl)
      .where(eq(compl.customerId, cust.id))
      .subSelect(unchecked(Integer.class, "count(*)"));

    List<Tuple2<CustomerData, Integer>> clients = squill
    .from(cust)
    .orderBy(desc(subQuery))
    .selectList(cust, subQuery);
    assertEquals("count",4,clients.size());

    final List<Tuple2<String, Integer>> expected = asList(_("Fairy", 2), _("Devil", 2), _("Claws", 1), _("Elf", 1));

    List<Tuple2<String, Integer>> mapped = Mapper.transform(clients, new Mapper<Tuple2<CustomerData, Integer>, Tuple2<String, Integer>>() {
      @Override
	public Tuple2<String, Integer> map(Tuple2<CustomerData, Integer> tuple) {
        return _(tuple.v1.getLastName(), tuple.v2);
      }
    });
    assertEquals("name count",expected,mapped);

  }

  @Test
  public void callbackSelect() {
    CustomerTable cust = new CustomerTable();

    Tuple2<String, Date> clientTuple =
        squill
            .from(cust)
            .where(eq(cust.id, 1))
            .select(concat(cust.firstName, " ", cust.lastName),
                cust.birthdate);

    assertEquals("result", _("Tooth Fairy", date("0003-02-18")), clientTuple);
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
