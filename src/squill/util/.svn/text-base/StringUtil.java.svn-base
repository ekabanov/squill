package squill.util;

/**
 * String helper methods.
 */
public class StringUtil {

    /**
     * Converts the first letter of a String into upper case.
     *
     * @param in input String.
     * @return String with first letter in upper case.
     */
    public static String capitalize(String in) {
        return in.substring(0, 1).toUpperCase() + in.substring(1);
    }
    
    /**
     * Converts the first letter of a String into lower case.
     *
     * @param in input String.
     * @return String with first letter in lower case.
     */
    public static String decapitalize(String in) {
      return in.substring(0, 1).toLowerCase() + in.substring(1);
  }

    /**
     * Converts a String into came case.
     * The first letter will be in upper case.
     * <p>
     * Underscore will be removed and each word will
     * start with an upper case letter.
     * </p>
     * <p/>
     * For example:
     * <pre>
     * toUpperCamelCase("first_name") = "FirstName"
     * toUpperCamelCase("First_name") = "FirstName"
     * </pre>
     *
     * @param in input String.
     * @return String in camel case.
     */
    public static String toUpperCamelCase(String in) {
        return toCamelCase(in, true);
    }

    /**
     * Converts a String into came case.
     * The first letter will be in lower case.
     * <p>
     * Underscore will be removed and each word will
     * start with an upper case letter.
     * </p>
     * <p/>
     * For example:
     * <pre>
     * toLowerCamelCase("first_name") = "firstName"
     * toLowerCamelCase("First_name") = "firstName"
     * </pre>
     *
     * @param in input String.
     * @return String in camel case.
     */
    public static String toLowerCamelCase(String in) {
        return toCamelCase(in, false);
    }

    /**
     * Converts a String into camel case.
     * <p>
     * Underscore will be removed and each word will
     * start with an upper case letter.
     * </p>
     * <p>
     * The first letter of the String will be capitalized
     * if the <code>upperCC</code> is true.
     * </p>
     * <p/>
     * For example:
     * <pre>
     * toCamelCase("first_name", false) = "firstName"
     * toCamelCase("first_name", true) = "FirstName"
     * </pre>
     *
     * @param in      input String.
     * @param upperCC whether to capitalize the first letter of the String.
     * @return String in camel case.
     */
    private static String toCamelCase(String in, boolean upperCC) {
        StringBuilder camelCased = new StringBuilder(in.length());
        boolean up = upperCC;

        for (int i = 0; i < in.length(); i++) {
            char ch = in.charAt(i);
            if (ch == '_') {
                up = true;
                continue;
            }
            if (up) {
                ch = Character.toUpperCase(ch);
            } else {
                ch = Character.toLowerCase(ch);
            }
            camelCased.append(ch);
            up = false;
        }
        return camelCased.toString();
    }

    /**
     * Converts a CamelCase string into upper case string with underscores.
     * <p>
     * Underscore will be removed and words will
     * be separated with underscores ("_")
     * </p>
     * <p/>
     * For example:
     * <pre>
     * camelCase2Underscores("firstName") = "FIRST_NAME"
     * camelCase2Underscores("FirstName") = "FIRST_NAME"
     * </pre>
     *
     * @param in      input String.
     * @return Uppercased String with underscores separating words.
     */
    public static String camelCase2Underscores(String in) {
      StringBuilder underscored = new StringBuilder(in.length()+5);
      
      for (int i = 0; i < in.length(); i++) {
        char ch = in.charAt(i);
        if (Character.isUpperCase(ch)) {
          underscored.append("_");
        }
        underscored.append(ch);
      }
      return underscored.toString().toUpperCase();
    }

    public static String join(Iterable<?> values, String delim) {
        return join(values, ToString.NOOP, delim);
    }

    public static String join(Iterable<?> values, ToString toString, String delim) {
        if (values == null) return "";
        StringBuilder sb = new StringBuilder();
        for (Object value : values) {
            sb.append(delim).append(toString == null ? value : toString.toString(value));
        }
        return  (sb.length()<delim.length()) ? "" : sb.substring(delim.length());
    }

}
