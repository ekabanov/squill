package squill.query.cud;

import static squill.util.StringUtil.join;

import java.util.List;
import java.util.Map;

import squill.query.select.Column;
import squill.query.select.ReadableTable;
import squill.util.ToString;




public class InsertCruElement<OBJ> extends AbstractCUDElement<OBJ> implements InsertElement {
    private static final ToString QUESTION_MARK = new ToString() {
        public String toString(Object value) {
            return "?";
        }
    };


    public InsertCruElement(ReadableTable<OBJ> table, OBJ insertObj) {
        super(table, insertObj);
    }

    protected String createSqlAndArgs(Map<Column, Object> columnValueMap, List<Object> argsList) {
        for (Object value : columnValueMap.values()) {
            argsList.add(value);
        }
        StringBuilder sql = new StringBuilder();
        sql.append(" (" + join(columnValueMap.keySet(), Column.GET_NAME,", ") + ")");
        sql.append(" VALUES( " + join(columnValueMap.keySet(), QUESTION_MARK, ",") + ")");
        return sql.toString();
    }

}
