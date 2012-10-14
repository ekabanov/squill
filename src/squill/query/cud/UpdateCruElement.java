package squill.query.cud;

import static java.lang.String.format;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import squill.query.select.Column;
import squill.query.select.ReadableTable;

public class UpdateCruElement<OBJ> extends AbstractCUDElement<OBJ> implements UpdateElement<OBJ> {
	
	public UpdateCruElement(ReadableTable<OBJ> table, OBJ updateObj) {
		super(table, updateObj);
	}
	
	@Override
	protected String createSqlAndArgs(Map<Column, Object> columnValueMap, List<Object> argsList) {
		StringBuilder updateSql = new StringBuilder();
		
		for (Entry<Column, Object> entry : columnValueMap.entrySet()) {
			updateSql.append(format(", %s = ?", entry.getKey().getColumnNameWithTableSql()));
			argsList.add(entry.getValue());
		}
		
		return updateSql.substring(2);
	}
}
