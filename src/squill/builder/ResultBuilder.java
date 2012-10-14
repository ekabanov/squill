package squill.builder;

import java.util.List;

import squill.Result;
import squill.callback.ResultCallback;
import squill.format.SqlFormat;
import squill.tree.QueryPartHandler;
import squill.tuple.Tuple;
import squill.tuple.Tuple2;



/**
 * @author Michael Hunger
 * @since 29.08.2008
 */
public class ResultBuilder<P extends Tuple> implements Result<P> {
  private final BaseOrderByBuilder orderByBuilder;
  private final Class<P> tupleType;

  @SuppressWarnings({"unchecked"})
  public ResultBuilder(BaseOrderByBuilder orderByBuilder, Class<? extends Tuple> tupleType) {
    this.orderByBuilder = orderByBuilder;
    this.tupleType = (Class<P>) tupleType;
  }

  public <R> List<R> map(ResultCallback<P, R> callback) {
    return this.orderByBuilder.queryCallback(tupleType, callback);
  }

  public long count() {
    return this.orderByBuilder.count();
  }
  public String toString() {
    return getSql();
  }

  public String getSql() {
    return this.orderByBuilder.getSql();
  }

  public List<Object> getParams() {
    return this.orderByBuilder.getArgs();
  }
  @SuppressWarnings({"unchecked"})
  public P tuple() {
    return (P) this.orderByBuilder.queryTuple(tupleType);
  }

  @SuppressWarnings({"unchecked"}) public List<P> list() {
    return (List<P>)this.orderByBuilder.queryTuples(tupleType);
  }
  public ResultBuilder<P> resolve(String name, Object param) {
    this.orderByBuilder.addParam(name,param);
    return this;
  }
  public ResultBuilder<P> resolve(Tuple2<String,?>...params) {
    for (Tuple2<String, ?> param : params) {
      this.orderByBuilder.addParam(param.v1,param.v2);
    }
    return this;
  }

  public <T> T traverse(final QueryPartHandler<T> handler, T collectingParameter) {
    return this.orderByBuilder.traverse(handler, collectingParameter);
  }

  public String getSql(final SqlFormat format) {
    return this.orderByBuilder.getSql(format);
  }
}

