package squill.mgen;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael Hunger
 * @since 27.08.2008
 */
class TypeMap {
	public static Map<Integer, Class<?>> typeMap = new HashMap<Integer, Class<?>>();
	
	static {
		typeMap.put(Types.CHAR, String.class);
		typeMap.put(Types.VARCHAR, String.class);
		typeMap.put(Types.LONGVARCHAR, String.class);
		typeMap.put(Types.NUMERIC, BigDecimal.class);
		typeMap.put(Types.DECIMAL, BigDecimal.class);
		typeMap.put(Types.BIT, Boolean.class);
		typeMap.put(Types.BOOLEAN, Boolean.class);
		typeMap.put(Types.TINYINT, Byte.class);
		typeMap.put(Types.SMALLINT, Short.class);
		typeMap.put(Types.INTEGER, Integer.class);
		typeMap.put(Types.BIGINT, Long.class);
		typeMap.put(Types.REAL, Float.class);
		typeMap.put(Types.FLOAT, Double.class);
		typeMap.put(Types.DOUBLE, Double.class);
		typeMap.put(Types.BINARY, byte[].class);
		typeMap.put(Types.VARBINARY, byte[].class);
		typeMap.put(Types.LONGVARBINARY, byte[].class);
		typeMap.put(Types.DATE, Date.class);
		typeMap.put(Types.TIME, Time.class);
		typeMap.put(Types.TIMESTAMP, Timestamp.class);
		typeMap.put(Types.CLOB, Clob.class);
		typeMap.put(Types.BLOB, Blob.class);
		typeMap.put(Types.ARRAY, Array.class);
		typeMap.put(Types.STRUCT, Struct.class);
		typeMap.put(Types.REF, Ref.class);
		typeMap.put(Types.DATALINK, java.net.URL.class);
		typeMap.put(Types.OTHER, Object.class);
	}
	
	public static Class<?> getType(final DbColumn col) {
		if (col.getSqlType() == Types.DECIMAL) {
			return (col.hasScale() ? BigDecimal.class : Integer.class);
		}
		
		return typeMap.get(col.getSqlType());
	}
	
	public static String getTypeString(final DbColumn col) {
		Class<?> type = getType(col);
		if (type == null) {
			type = Object.class;
		}
		return type.getCanonicalName();
	}
}
