/**
 *
 */
package squill.functions;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import squill.query.Declaration;
import squill.query.Expression;
import squill.query.select.SelectExpression;
import squill.query.select.SimpleSelectExpression;
import squill.query.where.WhereExpression;
import squill.util.StringUtil;



/**
 * Operators that can be used inside WHERE.
 */
public class OperationsHelper {

  public static final Object[] EMTPY_ARGS = {};

  // TODO constant is an operand not an operator
  @SuppressWarnings("unchecked")
  public static <T> SelectExpression<T> constant(final T value) {
    final Class type = value != null ? value.getClass() : null;
    return new SimpleSelectExpression<T>(type) {
      public String getDefaultSql() {
        return "?";
      }

      public List<Object> getSqlArguments() {
        return asList((Object) value);
      }
    };
  }

  public static <T> SelectExpression<T> param(final String name,final Class<T> type) {
    return new SimpleSelectExpression<T>(type) {
      public String getDefaultSql() {
        return "?"; // todo formatter for spring named params ":"+name;
      }

      public List<Object> getSqlArguments() {
        return Collections.<Object>singletonList(new Declaration<T>(type,name));
      }
    };
  }

  public static <T> SelectExpression<T> literal(final T value) {
    final Class type = value != null ? value.getClass() : null;
    return new SimpleSelectExpression<T>(type) {
      public String getDefaultSql() {
        if (value instanceof String) return "'" + value + "'";
        if (value instanceof Expression) return ((Expression) value).getDefaultSql();
        return value.toString();
      }

      public List<Object> getSqlArguments() {
        return Collections.emptyList();
      }
    };
  }

  public static WhereExpression multiBooleanOperator(final String operator, final WhereExpression... exps) {
    return new MultiOperatorBooleanExpression<Boolean>(operator,exps);
  }

  public static <T> WhereExpression unaryPrefixBooleanOperator(final Expression<T> exp, final String operator) {
    return new MultiOperatorBooleanExpression<T>(operator,exp) {
      @Override
      public String getDefaultSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(operator);
        sql.append(exp.getDefaultSql());
        return sql.toString();
      }
    };
  }
  
  public static <T> WhereExpression unaryPostfixBooleanOperator(final Expression<T> exp, final String operator) {
    return new MultiOperatorBooleanExpression<T>(operator,exp) {
      @Override
      public String getDefaultSql() {
        StringBuilder sql = new StringBuilder();
        sql.append(exp.getDefaultSql());
        sql.append(operator);
        return sql.toString();
      }
    };
  }

  public static <T> WhereExpression binaryBooleanOperator(final T val1, final String operator, final T val2) {
    return new MultiOperatorBooleanExpression<T>(operator, expressionOrConstant(val1), expressionOrConstant(val2));
  }

  public static <T> WhereExpression inOperation(final Collection<Expression<T>> expressions) {
    return new MultiOperatorBooleanExpression<T>("in", expressions) {
      @Override public String getDefaultSql() {
        final List<? extends Expression<?>> parts = getParts();
        StringBuilder sb = new StringBuilder(parts.get(0).getDefaultSql());
        sb.append(" ");
        sb.append(getOperator());
        sb.append(" (");
        final List<? extends Expression<?>> operands = parts.subList(1, parts.size());
        sb.append(StringUtil.join(operands, Expression.GET_SQL_STRING, ","));
        sb.append(") ");
        return sb.toString();
      }
    };
  }

  public static <A, B, RETURN_TYPE> SelectExpression<RETURN_TYPE> binaryOperator(Class<RETURN_TYPE> type, final String operator, final Expression<A> exp1, final Expression<B> exp2) {
    // using package access
    return new BinaryOperatorSelectExpression<RETURN_TYPE,A,B>(type, exp1, operator, exp2);
  }

  // TODO NVL is a function not an operator
  public static <T> SelectExpression<T> nvl(Class<T> type, final Expression<T> exp1, final Expression<T> exp2) {
    return new BinaryOperatorSelectExpression<T,T,T>(type,exp1,"nvl",exp2) {
      @Override
      public String getDefaultSql() {
        StringBuilder sql = new StringBuilder();
        sql.append("nvl(");
        sql.append(exp1.getDefaultSql());
        sql.append(",");
        sql.append(exp2.getDefaultSql());
        sql.append(")");
        return sql.toString();
      }
    };
  }

  /**
   * Concatenates two lists.
   *
   * @param list1 first list.
   * @param list2 second list.
   * @return new list containing items from both lists.
   */
  protected static List<Object> concat(List<Object> list1, List<Object> list2) {
    List<Object> args = new ArrayList<Object>(list1.size() + list2.size());
    args.addAll(list1);
    args.addAll(list2);
    return args;
  }

  @SuppressWarnings({"unchecked"})
  public static <T> Expression<T> expressionOrConstant(T value) {
    if (value instanceof Expression) return (Expression<T>) value;
    else return constant(value);
  }

  public static <T> SelectExpression<T> unchecked(String alias, Class<T> type, final String sql, final Object... args) {
    return new UncheckedExpression<T>(alias, type, sql, args);
  }

  public static <T> SelectExpression<T> unchecked(Class<T> type, final String sql, final Object... args) {
    return unchecked(null,type,sql,args);
  }

  public static <T> SelectExpression<T> unchecked(Class<T> type, final String sql) {
    return unchecked(null,type,sql, EMTPY_ARGS);
  }
}
