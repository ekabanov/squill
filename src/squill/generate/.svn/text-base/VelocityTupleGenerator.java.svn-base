package squill.generate;

import java.io.File;
import static java.lang.String.format;
import static squill.util.FileUtil.javaFile;
import static squill.util.FileUtil.path;
import static squill.util.FileUtil.writeFile;

import java.util.Map;
import java.util.HashMap;


public class VelocityTupleGenerator {
  private final String path;
  private final String packageName;
  private final int count;
  private final VelocityGenerator velocityGenerator;

  public VelocityTupleGenerator() {
    this("src", "squill.api.tuple", 10);
  }
  public VelocityTupleGenerator(final String path, String packageName, final int count) {
    this.path = path;
    this.packageName = packageName;
    this.count = count;
    velocityGenerator = new VelocityGenerator();
  }

  public static void main(final String[] args) {

    final VelocityTupleGenerator generator;
    if (args == null || args.length == 0)
      generator = new VelocityTupleGenerator();
    else
      generator = new VelocityTupleGenerator(args[0], args[1], Integer.parseInt(args[2]));
    generator.generate();
  }

  public void generate() {
    for (int i = 1; i <= count; i++) {
      final String fileContents = generate(i);
      writeFile(fileContents, fileName(i));
    }
  }

  public File fileName(final int i) {
    return javaFile(path, packageName,"Tuple"+i);
  }

  public String generate(final int i) {
    final Map<String,Object> inputs = new HashMap<String, Object>();
    inputs.put("i",i);
    inputs.put("pkg", packageName);
    return velocityGenerator.generate(path(getClass())+"Tuple", inputs);
  }
}