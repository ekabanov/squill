package squill.api;

import squill.Executable;

/**
 * @author mh14 @ jexp.de
 * @since 31.08.2008 11:14:20 (c) 2008 jexp.de
 */
@java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@java.lang.annotation.Target({java.lang.annotation.ElementType.METHOD})
public @interface Query {
  Class<? extends Executable> value();
}
