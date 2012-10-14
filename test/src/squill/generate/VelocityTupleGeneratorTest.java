package squill.generate;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import static squill.tests.TestUtil.assertContains;
import squill.generate.TupleGenerator;
import static squill.util.FileUtil.path;

import java.util.Collections;

/**
 * @author Michael Hunger
 * @since 25.08.2008
 */
public class VelocityTupleGeneratorTest {
  @Test
  public void single() {
    final VelocityTupleGenerator generator = new VelocityTupleGenerator("test/src", "squill.tuple", 2);
    assertEquals("fileName", "test/src/squill/tuple/Tuple1.java", generator.fileName(1).getPath());
    final String file = generator.generate(2);
    assertContains("package", "package squill.tuple;", file);
    assertContains("class", "Tuple2 < T1,T2 >", file);
    assertContains("var", "public final T2 v2;", file);
    assertContains("factory", "public static < P1,P2 > Tuple2< P1,P2 > _( P1 v1,P2 v2 )", file);
    assertContains("toString", "return \"(\" +  v1 +\",\"+ v2  + \")\";", file);
    assertContains("equals", "Tuple2 tuple = (Tuple2) o;", file);
    assertContains("hashCode", "result = 31 * result + (v2 != null ? v2.hashCode() : 0)", file);
  }

}