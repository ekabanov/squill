package squill.generate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.String.format;

public class TupleGenerator {
  private final String path;
  private final String pkg;
  private final int count;

  public TupleGenerator(final String path, String pkg, final int count) {
    this.path = path;
    this.pkg = pkg;
    this.count = count;
  }

  public static void main(final String[] args) {
    //new TupleGenerator("src", "squill.api.tuple", 2).generate();
    new TupleGenerator(args[0], args[1], Integer.parseInt(args[2])).generate();
  }

  public void generate() {
    for (int i = 1; i <= count; i++) {
      final String fileContents = generate(i);
      final String fileName = fileName(i);
      writeFile(fileContents, fileName);
    }
  }

  protected String fileName(int i) {
    return path + "/" + pkg.replace('.', '/') + "/Tuple" + i + ".java";
  }


  private void writeFile(final String contents, final String fileName) {
    try {
      final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
      writer.write(contents);
      writer.close();
    } catch (IOException ioe) {
      throw new RuntimeException("Error writing file " + fileName, ioe);
    }
  }

  public String generate(final int i) {
    final StringBuilder sb = new StringBuilder();
    sb.append(format("package %s;%n", getPackage()));
    sb.append(format("public class Tuple%d<%s> implements Tuple {%n", i, gen(i, "T%d", ",")));
    sb.append(gen(i, "  public final T%1$d v%1$d;%n", ""));
    sb.append(format("  public Tuple%d(%s) {%n  %s  }%n", i, gen(i, "T%1$d v%1$d", ","),
        gen(i, "  this.v%1$d = v%1$d;%n", "")
    ));
    sb.append(format("  public static <%2$s> Tuple%1$d<%2$s> _(%3$s) {%n    return new Tuple%1$d<%2$s>(%4$s);%n  }%n",
        i, gen(i, "P%d", ","),
        gen(i, "P%1$d v%1$d", ", "),
        gen(i, "v%d", ", ")
    ));
    sb.append(format("@Override%npublic String toString() {%n return \"(\"+%s+\")\";%n}%n", gen(i, "v%d", " + \", \" + ")));
    sb.append(format(
        "  @Override %n" +
            "  public boolean equals(Object o) { %n" +
            "    if (this == o) return true; %n" +
            "    if (o == null || getClass() != o.getClass()) return false; %n" +
            "    Tuple%1$d tuple = (Tuple%1$d) o; %n" +
            "    %2$s " +
            "    return true; %n" +
            "  }%n", i, gen(i, "    if (v%1$d == null ? tuple.v%1$d != null : !v%1$d.equals(tuple.v%1$d)) return false; %n", "")));
    sb.append(format(
        "  @Override %n" +
            "  public int hashCode() { %n" +
            "    int result = 0; %n" +
            "  %s " +
            "    return result;%n" +
            "  }%n", gen(i, "    result = 31 * result + (v%1$d != null ? v%1$d.hashCode() : 0);%n", "")));
    sb.append("}");
    return sb.toString();
  }

  private String gen(final int count, final String pattern, final String delim) {
    if (count < 1) return "";
    final StringBuilder sb = new StringBuilder();
    for (int i = 1; i <= count; i++) {
      sb.append(delim).append(format(pattern, i));
    }
    return sb.length() > delim.length() ? sb.substring(delim.length()) : sb.toString();
  }

  private String getPackage() {
    return pkg;
  }

}
/*
  @Override
  public int hashCode() {
    int result = v1 != null ? v1.hashCode() : 0;
    result = 31 * result + (v2 != null ? v2.hashCode() : 0);
    return result;
  }
}
*/