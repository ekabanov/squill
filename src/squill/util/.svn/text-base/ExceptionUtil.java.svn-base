package squill.util;

import java.lang.reflect.InvocationTargetException;

public abstract class ExceptionUtil {
  public static RuntimeException rethrow(Throwable e) {
    if (e instanceof Error)
      throw (Error) e;
    if (e instanceof RuntimeException)
      throw (RuntimeException) e;
    if (e instanceof InvocationTargetException)
      rethrow(((InvocationTargetException) e).getTargetException());
    throw new RuntimeException(e);
  }
}
