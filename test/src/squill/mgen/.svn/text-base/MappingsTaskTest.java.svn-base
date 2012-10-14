package squill.mgen;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import squill.tests.TestUtil;
import squill.db.Database;

import java.io.*;
import static java.util.Arrays.asList;
import java.util.Collections;
import java.util.List;

/**
 * @author Michael Hunger
 * @since 28.08.2008
 */
public class MappingsTaskTest {
  private static final String OUTPUT_PATH = "build/tmp";
  private Database database;
  @Test
  public void generatePetclinicMapping() {
    database = TestUtil.getDefaultHsqlDatabase("petclinic.sql");
    testCreateMapping("org.springframework.samples.petclinic.squill"
    ,"PetsData","OwnersData","SpecialtiesData","TypesData","VetSpecialtiesData","VisitsData","VetsData");
  }

  @Test
  public void generateSimpleMapping() {
    database = TestUtil.getDefaultHsqlDatabase();
    testCreateMapping("demo.model","CustomerData", "ComplaintData");
  }


  private void testCreateMapping(final String pkg,String...expectedFiles) {
    final String path = OUTPUT_PATH + File.separator + pkg.replace('.', File.separatorChar);
    for (final File file : getFiles(path)) {
      file.delete();
    }
    final SquillMappingsTask task = new SquillMappingsTask();
    task.setDriver("org.hsqldb.jdbcDriver");
    task.setUrl("jdbc:hsqldb:mem:squilldemodb");

    task.setPackageName(pkg);
    task.setOutputPath(OUTPUT_PATH);

    task.setUser("SA");
    task.setPassword("");
    task.setSchema("PUBLIC");

    task.execute();
    final List<File> files = getFiles(path);
    final List<String> expectedNames = asList(expectedFiles);
    assertEquals(expectedNames.size(), files.size());
    for (final File file : files) {
      String fileName = file.getName();
      fileName = fileName.substring(0,fileName.lastIndexOf('.'));
      assertTrue("File exists " + fileName, expectedNames.contains(fileName));
      final String contents = readFile(file);
      // todo assertions per file
    }
  }

  private String readFile(final File file) {
    try {
      final Reader reader = new BufferedReader(new FileReader(file));
      final char[] buffer = new char[1024 * 10];
      int count;
      final StringBuilder sb = new StringBuilder();
      while ((count = reader.read(buffer)) >= 0) {
        sb.append(buffer, 0, count);
      }
      return sb.toString();
    } catch (IOException ioe) {
      throw new RuntimeException("Error reading file", ioe);
    }
  }



  private List<File> getFiles(final String path) {
    final File filePath = new File(path);
    final File[] files = filePath.listFiles(new JavaFilter());
    if (files == null) return Collections.emptyList();
    return asList(files);
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() {
    TestUtil.shutDownHsql();
  }

  private static class JavaFilter implements FilenameFilter {
    public boolean accept(final File dir, final String name) {
      return name.endsWith(".java");
    }
  }
}
