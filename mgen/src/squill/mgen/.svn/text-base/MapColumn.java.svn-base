package squill.mgen;

import static java.lang.String.format;

import org.objectweb.asm.Type;

import squill.mgen.ClassInspectionUtil.ClassInfo;
import squill.mgen.ClassInspectionUtil.MethodInfo;
import squill.mgen.naming.NamingStrategy;
import squill.util.StringUtil;

public class MapColumn {
	
	private final DbColumn col;
	private final NamingStrategy namingStrategy;
	private String overrideClassName;
	private boolean hasProperty = false;
	
	@SuppressWarnings("unchecked")
	public MapColumn(DbColumn col, NamingStrategy namingStrategy, ClassInfo modelClass) {
		this.col = col;
		this.namingStrategy = namingStrategy;
		
		if (modelClass != null) {
			// Class proposed by typemap
			Class clazz = TypeMap.getType(col);
			System.out.println("searching for getter: " + "get" + getPropertyName());
			boolean hasGetter = modelClass.getMethods().keySet().contains("get" + getPropertyName());
			if (hasGetter) {
				MethodInfo methodInfo = modelClass.getMethods().get("get" + getPropertyName());
				// The classname used in existing bean
				String className = Type.getReturnType(methodInfo.getDesc()).getClassName();
				if (!className.equals(clazz.getCanonicalName())) {
					overrideClassName = className;
					System.out.println("The class for property " + getPropertyName() 
							+ " was overridden by class " + overrideClassName + " from existing model");
				}
			}
			
			boolean hasSetter = modelClass.getMethods().keySet().contains("set" + getPropertyName());
			
			hasProperty = hasGetter && hasSetter;
			
			if (hasGetter ^ hasSetter) {
				System.err.println("The model class '" + modelClass.getName() + "' does defines only a getter or a setter for the property '"
						+ getPropertyName() + "'.");
			}
		}
	}
	
	public String getName() {
		return col.getName();
	}
	
	public String getJavaName() {
		return namingStrategy.getFieldName(col.getTable().getName(), col.getName());
	}
	
	public String getPropertyName() {
		return StringUtil.capitalize(getJavaName());
	}
	
	public String getJavaType() {
		return overrideClassName != null ? overrideClassName : TypeMap.getTypeString(col);
	}
	
	public boolean isHasProperty() {
		return hasProperty;
	}
	
	public String genGetterSetter() {
		final String propertyName = getPropertyName();
		final String variableName = getJavaName();
		final String variableType = getJavaType();
		
		StringBuilder sb = new StringBuilder(200);
		
		sb.append(format("  public %s get%s() { return %s;}%n", variableType, propertyName, variableName));
		sb.append(format("  public void set%s(%s %3$s) { this.%3$s = %3$s; }%n%n", propertyName, variableType, variableName));
		
		return sb.toString();
	}
	
}
