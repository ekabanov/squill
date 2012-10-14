package squill.callback;

import squill.tuple.Tuple;

/**
 * @author Michael Hunger
 * @since 28.08.2008
 */
public interface ResultCallback<P, R> {
  R handle(P tuple, Status status); // todo perhaps an R previous for self-aggregation

  public static interface Status {
    int getRow();

    void abort();

    boolean isAborted();
  }
}
