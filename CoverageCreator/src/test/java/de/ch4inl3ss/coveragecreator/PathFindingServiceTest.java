package de.ch4inl3ss.coveragecreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PathFindingServiceTest {

	private static final String CORRECT_FILENAME = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/main/java/de/ch4inl3ss/coveragecreator/TestClass.java";
	private static final String ANOTHER_CORRECT_FILENAME = "/Users/Felix/git/legacyExample/legacyExample/src/main/java/de/ch4inl3ss/aktivitaet/Aktivitaet.java";
	private static final String FALSE_FILENAME_1 = "/de/ch4inl3ss/coveragecreator/TestClass.java";
	private static final String FALSE_FILENAME_2 = "src/main/java/de/ch4inl3ss/coveragecreator/TestClass";

	private static final String XML_PATH = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/";
	private static final String ANOTHER_XML_PATH = "/Users/Felix/git/legacyExample/legacyExample/src/test/resources/";

	private PathFindingService pathFindingService;

	@Before
	public void setUp() {
		pathFindingService = new PathFindingService();
	}

	@Test
	public void shouldCheckFileName() throws Exception {
		boolean isFileNameCorrect = pathFindingService.checkFileName(FALSE_FILENAME_1);
		assertThat(isFileNameCorrect, is(false));
		isFileNameCorrect = pathFindingService.checkFileName(FALSE_FILENAME_2);
		assertThat(isFileNameCorrect, is(false));
	}

	@Test
	public void shouldFindAbsolutePath() throws Exception {
		String absolutePath = pathFindingService.findAbsolutePath(CORRECT_FILENAME);
		assertThat(absolutePath, is(equalTo("/Users/Felix/git/CoverageCreator/CoverageCreator/")));
	}

	@Test
	public void shouldFindClassName() throws Exception {
		String className = pathFindingService.findClassName(CORRECT_FILENAME);
		assertThat(className, is(equalTo("TestClass.java")));
	}

	@Test
	public void shouldFindClassNameWithoudFileExtension() throws Exception {
		String className = pathFindingService.findClassNameWithoutFileExtension(CORRECT_FILENAME);
		assertThat(className, is(equalTo("TestClass")));
	}

	@Test
	public void shouldFindFilePathToGenerateTo() throws Exception {
		String filePathToGenerateTo = pathFindingService.findFilePathToGenerateTo(CORRECT_FILENAME);
		assertThat(filePathToGenerateTo, is(equalTo(
				"/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/java/de/ch4inl3ss/coveragecreator/TestClassTest.java")));
	}

	@Test
	public void shouldFindPackageName() throws Exception {
		String packageName = pathFindingService.findPackageName(CORRECT_FILENAME);
		assertThat(packageName, is(equalTo("de.ch4inl3ss.coveragecreator")));
	}

	@Test
	public void shouldFindPathForXML() throws Exception {
		String pathForXML = pathFindingService.findPathForXML(CORRECT_FILENAME);
		assertThat(pathForXML, is(equalTo(XML_PATH)));
		pathForXML = pathFindingService.findPathForXML(ANOTHER_CORRECT_FILENAME);
		assertThat(pathForXML, is(equalTo(ANOTHER_XML_PATH)));

	}
}
