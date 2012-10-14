package squill.format;

import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.query.select.SelectExpression;
import squill.query.select.SimpleSelectExpression;

public final class FormatUtil {
  private FormatUtil() {}
  
  public static String formatOrderBy(SelectExpression selExpr) {
    if (selExpr instanceof Column) {
      return ((Column) selExpr).getColumnNameWithTableSql();
    }
    else if (selExpr instanceof SimpleSelectExpression) {
      return ((SimpleSelectExpression) selExpr).getAlias();
    }
    else throw new IllegalArgumentException();
  }
  
  public static String formatSelect(SelectExpression selExpr) {
    if (selExpr instanceof Column) {
      return ((Column) selExpr).getColumnAsAliasSql();
    }
    else if (selExpr instanceof ReadableTable) {
      return ((ReadableTable) selExpr).getColumnsAsAliasSql();
    }
    else if (selExpr instanceof SimpleSelectExpression) {
      return ((SimpleSelectExpression) selExpr).getAsAliasSql();
    }
    else throw new IllegalArgumentException();
  }
}
