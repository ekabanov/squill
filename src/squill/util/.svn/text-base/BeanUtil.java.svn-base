/**
 *
 */
package squill.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Reflection helper methods.
 */
public class BeanUtil {

    /**
     * Returns all getters of the specified class.
     *
     * @param type class.
     * @return all getters by the method names.
     */
    public static Map<String, Method> getGetters(Class type) {
        Map<String, Method> getMethods = new HashMap<String, Method>();

        for (Method method : type.getMethods()) {
            String name = method.getName();
            if (paramCount(method) == 0 && (name.startsWith("get") || name.startsWith("is"))) {
                getMethods.put(name, method);
            }
        }
        return getMethods;
    }

    private static int paramCount(Method method) {
        return method.getParameterTypes().length;
    }

    /**
     * Returns all setters of the specified class.
     *
     * @param type class.
     * @return all setters by the method names.
     */
    public static Map<String, Method> getSetters(Class type) {
        Map<String, Method> setMethods = new HashMap<String, Method>();

        for (Method method : type.getMethods()) {
            String name = method.getName();
            if (paramCount(method) == 1 && name.startsWith("set")) {
                setMethods.put(name, method);
            }
        }
		return setMethods;
	}

}
