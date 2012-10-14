package squill.alias;

import java.util.HashMap;
import java.util.Map;

public class AliasResolver {
  private final Map<String, Integer> aliasCounterMap = 
    new HashMap<String, Integer>(); 
  
  private int getAndIncreaseCounter(String prefix) {
    Integer c = aliasCounterMap.get(prefix);
    if (c == null)
      c = 0;
    else
      c++;
    aliasCounterMap.put(prefix, c);
    return c;
    
  }
  
  public String uniqueAlias(String prefix) {
    return prefix + getAndIncreaseCounter(prefix);
  }
}
