package squill.mgen;

/**
 * @author Michael Hunger
 * @since 27.08.2008
 */
public interface TableCodeGenerator {
  String generateJavaTableCode(String packageName, MapTable mapTable);
}
