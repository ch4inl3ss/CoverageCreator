package de.ch4inl3ss.coveragecreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.ch4inl3ss.coveragecreator.constants.Parameters;

public class InvokerTest {

	private static final String CORRECT_FILENAME = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/main/java/de/ch4inl3ss/coveragecreator/TestClass.java";

	private Invoker invoker;

	@Before
	public void setUp() {
		invoker = new Invoker();
	}

	@Test
	public void shouldCreateAndFillComplexTypes() throws Exception {

	}

	@Test
	public void shouldFindCorrectParameterListForMethod() throws Exception {
		List<Object[]> result = invoker.findParamterListForMethod(TestClass.class.getMethod("fib", int.class),
				CORRECT_FILENAME);
		assertThat(Arrays.deepEquals(result.get(0), Parameters.intValues), is(true));
		result = invoker.findParamterListForMethod(TestClass.class.getMethod("hello", String.class), CORRECT_FILENAME);
		assertThat("hello".equals(result.get(0)[0]), is(true));
		assertThat("bye".equals(result.get(0)[1]), is(true));
	}

	@Test
	public void shouldFindStringsInClass() throws Exception {
		Object[] stringsInClass = invoker.findStringsInClass(CORRECT_FILENAME);
		assertThat(stringsInClass.length, is(equalTo(2)));
		assertThat(stringsInClass[0], is(equalTo("hello")));
		assertThat(stringsInClass[1], is(equalTo("bye")));
	}

	@Test
	public void shouldInvokeMethod() throws Exception {
		Object result = invoker.invokeMethod(TestClass.class, TestClass.class.getMethod("fib", int.class), 5);
		assertThat(result, is(instanceOf(Long.class)));
		assertThat(8l == (Long) result, is(true));
	}

	@Test
	public void shouldInvokeMethodWithParameterList() throws Exception {
		Object result = invoker.invokeMethod(TestClass.class, TestClass.class.getMethod("fib", int.class),
				Integer.MAX_VALUE);
		assertThat(result, is(instanceOf(StackOverflowError.class)));

	}
}
