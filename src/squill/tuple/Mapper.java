package squill.tuple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michael Hunger
 * @since 25.08.2008
 */
public abstract class Mapper<I, O> {
  public static <I, O> List<O> transform(Iterable<I> iterable, Mapper<I, O> mapper) {
    List<O> result = new ArrayList<O>();
    for (I value : iterable) {
      result.add(mapper.map(value));
    }
    return result;
  }

  public abstract O map(I value);
}
