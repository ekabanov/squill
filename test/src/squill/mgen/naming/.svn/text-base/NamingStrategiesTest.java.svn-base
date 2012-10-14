package squill.mgen.naming;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

/**
 * @author Juhan Aasaru
 * @since 01.09.2008
 */
public class NamingStrategiesTest {
  
  static final String[] VARIANTS = new String[]{"POOL","second_address","the_THIRD_moon"};
  static final String[] types_cc   = new String[]{"Pool","SecondAddress", "TheThirdMoon"};
  static final String[] types_low  = new String[]{"pool","second_address", "the_third_moon"};
 
  
  static final Map<NamingStrategy, String[]> strats = new HashMap<NamingStrategy, String[]>();
  
  static {
    strats.put(new CamelCaseNaming(), types_cc);
    strats.put(new LowerCaseNaming(), types_low);
    strats.put(new SameNaming(), VARIANTS);
  }
  
  
  
  @Test
  public void typeTest() {


    for (final NamingStrategy strategy : strats.keySet()) {
      String[] expected = strats.get(strategy);
      String strategyName = strategy.getClass().getName();

      for (int i = 0; i < VARIANTS.length; i++) {
        assertEquals(strategyName + " failure",
            expected[i], strategy.getTypeName(VARIANTS[i]));
      }

    }
    
  }
  

  
  
}
