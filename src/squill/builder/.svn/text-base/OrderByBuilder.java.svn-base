package squill.builder;

import java.util.List;

import squill.callback.ResultCallback;
import squill.db.Database;
import squill.query.QueryContext;
import squill.query.select.SelectExpression;
import squill.tuple.Tuple10;
import squill.tuple.Tuple2;
import squill.tuple.Tuple3;
import squill.tuple.Tuple4;
import squill.tuple.Tuple5;
import squill.tuple.Tuple6;
import squill.tuple.Tuple7;
import squill.tuple.Tuple8;
import squill.tuple.Tuple9;




@SuppressWarnings("unchecked")
public class OrderByBuilder extends BaseOrderByBuilder {

  protected OrderByBuilder(QueryContext ctx, Database database) {
    super(ctx, database);
  }



  /**
   * Select 2 value(s) as single object (not a list)
   */
  public <F1, F2> Tuple2<F1, F2> select(SelectExpression<F1> select1, SelectExpression<F2> select2) {
    return (Tuple2<F1, F2>) queryTuple(Tuple2.class, select1, select2);
  }

  /**
   * Select 2 value(s) as a list
   */
  public <F1, F2> List<Tuple2<F1, F2>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2) {
    return (List<Tuple2<F1, F2>>) queryTuples(Tuple2.class, select1, select2);
  }

  public <F1, F2, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, final ResultCallback<Tuple2<F1, F2>, R> callback) {
    final ResultCallback<Tuple2, R> resultCallback = (ResultCallback<Tuple2, R>) (Object) callback;
    return queryCallback(Tuple2.class, resultCallback, select1, select2);
  }

  public <F1, F2> ResultBuilder<Tuple2<F1, F2>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2) {
    addSelects(select1, select2);
    return new ResultBuilder<Tuple2<F1, F2>>(this, Tuple2.class);
  }

  /**
   * Select 3 value(s) as single object (not a list)
   */
  public <F1, F2, F3> Tuple3<F1, F2, F3> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3) {
    return (Tuple3<F1, F2, F3>) queryTuple(Tuple3.class, select1, select2, select3);
  }

  /**
   * Select 3 value(s) as a list
   */
  public <F1, F2, F3> List<Tuple3<F1, F2, F3>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3) {
    return (List<Tuple3<F1, F2, F3>>) queryTuples(Tuple3.class, select1, select2, select3);
  }

  public <F1, F2, F3, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, final ResultCallback<Tuple3<F1, F2, F3>, R> callback) {
    final ResultCallback<Tuple3, R> resultCallback = (ResultCallback<Tuple3, R>) (Object) callback;
    return queryCallback(Tuple3.class, resultCallback, select1, select2, select3);
  }

  public <F1, F2, F3> ResultBuilder<Tuple3<F1, F2, F3>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3) {
    addSelects(select1, select2, select3);
    return new ResultBuilder<Tuple3<F1, F2, F3>>(this, Tuple3.class);
  }

  /**
   * Select 4 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4> Tuple4<F1, F2, F3, F4> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4) {
    return (Tuple4<F1, F2, F3, F4>) queryTuple(Tuple4.class, select1, select2, select3, select4);
  }

  /**
   * Select 4 value(s) as a list
   */
  public <F1, F2, F3, F4> List<Tuple4<F1, F2, F3, F4>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4) {
    return (List<Tuple4<F1, F2, F3, F4>>) queryTuples(Tuple4.class, select1, select2, select3, select4);
  }

  public <F1, F2, F3, F4, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, final ResultCallback<Tuple4<F1, F2, F3, F4>, R> callback) {
    final ResultCallback<Tuple4, R> resultCallback = (ResultCallback<Tuple4, R>) (Object) callback;
    return queryCallback(Tuple4.class, resultCallback, select1, select2, select3, select4);
  }

  public <F1, F2, F3, F4> ResultBuilder<Tuple4<F1, F2, F3, F4>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4) {
    addSelects(select1, select2, select3, select4);
    return new ResultBuilder<Tuple4<F1, F2, F3, F4>>(this, Tuple4.class);
  }

  /**
   * Select 5 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5> Tuple5<F1, F2, F3, F4, F5> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5) {
    return (Tuple5<F1, F2, F3, F4, F5>) queryTuple(Tuple5.class, select1, select2, select3, select4, select5);
  }

  /**
   * Select 5 value(s) as a list
   */
  public <F1, F2, F3, F4, F5> List<Tuple5<F1, F2, F3, F4, F5>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5) {
    return (List<Tuple5<F1, F2, F3, F4, F5>>) queryTuples(Tuple5.class, select1, select2, select3, select4, select5);
  }

  public <F1, F2, F3, F4, F5, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, final ResultCallback<Tuple5<F1, F2, F3, F4, F5>, R> callback) {
    final ResultCallback<Tuple5, R> resultCallback = (ResultCallback<Tuple5, R>) (Object) callback;
    return queryCallback(Tuple5.class, resultCallback, select1, select2, select3, select4, select5);
  }

  public <F1, F2, F3, F4, F5> ResultBuilder<Tuple5<F1, F2, F3, F4, F5>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5) {
    addSelects(select1, select2, select3, select4, select5);
    return new ResultBuilder<Tuple5<F1, F2, F3, F4, F5>>(this, Tuple5.class);
  }

  /**
   * Select 6 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5, F6> Tuple6<F1, F2, F3, F4, F5, F6> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6) {
    return (Tuple6<F1, F2, F3, F4, F5, F6>) queryTuple(Tuple6.class, select1, select2, select3, select4, select5, select6);
  }

  /**
   * Select 6 value(s) as a list
   */
  public <F1, F2, F3, F4, F5, F6> List<Tuple6<F1, F2, F3, F4, F5, F6>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6) {
    return (List<Tuple6<F1, F2, F3, F4, F5, F6>>) queryTuples(Tuple6.class, select1, select2, select3, select4, select5, select6);
  }

  public <F1, F2, F3, F4, F5, F6, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, final ResultCallback<Tuple6<F1, F2, F3, F4, F5, F6>, R> callback) {
    final ResultCallback<Tuple6, R> resultCallback = (ResultCallback<Tuple6, R>) (Object) callback;
    return queryCallback(Tuple6.class, resultCallback, select1, select2, select3, select4, select5, select6);
  }

  public <F1, F2, F3, F4, F5, F6> ResultBuilder<Tuple6<F1, F2, F3, F4, F5, F6>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6) {
    addSelects(select1, select2, select3, select4, select5, select6);
    return new ResultBuilder<Tuple6<F1, F2, F3, F4, F5, F6>>(this, Tuple6.class);
  }

  /**
   * Select 7 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5, F6, F7> Tuple7<F1, F2, F3, F4, F5, F6, F7> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7) {
    return (Tuple7<F1, F2, F3, F4, F5, F6, F7>) queryTuple(Tuple7.class, select1, select2, select3, select4, select5, select6, select7);
  }

  /**
   * Select 7 value(s) as a list
   */
  public <F1, F2, F3, F4, F5, F6, F7> List<Tuple7<F1, F2, F3, F4, F5, F6, F7>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7) {
    return (List<Tuple7<F1, F2, F3, F4, F5, F6, F7>>) queryTuples(Tuple7.class, select1, select2, select3, select4, select5, select6, select7);
  }

  public <F1, F2, F3, F4, F5, F6, F7, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, final ResultCallback<Tuple7<F1, F2, F3, F4, F5, F6, F7>, R> callback) {
    final ResultCallback<Tuple7, R> resultCallback = (ResultCallback<Tuple7, R>) (Object) callback;
    return queryCallback(Tuple7.class, resultCallback, select1, select2, select3, select4, select5, select6, select7);
  }

  public <F1, F2, F3, F4, F5, F6, F7> ResultBuilder<Tuple7<F1, F2, F3, F4, F5, F6, F7>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7) {
    addSelects(select1, select2, select3, select4, select5, select6, select7);
    return new ResultBuilder<Tuple7<F1, F2, F3, F4, F5, F6, F7>>(this, Tuple7.class);
  }

  /**
   * Select 8 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8> Tuple8<F1, F2, F3, F4, F5, F6, F7, F8> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8) {
    return (Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>) queryTuple(Tuple8.class, select1, select2, select3, select4, select5, select6, select7, select8);
  }

  /**
   * Select 8 value(s) as a list
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8> List<Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8) {
    return (List<Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>>) queryTuples(Tuple8.class, select1, select2, select3, select4, select5, select6, select7, select8);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, final ResultCallback<Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>, R> callback) {
    final ResultCallback<Tuple8, R> resultCallback = (ResultCallback<Tuple8, R>) (Object) callback;
    return queryCallback(Tuple8.class, resultCallback, select1, select2, select3, select4, select5, select6, select7, select8);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8> ResultBuilder<Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8) {
    addSelects(select1, select2, select3, select4, select5, select6, select7, select8);
    return new ResultBuilder<Tuple8<F1, F2, F3, F4, F5, F6, F7, F8>>(this, Tuple8.class);
  }

  /**
   * Select 9 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8, F9> Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9) {
    return (Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>) queryTuple(Tuple9.class, select1, select2, select3, select4, select5, select6, select7, select8, select9);
  }

  /**
   * Select 9 value(s) as a list
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8, F9> List<Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9) {
    return (List<Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>>) queryTuples(Tuple9.class, select1, select2, select3, select4, select5, select6, select7, select8, select9);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8, F9, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9, final ResultCallback<Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>, R> callback) {
    final ResultCallback<Tuple9, R> resultCallback = (ResultCallback<Tuple9, R>) (Object) callback;
    return queryCallback(Tuple9.class, resultCallback, select1, select2, select3, select4, select5, select6, select7, select8, select9);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8, F9> ResultBuilder<Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9) {
    addSelects(select1, select2, select3, select4, select5, select6, select7, select8, select9);
    return new ResultBuilder<Tuple9<F1, F2, F3, F4, F5, F6, F7, F8, F9>>(this, Tuple9.class);
  }

  /**
   * Select 10 value(s) as single object (not a list)
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> select(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9, SelectExpression<F10> select10) {
    return (Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>) queryTuple(Tuple10.class, select1, select2, select3, select4, select5, select6, select7, select8, select9, select10);
  }

  /**
   * Select 10 value(s) as a list
   */
  public <F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> List<Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>> selectList(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9, SelectExpression<F10> select10) {
    return (List<Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>>) queryTuples(Tuple10.class, select1, select2, select3, select4, select5, select6, select7, select8, select9, select10);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8, F9, F10, R> List<R> selectCallback(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9, SelectExpression<F10> select10, final ResultCallback<Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>, R> callback) {
    final ResultCallback<Tuple10, R> resultCallback = (ResultCallback<Tuple10, R>) (Object) callback;
    return queryCallback(Tuple10.class, resultCallback, select1, select2, select3, select4, select5, select6, select7, select8, select9, select10);
  }

  public <F1, F2, F3, F4, F5, F6, F7, F8, F9, F10> ResultBuilder<Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>> selectAs(SelectExpression<F1> select1, SelectExpression<F2> select2, SelectExpression<F3> select3, SelectExpression<F4> select4, SelectExpression<F5> select5, SelectExpression<F6> select6, SelectExpression<F7> select7, SelectExpression<F8> select8, SelectExpression<F9> select9, SelectExpression<F10> select10) {
    addSelects(select1, select2, select3, select4, select5, select6, select7, select8, select9, select10);
    return new ResultBuilder<Tuple10<F1, F2, F3, F4, F5, F6, F7, F8, F9, F10>>(this, Tuple10.class);
  }
}