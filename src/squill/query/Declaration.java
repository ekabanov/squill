package squill.query;

import java.util.Map;

public class Declaration<T> {
  private final Class<T> type;
  private final String name;

  public Declaration(final Class<T> type, final String name) {
    this.type = type;
    this.name = name;
  }

  public Class<T> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public T resolve(Map<String, ? extends Object> ctx) {
    final Object result = ctx.get(name);
    if (type.isInstance(result))
      return type.cast(result);
    else
      throw new IllegalArgumentException("Could not find declaration " + this + " in context");
  }

  @Override public String toString() {
    return type.getSimpleName()+" "+name;
  }
}
