package squill.util;

/**
 * @author Michael Hunger
 * @since 24.08.2008
 */
public interface ToString<T> {
    ToString NOOP = new ToString<Object>() {
        public String toString(Object value) {
            return value != null ? value.toString() : "null";
        }
    };

    String toString(T value);
}
