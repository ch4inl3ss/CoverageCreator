package de.ch4inl3ss.coveragecreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class ClassServiceTest {

	private static final String REAL_FILE = "/Users/Felix/git/legacyExample/legacyExample/src/main/java/de/ch4inl3ss/aktivitaet/Aktivitaet.java";

	private static final String REAL_CLASSNAME = "de.ch4inl3ss.aktivitaet.Aktivitaet";

	private ClassService classService;

	@Before
	public void setUp() {
		classService = new ClassService();
	}

	@Test
	public void shouldFindClassDirectory() throws Exception {
		String classFile = classService.findClassFileDirectorToFile(REAL_FILE);
		assertThat(classFile, is(equalTo("/Users/Felix/git/legacyExample/legacyExample/target/classes/")));
	}

	@Test
	public void shouldFindClassFileToFile() throws Exception {
		String classFile = classService.findClassFileToFile(REAL_FILE);
		assertThat(classFile, is(equalTo(
				"/Users/Felix/git/legacyExample/legacyExample/target/classes/de/ch4inl3ss/aktivitaet/Aktivitaet.class")));
	}

	@Test
	public void shouldFindClassMethodsWichAreNotInClassPath() {
		Method[] methods = classService.findMethods(REAL_FILE, REAL_CLASSNAME);

		assertThat(methods.length, is(equalTo(1)));
		assertThat(methods[0].getName().equals("ausfuehrenImplementierung"), is(true));
	}
}
