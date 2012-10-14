package squill.mgen;

import static squill.tests.TestUtil.assertContains;

import java.sql.Types;

import org.junit.Before;
import org.junit.Test;

import squill.TestLogger;
import squill.mgen.naming.CamelCaseNaming;
import squill.util.FileUtil;

/**
 * @author Michael Hunger
 * @since 27.08.2008
 */
public class VelocityTableCodeGeneratorTest {
	private TableCodeGenerator tableCodeGenerator;
	private static final String TEST_PACKAGE = "squill.mgen.test";
	
	@Test
	public void singleFieldTest() {
		final DbTable table = new DbTable("TEST", false);
		final DbColumn nameColumn = new DbColumn(table, "NAME");
		nameColumn.setSqlType(Types.VARCHAR);
		table.addColumn(nameColumn);
		final MapTable mapTable = new MapTable(table, new CamelCaseNaming(), null, null, new TestLogger());
		final String javaCode = tableCodeGenerator.generateJavaTableCode(TEST_PACKAGE, mapTable);
		assertContains("package", "package " + TEST_PACKAGE, javaCode);
		assertContains("class", "class TestTable ", javaCode);
		assertContains("field", "private java.lang.String name;", javaCode);
		assertContains("getter", "public java.lang.String getName() { return name; }", javaCode);
		assertContains("setter", "public void setName(java.lang.String name) { this.name = name; }", javaCode);
		assertContains("table class", "public static class TestTable extends WritableTable<TestData> {", javaCode);
		assertContains("table name", "public String getTableName() { return \"test\"; }", javaCode);
		assertContains("table type", "public Class<TestData> getTableType() { return TestData.class; }", javaCode);
		assertContains("column declaration", "public final Column<java.lang.String, TestData> name =", javaCode);
		assertContains("column instance", "new Column<java.lang.String, TestData>(\"NAME\", java.lang.String.class, \"name\", this);", javaCode);
	}
	
	@Before
	public void createGenerator() throws Exception {
		tableCodeGenerator = new VelocityTableCodeGenerator(FileUtil.path(VelocityTableCodeGenerator.class) + "Model");
	}
}