package squill.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author Michael Hunger
 * @since 24.08.2008
 */
public class ReflectUtils {
    public static void setValue(Object target, Method setter, Object value) {
        try {
            setter.invoke(target, value);
        } catch (Exception e) {
            ExceptionUtil.rethrow(e);
        }
    }

    public static <OBJ> OBJ typeInstance(final Class<OBJ> type) {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw ExceptionUtil.rethrow(e);
        }
    }

    public static void processFields(Class<?> type, FieldHandler fieldHandler) {
        for (Field field : type.getFields()) {
            try {
                if (!fieldHandler.handleField(field)) break;
            }
            catch (Exception e) {
                ExceptionUtil.rethrow(e);
            }
        }
    }

    public static <TABLE> Object getValue(Object target, Method getter) {
        Object arg;
        try {
            arg = getter.invoke(target);
        } catch (Exception e) {
            throw ExceptionUtil.rethrow(e);
        }
        return arg;
    }
}
