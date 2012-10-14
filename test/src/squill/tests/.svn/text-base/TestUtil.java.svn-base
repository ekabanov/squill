package squill.tests;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hsqldb.jdbc.jdbcDataSource;
import static org.junit.Assert.assertTrue;
import squill.db.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.lang.String.format;

public class TestUtil {
  private static jdbcDataSource dataSource = null;
  protected static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static Log log= LogFactory.getLog(TestUtil.class);

  public static Database getDefaultHsqlDatabase() {
    return getDefaultHsqlDatabase("initdb.sql");
  }

  public static Database getDefaultHsqlDatabase(final String initDbFile) {
    if (dataSource == null) {
      dataSource = new jdbcDataSource();
      // dataSource.setDatabase("jdbc:hsqldb:hsql://localhost/squilldemodb");
      dataSource.setDatabase("jdbc:hsqldb:mem:squilldemodb");
      dataSource.setUser("SA");
      initDb(dataSource, initDbFile);
    }
    return new Database(dataSource);
  }

  private static void initDb(jdbcDataSource dataSource, final String initDbFile) {
    Connection con = null;
    try {
      String result = readResource(initDbFile);
      con = dataSource.getConnection();
      final Statement stmt = con.createStatement();
      stmt.execute(result);
    } catch (SQLException sqle) {
      throw new RuntimeException("Error running init scripts", sqle);
    } finally {
      if (con != null) try {
        con.close();
      } catch (SQLException e) {
        // ignore
      }
    }
  }

  private static String readResource(final String resourceName) {
    try {
      final InputStream is = TestUtil.class.getResourceAsStream(resourceName);
      final BufferedReader br = new BufferedReader(new InputStreamReader(is));
      final char[] buffer = new char[1024 * 10];
      int read = 0;
      final StringBuilder sb = new StringBuilder();
      while ((read = br.read(buffer)) > 0) {
        sb.append(buffer, 0, read);
      }
      return sb.toString();
    } catch (IOException ioe) {
      throw new RuntimeException("Error reading file " + resourceName);
    }
  }

  public static void shutDownHsql() {
    if (dataSource != null) {
      Connection con = null;
      Statement stmt = null;
      try {
        con = dataSource.getConnection();
        stmt = con.createStatement();
        stmt.execute("shutdown");
      } catch (SQLException e) {
        if (log.isErrorEnabled()) log.error("Error shutting down hsql", e);
      } finally {
        Database.closeIt(con, stmt, null);
        dataSource = null;
      }
    }
  }

  public static Date date(final String dateString) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return dateFormat.parse(dateString);
    } catch (ParseException pe) {
      throw new RuntimeException("Error parsing date " + dateString + " with format " + dateFormat.toPattern());
    }
  }

  public static void assertContains(String msg, String expected, String actual) {
    final boolean contains = actual.contains(expected);
    if (!contains)
      assertTrue(format("%s: %s not contained in%n%s",msg,expected,actual), contains);
  }

  public static void assertRegexp(final String msg, final String expected, final String value) {
    assertTrue(msg+" regexp \nmatch failed:"+value+"\nshould match: "+expected,value.matches(expected));
  }
}
