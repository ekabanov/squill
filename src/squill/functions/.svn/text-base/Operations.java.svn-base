package squill.functions;

import static squill.functions.OperationsHelper.expressionOrConstant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import squill.query.Expression;
import squill.query.JoinType;
import squill.query.cud.InsertCruElement;
import squill.query.cud.InsertFieldElement;
import squill.query.cud.UpdateCruElement;
import squill.query.cud.UpdateFieldElement;
import squill.query.from.JoinElement;
import squill.query.orderby.OrderByElement;
import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.query.select.SelectExpression;
import squill.query.where.WhereExpression;




/**
 * Helper methods for creating expressions.
 * <p/>
 * These methods should be used by static imports.
 */
public class Operations {

    public static <T> SelectExpression<T> unchecked(String alias, Class<T> type, final String sql, final Object... args) {
        return OperationsHelper.unchecked(alias, type, sql, args);
    }

    public static <T> SelectExpression<T> unchecked(Class<T> type, final String sql, final Object... args) {
        return OperationsHelper.unchecked(type,sql,args);
    }

    public static <T> SelectExpression<T> unchecked(Class<T> type, final String sql) {
      return OperationsHelper.unchecked(type,sql);
    }

    public static <T> SelectExpression<T> constant(T value) {
        return OperationsHelper.constant(value);
    }

    public static <T> SelectExpression<T> param(String name,Class<T> type) {
        return OperationsHelper.param(name,type);
    }
    public static SelectExpression<Object> param(String name) {
        return OperationsHelper.param(name,Object.class);
    }

    public static <T> SelectExpression<T> literal(T value) {
        return OperationsHelper.literal(value);
    }

    public static WhereExpression or(WhereExpression... exps) {
        return OperationsHelper.multiBooleanOperator(" OR ", exps);
    }

    public static WhereExpression and(WhereExpression... exps) {
        return OperationsHelper.multiBooleanOperator(" AND ", exps);
    }
    
    public static <T> WhereExpression not(Expression<T> exp1) {
      return OperationsHelper.unaryPrefixBooleanOperator(exp1, " not ");
    }
    
    public static <T> WhereExpression not(T val) {
      return OperationsHelper.unaryPrefixBooleanOperator(constant(val), " not ");
    }

    public static <T> WhereExpression notNull(Expression<T> exp1) {
        return OperationsHelper.unaryPostfixBooleanOperator(exp1, " is not null ");
    }
    
    public static <T> WhereExpression isNull(Expression<T> exp1) {
        return OperationsHelper.unaryPostfixBooleanOperator(exp1, " is null ");
    }

