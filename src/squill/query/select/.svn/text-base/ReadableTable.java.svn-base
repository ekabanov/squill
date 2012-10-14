package squill.query.select;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

import squill.alias.Alias;
import squill.query.QueryContext;
import squill.query.from.FromExpression;
import squill.util.BeanUtil;
import squill.util.FieldHandler;
import squill.util.ReflectUtils;
import squill.util.StringUtil;
import squill.util.ToString;




/**
 * This needs to be extended by tables which user can only select data from.
 * WritableTable must be extended for tables where data needs to be updated,
 * inserted or deleted.
 */
public abstract class ReadableTable<OBJ> extends BaseSelectExpression<OBJ> implements FromExpression {

  private final Alias alias;
  private List<Column<?, OBJ>> columns;
  
  public static final ToString<ReadableTable<?>> GET_NAME = new ToString<ReadableTable<?>>() {
    public String toString(final ReadableTable<?> table) {
      return table.getTableName();
    }
  };

  public ReadableTable() {
    this.alias=Alias.newTableAlias();
  }

  public ReadableTable(final String alias) {
    this.alias = Alias.newAlias(alias);
  }
  
  private List<Column<?, OBJ>> extractColumns() {
      final List<Column<?, OBJ>> columns = new ArrayList<Column<?, OBJ>>();
    ReflectUtils.processFields(getClass(), new FieldHandler() {
      @SuppressWarnings({"unchecked"})
      public boolean handleField(final Field field) throws Exception {
        if (!Column.class.isAssignableFrom(field.getType())) return true;
        final Column<?, OBJ> column = (Column<?, OBJ>) field.get(ReadableTable.this); // get the value of field
        columns.add(column);
        return true;
      }
    });
      return Collections.unmodifiableList(columns);
  }

  /** default name database table (== getType()) in all lowercase **/
  public String getTableName() {
    return getTableType().getSimpleName().toLowerCase();
  }

  public abstract Class<OBJ> getTableType();

  public String getAlias() {
    return alias.resolve(getContext());
  }
  
  public String getTableWithAliasSql() {
    return getTableName() + " " + getAlias();
  }

  /**
   * Return field names together with aliases. Create alias names if not generated already
   */
  public String getColumnsAsAliasSql() {
    final StringBuilder sb = new StringBuilder();
    for (final Column column : getColumns()) {
      sb.append(",").append(column.getColumnAsAliasSql());
    }
    return sb.length() == 0 ? "" : sb.substring(1);
  }

  /**
   * Return field names without aliases.
   */
  @Override
  public String getDefaultSql() {
    return StringUtil.join(getColumns(), Column.GET_SQL_STRING, "," );
  }
  
  public String getFromSql() {
    return getTableWithAliasSql();
  }
  
  public boolean isJoin() {    
    return false;
  }


  /**
   * Convert model object into a map of fields.
   * Key is sql of field and value is corresponding value from model object.
   * If includeNullValues is false, only non-null values are present in list.
   */
  public Map<Column, Object> getColumnValueMap(final OBJ modelObj, final boolean includeNullvalues) {
    final Map<Column, Object> columnValueMap = new LinkedHashMap<Column, Object>();

    final Map<String, Method> getterMap = BeanUtil.getGetters(getTableType());

    for (final Column field : getColumns()) {

      final Method getter = getterMap.get(field.getModelGetterName());
      final Object value = ReflectUtils.getValue(modelObj, getter);

      if (includeNullvalues || value != null) {
        columnValueMap.put(field, value);  
      }
    }
    return columnValueMap;
  }

    public Column<?, OBJ> getColumn(final String propertyName) {
        if (propertyName==null) throw new NullPointerException("propertyName");
        if (columns == null) columns = extractColumns();
        for (final Column<?, OBJ> column : columns) {
            if (column.getPropertyName().equals(propertyName)) return column;
        }
        throw new RuntimeException("Unknown column: "+propertyName);
    }


    public List<Column<?, OBJ>> getColumns() {
        if (columns == null) columns = extractColumns();
        return columns;
    }

    @Override
  public void setQueryContext(final QueryContext ctx) {
    super.setQueryContext(ctx);
    for (final Column field : getColumns()) {
      field.setQueryContext(ctx);
    }
  }
}