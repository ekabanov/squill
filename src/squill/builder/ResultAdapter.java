package squill.builder;

import java.util.List;

import squill.Result;
import squill.callback.ResultCallback;
import squill.tuple.Tuple;



public class ResultAdapter<P extends Tuple> implements Result<P> {
  private final Result<P> result;

  public ResultAdapter(final Result<P> result) {
    this.result = result;
  }

  public Result getResult() {
    return result;
  }

  public <R> List<R> map(ResultCallback<P,R> resultCallback) {
    return result.map(resultCallback);
  }

  public long count() {
    return result.count();
  }

  public String getSql() {
    return result.getSql();
  }

  public List<Object> getParams() {
    return result.getParams();
  }

  public P tuple() {
    return result.tuple();
  }

  public List<P> list() {
    return result.list();
  }
}
