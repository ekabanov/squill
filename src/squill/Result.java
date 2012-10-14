package squill;


import java.util.List;

import squill.callback.ResultCallback;
import squill.tuple.Tuple;


public interface Result<P extends Tuple> {
  <R> List<R> map(ResultCallback<P, R> callback);

  long count();

  String getSql();

  List<Object> getParams();

  P tuple();

  List<P> list();
}
