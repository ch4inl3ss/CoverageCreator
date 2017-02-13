
package de.ch4inl3ss.coveragecreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class TestGeneratorTest {

	private static final String CORRECT_FILENAME = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/main/java/de/ch4inl3ss/coveragecreator/TestClass.java";

	private static final String REFERENCE_TESTCLASS = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/TestClassTest.java";
	private static final String EXPECTED_TESTFILE = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/java/de/ch4inl3ss/coveragecreator/TestClassTest.java";
	private static final String EXPECTED_XML = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello0.xml";

	private TestGenerator testGenerator;

	@Before
	public void setUp() {
		testGenerator = new TestGenerator();
	}

	@Test
	public void shouldFindMethodsOfTestClass() throws Exception {
		Method[] methods = TestClass.class.getMethods();
		List<Method> expectedMethods = new ArrayList<>();
		for (Method method : methods) {
			if (!ArrayUtils.contains(Object.class.getMethods(), method)) {
				expectedMethods.add(method);
			}
		}
		Method[] foundMethods = testGenerator.findMethodsOfSystemUnderTest(CORRECT_FILENAME);

		for (Method method : foundMethods) {
			if (ArrayUtils.contains(Object.class.getMethods(), method)) {
				fail();
			}
		}
		assertThat(expectedMethods.toArray(), is(equalTo(foundMethods)));
	}

	@Test
	public void shouldGenerateObjectArrayFile() throws Exception {
		File file = new File(EXPECTED_XML);
		testGenerator.writeParamterObjectArrayToXMLFile(CORRECT_FILENAME, new Object[] { "HALLO" },
				TestClass.class.getMethod("hello", String.class), 0);

		String string = FileUtils.readFileToString(file, Charset.defaultCharset());
		XStream xstream = new XStream(new StaxDriver());
		Object[] object = (Object[]) xstream.fromXML(string);
		assertThat(object[0], is(equalTo("HALLO")));
	}

	@Test
	public void shouldGenerateTestFile() throws Exception {
		testGenerator.generateTestFile(CORRECT_FILENAME);
		File referenceFile = new File(REFERENCE_TESTCLASS);
		List<String> referenceLines = FileUtils.readLines(referenceFile, Charset.defaultCharset());
		File generatedFile = new File(EXPECTED_TESTFILE);
		List<String> generatedLines = FileUtils.readLines(generatedFile, Charset.defaultCharset());
		assertThat(referenceLines.size(), is(equalTo(generatedLines.size())));
		for (int i = 0; i < referenceLines.size(); i++) {
			assertThat(referenceLines.get(i).trim(), is(equalTo(generatedLines.get(i).trim())));
		}

		// FileUtils.deleteQuietly(generatedFile);
	}
}
