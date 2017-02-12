package de.ch4inl3ss.coveragecreator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import de.ch4inl3ss.coveragecreator.testclasses.ComplexType;

public class InvokerTest {

	private static final String CORRECT_FILENAME = "/Users/Felix/git/CoverageCreator/CoverageCreator/src/main/java/de/ch4inl3ss/coveragecreator/TestClass.java";

	private Invoker invoker;

	@Before
	public void setUp() {
		invoker = new Invoker();
	}

	@Test
	public void shouldCreateAndFillComplexTypes() throws Exception {
		List<Object[]> result = invoker.findParamterListForMethod(
				TestClass.class.getMethod("changeComplexType", ComplexType.class), CORRECT_FILENAME);
		assertThat(result.get(0), is(instanceOf(ComplexType.class)));

	}

	@Test
	public void shouldFindCorrectParameterListForMethod() throws Exception {
		List<Object[]> result = invoker.findParamterListForMethod(TestClass.class.getMethod("fib", int.class),
				CORRECT_FILENAME);
		System.out.println(ReflectionToStringBuilder.toString(result.get(0)));
		result = invoker.findParamterListForMethod(TestClass.class.getMethod("hello", String.class), CORRECT_FILENAME);
		assertThat("test", is(equalTo(result.get(0)[0])));
		assertThat("asdf", is(equalTo(result.get(1)[0])));
		assertThat("hello", is(equalTo(result.get(2)[0])));
		assertThat("bye", is(equalTo(result.get(3)[0])));
	}

	@Test
	public void shouldFindStringsInClass() throws Exception {
		Object[] stringsInClass = invoker.findStringsInClass(CORRECT_FILENAME);
		assertThat(stringsInClass.length, is(equalTo(4)));

		assertThat(stringsInClass[0], is(equalTo("test")));
		assertThat(stringsInClass[1], is(equalTo("asdf")));
		assertThat(stringsInClass[2], is(equalTo("hello")));
		assertThat(stringsInClass[3], is(equalTo("bye")));
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
