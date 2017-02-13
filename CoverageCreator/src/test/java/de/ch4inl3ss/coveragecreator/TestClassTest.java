package de.ch4inl3ss.coveragecreator;

import java.lang.String;
import de.ch4inl3ss.coveragecreator.testclasses.ComplexType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import java.io.File;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class TestClassTest {

private TestClass testclass;

private XStream xstream;

@Before
public void setUp() {
testclass = new TestClass();
xstream = new XStream(new StaxDriver());
}

@Test
public void testChangeComplexType() throws Exception {
Object[] params;
Method testMethod = testclass.getClass().getMethod("changeComplexType",ComplexType.class );
Object result;
Object expectedResult;
File fileParam0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType0.xml");
File fileResult0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult0.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam0, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult0, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType1.xml");
File fileResult1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult1.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam1, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult1, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType2.xml");
File fileResult2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult2.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam2, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult2, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType3.xml");
File fileResult3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult3.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam3, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult3, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType4.xml");
File fileResult4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult4.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam4, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult4, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType5.xml");
File fileResult5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult5.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam5, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult5, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType6.xml");
File fileResult6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult6.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam6, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult6, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexType7.xml");
File fileResult7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/changeComplexTypeResult7.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam7, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult7, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}

}

@Test
public void testFib() throws Exception {
Object[] params;
Method testMethod = testclass.getClass().getMethod("fib",int.class );
Object result;
Object expectedResult;
File fileParam0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib0.xml");
File fileResult0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult0.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam0, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult0, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib1.xml");
File fileResult1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult1.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam1, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult1, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib2.xml");
File fileResult2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult2.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam2, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult2, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib3.xml");
File fileResult3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult3.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam3, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult3, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib4.xml");
File fileResult4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult4.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam4, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult4, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib5.xml");
File fileResult5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult5.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam5, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult5, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib6.xml");
File fileResult6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult6.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam6, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult6, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fib7.xml");
File fileResult7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/fibResult7.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam7, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult7, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}

}

@Test
public void testHello() throws Exception {
Object[] params;
Method testMethod = testclass.getClass().getMethod("hello",String.class );
Object result;
Object expectedResult;
File fileParam0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello0.xml");
File fileResult0 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult0.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam0, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult0, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello1.xml");
File fileResult1 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult1.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam1, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult1, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello2.xml");
File fileResult2 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult2.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam2, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult2, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello3.xml");
File fileResult3 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult3.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam3, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult3, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello4.xml");
File fileResult4 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult4.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam4, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult4, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello5.xml");
File fileResult5 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult5.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam5, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult5, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello6.xml");
File fileResult6 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult6.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam6, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult6, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}
File fileParam7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/hello7.xml");
File fileResult7 = new File("/Users/Felix/git/CoverageCreator/CoverageCreator/src/test/resources/helloResult7.xml");
params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam7, Charset.defaultCharset()));
expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult7, Charset.defaultCharset()));
try {
result = testMethod.invoke(testclass, params);
assertThat(result, is(equalTo(expectedResult)));
} catch (Exception | Error e) {
result = e;
}

}

}
