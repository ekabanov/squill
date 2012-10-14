package squill.tests;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;
import static squill.functions.Operations.desc;
import static squill.functions.Operations.gt;
import static squill.functions.Operations.notNull;
import static squill.tuple.Tuple2._;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import squill.Squill;
import squill.db.Database;
import squill.tuple.Tuple2;
import squill.model.ComplaintData.ComplaintTable;

public class OrmTest {
  private Database database;
  private Squill squill;


  @Test
  public void ormJoins() {
    ComplaintTable complaint = new ComplaintTable();

    List<Tuple2<String, Integer>> resultList =
        squill
            .from(complaint, complaint.customer())
            .where(
                gt(complaint.customer().isActive, 0),
                notNull(complaint.percentSolved),
                notNull(complaint.refoundSum))
            .orderBy(desc(complaint.customer().id))
            .selectList(
                complaint.customer().lastName,
                complaint.percentSolved);

    assertEquals("Result list size", 6, resultList.size());
    assertEquals("Result",
        asList(_("Elf", 55), _("Claws", 85), _("Devil", 10), _("Devil", 75), _("Fairy", 15), _("Fairy", 100)),
        resultList);
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
