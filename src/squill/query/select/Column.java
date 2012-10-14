package squill.query.select;

import static java.lang.String.format;

import java.math.BigDecimal;

import squill.alias.Alias;
import squill.functions.Operations;
import squill.query.Expression;
import squill.query.orderby.OrderByElement;
import squill.query.where.WhereExpression;
import squill.util.StringUtil;
import squill.util.ToString;

/**
 * All fields extend this class.
 * 
 * @param <FIELD> Field type
 */
public class Column<FIELD, TABLE> extends BaseSelectExpression<FIELD> {
	private final ReadableTable<TABLE> table;
	private final String columnName;
	
	private final Alias fieldAlias;
	private final String modelSetterName;
	private final String modelGetterName;
	private final String propertyName;
	
	private final Class<FIELD> resultType;
	private FIELD value;
	
	public static final ToString<Column> GET_COLUMN_NAME = new ToString<Column>() {
		public String toString(Column column) {
			return column.getColumnName();
		}
	};
	public static final ToString<Column> GET_NAME = new ToString<Column>() {
		public String toString(Column column) {
			return column.getColumnNameWithTableSql();
		}
	};
	public static final ToString<Column> GET_SQL_STRING = new ToString<Column>() {
		public String toString(final Column column) {
			return column.getDefaultSql();
		}
	};
	
	public Column(String columnName, Class<FIELD> resultType, String propertyName, ReadableTable<TABLE> table) {
		this.fieldAlias = Alias.newColumnAlias();
		this.columnName = columnName;
		this.resultType = resultType;
		this.propertyName = propertyName;
		this.table = table;
		final String capitalizedPropertyName = StringUtil.capitalize(propertyName);
		this.modelSetterName = "set" + capitalizedPropertyName;
		this.modelGetterName = "get" + capitalizedPropertyName;
	}
	
	@Override
	public String getDefaultSql() {
		return getColumnNameWithTableSql();
	}
	
	public String getColumnAsAliasSql() {
		return format("%s AS %s", getDefaultSql(), getAlias());
	}
	
	public Class<FIELD> getTableType() {
		return resultType;
	}
	
	public String getColumnName() {
		return columnName;
	}
	
	public String getColumnNameWithTableSql() {
		return format("%s.%s", table.getAlias(), columnName);
	}
	
	public String getAlias() {
		return table.getAlias() + fieldAlias.resolve(getContext());
	}
	
	// get the field name of model object
	// this can be set by constructor or lazily found out on first call
	public String getModelSetterName() {
		return modelSetterName;
	}
	
	String getModelGetterName() {
		return modelGetterName;
	}
	
	public ReadableTable<TABLE> getTable() {
		return table;
	}
	
	public void set(FIELD value) {
		this.value = value;
	}
	
	public FIELD get() {
		return this.value;
	}
	
	public String getPropertyName() {
		return propertyName;
	}
	
	/**
	 * Like
	 */
	public WhereExpression like(Expression<FIELD> exp) {
		return Operations.like(this, exp);
	}
	
	/**
	 * Like
	 */
	public WhereExpression like(FIELD value) {
		return Operations.like(this, value);
	}
	
	/**
	 * Equal to
	 */
	public WhereExpression eq(Expression<FIELD> exp) {
		return Operations.eq(this, exp);
	}
	
	/**
	 * Equal to
	 */
	public WhereExpression eq(FIELD value) {
		return Operations.eq(this, value);
	}
	
	/**
	 * In
	 */
	public WhereExpression in(FIELD... values) {
		return Operations.in(this, values);
	}
	
	/**
	 * Not equal to
	 */
	public WhereExpression ne(Expression<FIELD> exp) {
		return Operations.ne(this, exp);
	}
	
	/**
	 * Not equal to
	 */
	public WhereExpression ne(FIELD value) {
		return Operations.ne(this, value);
	}
	
	/**
	 * Less than
	 */
	public WhereExpression lt(Expression<FIELD> exp) {
		return Operations.lt(this, exp);
	}
	
	/**
	 * Less than
	 */
	public WhereExpression lt(FIELD value) {
		return Operations.lt(this, value);
	}
	
	/**
	 * Greater than
	 */
	public WhereExpression gt(Expression<FIELD> exp) {
		return Operations.gt(this, exp);
	}
	
	/**
	 * Greater than
	 */
	public WhereExpression gt(FIELD value) {
		return Operations.gt(this, value);
	}
	
	/**
	 * Greater than or equal to
	 */
	public WhereExpression ge(Expression<FIELD> exp) {
		return Operations.ge(this, exp);
	}
	
	/**
	 * Greater than or equal to
	 */
	public WhereExpression ge(FIELD value) {
		return Operations.ge(this, value);
	}
	
	/**
	 * Less than or equal to
	 */
	public WhereExpression le(Expression<FIELD> exp) {
		return Operations.le(this, exp);
	}
	
	/**
	 * Less than or equal to
	 */
	public WhereExpression le(FIELD value) {
		return Operations.le(this, value);
	}
	
	public <A extends Number, B extends Number> SelectExpression<BigDecimal> add(Expression<B> exp) {
		return Operations.add((Expression<A>) this, exp);
	}
	
	public <A extends Number, B extends Number> SelectExpression<BigDecimal> add(B val) {
		return Operations.add((Expression<A>) this, val);
	}
	
	public OrderByElement<FIELD> asc() {
		return Operations.asc(this);
	}
	
	public OrderByElement<FIELD> desc() {
		return Operations.desc(this);
	}
	
	public WhereExpression not() {
		return Operations.not(this);
	}
	
	public WhereExpression notNull() {
		return Operations.notNull(this);
	}
	
	public WhereExpression isNull() {
		return Operations.isNull(this);
	}
}
