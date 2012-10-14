package squill.generate;

import static squill.util.FileUtil.path;

import java.util.Collections;
import java.io.File;

import squill.util.FileUtil;


public class OrderByBuilderGenerator {
  private final int tuples;

  public OrderByBuilderGenerator(final int tuples) {
    this.tuples = tuples;
  }

  public static void main(String[] args) {
    int tuples=10;
    if (args != null && args.length == 1) tuples = Integer.valueOf(args[1]);
    new OrderByBuilderGenerator(tuples).generate();
  }

  private void generate() {
    final String template = path(getClass()) + "OrderByBuilder";
    final VelocityGenerator generator = new VelocityGenerator();
    final String orderByBuilder = generator.generate(template, Collections.singletonMap("tupleCount", tuples));
    final File javaFile = FileUtil.javaFile("src", "squill.api.builder", "OrderByBuilder");
    FileUtil.writeFile(orderByBuilder,javaFile);
  }
}