    /**
     * Equal to
     */
    public static <T> WhereExpression eq(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " = ", exp2);
    }

    /**
     * Equal to
     */
    public static <T> WhereExpression eq(Expression<T> exp, T val) {
        return OperationsHelper.binaryBooleanOperator(exp, " = ", constant(val));
    }

    /**
     * Equal to
     */
    public static <T> WhereExpression eq(T val, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(val), " = ", exp);
    }

    /**
     * Equal to
     */
    public static <T> WhereExpression eq(T val1, T val2) {
        return OperationsHelper.binaryBooleanOperator(expressionOrConstant(val1), " = ", expressionOrConstant(val2));
    }

  /**
   * In
   */
  public static <T> WhereExpression in(T exp, T...values) {
    final Collection<Expression<T>> expressions=new ArrayList<Expression<T>>(values.length+1);
    expressions.add(expressionOrConstant(exp));
    for (T value : values) {
      expressions.add(expressionOrConstant(value));
    }
    return OperationsHelper.inOperation(expressions);
  }

  /**
     * Like
     */
    public static <T> WhereExpression like(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " like ", exp2);
    }

    /**
     * Like
     */
    public static <T> WhereExpression like(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " like ", constant(value));
    }

    /**
     * Like
     */
    public static <T> WhereExpression like(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " like ", exp);
    }

    /**
     * Less than
     */
    public static <T> WhereExpression lt(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " < ", exp2);
    }

    /**
     * Less than
     */
    public static <T> WhereExpression lt(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " < ", constant(value));
    }

    /**
     * Less than
     */
    public static <T> WhereExpression lt(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " < ", exp);
    }

    /**
     * Greater than
     */
    public static <T> WhereExpression gt(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " > ", exp2);
    }

    /**
     * Greater than
     */
    public static <T> WhereExpression gt(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " > ", constant(value));
    }

    /**
     * Greater than
     */
    public static <T> WhereExpression gt(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " > ", exp);
    }

    /**
     * Greater than - Double to BigDecimal conversion
     */
    public static <T> WhereExpression gt(Expression<BigDecimal> exp, Double value) {
        return OperationsHelper.binaryBooleanOperator(exp, " > ", constant(new BigDecimal(value)));
    }

    /**
     * Not equal to
     */
    public static <T> WhereExpression ne(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " <> ", exp2);
    }

    /**
     * Not equal to
     */
    public static <T> WhereExpression ne(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " <> ", constant(value));
    }

    /**
     * Not equal to
     */
    public static <T> WhereExpression ne(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " <> ", exp);
    }

    /**
     * Greater than or equal to
     */
    public static <T> WhereExpression ge(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " >= ", exp2);
    }

    /**
     * Greater than or equal to
     */
    public static <T> WhereExpression ge(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " >= ", constant(value));
    }

    /**
     * Greater than or equal to
     */
    public static <T> WhereExpression ge(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " >= ", exp);
    }

    /**
     * Less than or equal to
     */
    public static <T> WhereExpression le(Expression<T> exp1, Expression<T> exp2) {
        return OperationsHelper.binaryBooleanOperator(exp1, " <= ", exp2);
    }

    /**
     * Less than or equal to
     */
    public static <T> WhereExpression le(Expression<T> exp, T value) {
        return OperationsHelper.binaryBooleanOperator(exp, " <= ", constant(value));
    }

    /**
     * Less than or equal to
     */
    public static <T> WhereExpression le(T value, Expression<T> exp) {
        return OperationsHelper.binaryBooleanOperator(constant(value), " <= ", exp);
    }


    public static <A extends Number, B extends Number> SelectExpression<BigDecimal> add(Expression<A> exp1, Expression<B> exp2) {
        return OperationsHelper.binaryOperator(BigDecimal.class, " + ", exp1, exp2);
    }

    public static <A extends Number, B extends Number> SelectExpression<BigDecimal> add(Expression<A> exp, B val) {
        return OperationsHelper.binaryOperator(BigDecimal.class, " + ", exp, constant(val));
    }

    public static <A extends Number, B extends Number> SelectExpression<BigDecimal> add(A val, Expression<B> exp) {
        return OperationsHelper.binaryOperator(BigDecimal.class, " + ", constant(val), exp);
    }

    /**
     * Returns UncheckedExpr because this can be used in insert!
     */
    public static <A extends Number, B extends Number> SelectExpression<BigDecimal> add(A val, B val2) {
        return OperationsHelper.binaryOperator(BigDecimal.class, " + ", constant(val), constant(val2));
    }


    /**
     * Standard: a || b. MSSQL: + MySQL: CONCAT(,)
     */
    public static <A, B> SelectExpression<String> concat(Expression<A> exp1, Expression<B> exp2) {
        return OperationsHelper.binaryOperator(String.class, " || ", exp1, exp2);
    }

    public static <A, B> SelectExpression<String> concat(Expression<A> exp, B val) {
        return OperationsHelper.binaryOperator(String.class, " || ", exp, constant(val));
    }

    public static <A, B> SelectExpression<String> concat(A val, Expression<B> exp) {
        return OperationsHelper.binaryOperator(String.class, " || ", constant(val), exp);
    }

    public static <A, B, C> SelectExpression<String> concat(Expression<A> exp1, C val, Expression<B> exp2) {
        return OperationsHelper.binaryOperator(String.class, " || ", expressionOrConstant(exp1),
                OperationsHelper.binaryOperator(String.class, " || ", literal(val), expressionOrConstant(exp2)));
    }

    // standard coalesce, Oracle: nvl
    public static <T extends Number> SelectExpression<T> nvl(SelectExpression<T> exp1, SelectExpression<T> exp2) {
        return OperationsHelper.nvl(exp1.getTableType(), exp1, exp2);
    }

    public static <T extends Number> SelectExpression<T> nvl(SelectExpression<T> exp, T val) {
        return OperationsHelper.nvl(exp.getTableType(), exp, constant(val));
    }

    public static <T extends Number> SelectExpression<T> nvl(T val, SelectExpression<T> exp) {
        return OperationsHelper.nvl(exp.getTableType(), constant(val), exp);
    }


    public static <FIELD, TABLE> UpdateFieldElement<TABLE> updateElement(
            Column<FIELD, TABLE> field, SelectExpression<FIELD> newValue) {
        return new UpdateFieldElement<TABLE>(field, newValue);
    }

    public static <FIELD, TABLE> UpdateFieldElement<TABLE> updateElement(
            Column<FIELD, TABLE> field, FIELD newValueRaw) {
        return new UpdateFieldElement<TABLE>(field, constant(newValueRaw));
    }

    public static <FIELD, TABLE> UpdateCruElement<TABLE> updateElement(
            ReadableTable<TABLE> cru, TABLE newValue) {
        return new UpdateCruElement<TABLE>(cru, newValue);
    }

    public static <FIELD, TABLE> InsertFieldElement<TABLE> insertElement(
            Column<FIELD, TABLE> field, SelectExpression<FIELD> newValue) {
        return new InsertFieldElement<TABLE>(field, newValue);
    }


    // thesis:label=insertElement
    public static <FIELD, TABLE> InsertFieldElement<TABLE> insertElement(Column<FIELD, TABLE> field, FIELD newValue) {
        return new InsertFieldElement<TABLE>
                (field, newValue);
    }

    public static <TABLE> InsertCruElement<TABLE> insertElement(ReadableTable<TABLE> field, TABLE modelObj) {
        return new InsertCruElement<TABLE>(field, modelObj);
    }

    // Order by a field using default order
    public static <FIELD> OrderByElement<FIELD> asc(Column<FIELD, ?> field) {
    	return ascOrDesc(field, true);
    }

    // Order by
    public static <FIELD> OrderByElement<FIELD> desc(Column<FIELD, ?> field) {
        return ascOrDesc(field, false);
    }
    
    public static <FIELD> OrderByElement<FIELD> ascOrDesc(Column<FIELD, ?> field, boolean ascendingly) {
        return new OrderByElement<FIELD>(field, ascendingly);
    }

    public static <RETURNTYPE> OrderByElement<RETURNTYPE> asc(SelectExpression<RETURNTYPE> uncheckedExpr) {
    	return ascOrDesc(uncheckedExpr, true);
    }

    public static <RETURNTYPE> OrderByElement<RETURNTYPE> desc(SelectExpression<RETURNTYPE> uncheckedExpr) {
        return ascOrDesc(uncheckedExpr, false);
    }
    
    public static <RETURNTYPE> OrderByElement<RETURNTYPE> ascOrDesc(SelectExpression<RETURNTYPE> uncheckedExpr,
    		boolean ascendingly) {
        return new OrderByElement<RETURNTYPE>(uncheckedExpr, ascendingly);
    }


    // Different types of joins

  public static <FIELD, TABLE> JoinElement<FIELD, TABLE> join
      (ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1, Column<FIELD, ?> onField2) {

    return new JoinElement<FIELD, TABLE>(table, onField1, onField2, JoinType.INNER);
  }

  public static <FIELD, TABLE> JoinElement<FIELD, TABLE> rightJoin(
      ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1, Column<FIELD, ?> onField2) {

    return new JoinElement<FIELD, TABLE>(table, onField1, onField2, JoinType.RIGHT);
  }
  
  public static <FIELD, TABLE> JoinElement<FIELD, TABLE> leftJoin(
      ReadableTable<TABLE> table, Column<FIELD, TABLE> onField1, Column<FIELD, ?> onField2) {

    return new JoinElement<FIELD, TABLE>(table, onField1, onField2, JoinType.LEFT);
  }
}
