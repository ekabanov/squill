package squill.format;

import static squill.util.StringUtil.join;

import java.util.List;

import squill.query.FromPart;
import squill.query.OrderByPart;
import squill.query.QueryPart;
import squill.query.SelectPart;
import squill.query.WherePart;
import squill.query.from.FromExpression;
import squill.query.from.JoinElement;
import squill.query.orderby.OrderByElement;
import squill.query.select.ReadableTable;
import squill.query.select.SelectExpression;
import squill.query.where.WhereExpression;
import squill.util.ToString;



public class Sql92Format implements SqlFormat {

  public static final ToString<SelectExpression<?>> GET_SELECT_SQL = new ToString<SelectExpression<?>>() {
    public String toString(SelectExpression<?> value) {
      return FormatUtil.formatSelect(value);
    }
  };
  public static final ToString<FromExpression> GET_FROM_SQL = new ToString<FromExpression>() {
    public String toString(FromExpression table) {
      return table.getFromSql();
    }
  };
  public static final ToString<OrderByElement<?>> GET_SQL = new ToString<OrderByElement<?>>() {
    public String toString(OrderByElement<?> orderByElement) {
      return orderByElement.getDefaultSql();
    }
  };

  public String format(final QueryPart queryPart) {
    if (queryPart instanceof SelectPart) {
      return format((SelectPart) queryPart);
    }
    if (queryPart instanceof FromPart) {
      return format((FromPart) queryPart);
    }
    if (queryPart instanceof WherePart) {
      return format((WherePart) queryPart);
    }
    if (queryPart instanceof OrderByPart) {
      return format((OrderByPart) queryPart);
    }
    return "";
  }

  protected String format(final SelectPart selectPart) {
    final List<SelectExpression<?>> selectList = selectPart.getSelectList();
    return " SELECT " + join(selectList, GET_SELECT_SQL, ", ");
  }

  protected String format(final FromPart tablesPart) {
    final List<ReadableTable> tablesList = tablesPart.getTableList();
    final List<JoinElement> joinList = tablesPart.getJoinList();
    return " FROM " + join(tablesList, GET_FROM_SQL, ", ") + join(joinList, GET_FROM_SQL, "");
  }

  protected String format(final OrderByPart orderByPart) {
    final List<OrderByElement<?>> orderByList = orderByPart.getOrderByList();
    if (orderByList.isEmpty()) {
      return "";
    }
    return " ORDER BY " + join(orderByList, GET_SQL, ", ");
  }

  protected String format(final WherePart wherePart) {
    final WhereExpression whereClause = wherePart.getWhereClause();
    if (whereClause == null) {
      return "";
    }
    return " WHERE " + whereClause.getDefaultSql();
  }
}
