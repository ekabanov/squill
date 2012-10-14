package squill.generate;

import static org.junit.Assert.*;
import org.junit.Test;
import static squill.tests.TestUtil.*;
import squill.util.FileUtil;

import java.util.Collections;

public class VelocityGeneratorTest {

  private final VelocityGenerator generator = new VelocityGenerator();

  @Test public void simpleGeneration() {
    assertEquals("Value: 2\n" +
        "    Tuple1   Tuple1 < F1 > \n" +
        "    Tuple2   Tuple2 < F1,F2 > \n",
        generator.generate(FileUtil.path(getClass()) + "test", 2));
  }

  @Test public void orderByBuilderGeneration() {
    final String template = FileUtil.path(OrderByBuilderGenerator.class) + "OrderByBuilder";
    final String twoTuples = generator.generate(template, Collections.singletonMap("tupleCount", 10));
    assertContains("comment1", "Select 1 value(s)", twoTuples);
    assertContains("comment2", "Select 2 value(s)", twoTuples);
    // todo more assertions
    System.out.println("twoTuples = " + twoTuples);
  }
}
