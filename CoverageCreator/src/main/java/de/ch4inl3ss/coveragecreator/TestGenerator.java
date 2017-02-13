package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class TestGenerator {

	private PathFindingService pathFindingService = new PathFindingService();

	private ClassService classService = new ClassService();

	private Invoker invoker = new Invoker();

	public Method[] findMethodsOfSystemUnderTest(String fileName) {
		return new ClassService().findMethods(fileName, pathFindingService.findPackageName(fileName) + "."
				+ pathFindingService.findClassNameWithoutFileExtension(fileName));
	}

	private void generateClassEnd(List<String> lines) {
		lines.add("}");
	}

	private void generateClassStart(String fileName, List<String> lines) {
		lines.add("public class " + pathFindingService.findClassNameWithoutFileExtension(fileName) + "Test" + " {");
		lines.add("");
	}

	private void generateFields(List<String> lines) {
		lines.add("private XStream xstream;");
		lines.add("");
	}

	private void generateImports(List<String> lines) {
		lines.add("import static org.hamcrest.MatcherAssert.assertThat;");
		lines.add("import static org.hamcrest.CoreMatchers.is;");
		lines.add("import static org.hamcrest.CoreMatchers.equalTo;");
		lines.add("import java.io.File;");
		lines.add("import java.lang.reflect.Method;");
		lines.add("import java.nio.charset.Charset;");
		lines.add("import org.apache.commons.io.FileUtils;");
		lines.add("import org.junit.Before;");
		lines.add("import org.junit.Test;");
		lines.add("import com.thoughtworks.xstream.XStream;");
		lines.add("import com.thoughtworks.xstream.io.xml.StaxDriver;");
		lines.add("");
	}

	private void generatePackageDeclaration(String fileName, List<String> lines) {
		lines.add("package " + pathFindingService.findPackageName(fileName) + ";");
		lines.add("");
	}

	private void generateSetup(String fileName, List<String> lines) {
		lines.add("@Before");
		lines.add("public void setUp() {");
		lines.add(getSystemUnderTestVariableName(fileName) + " = new "
				+ pathFindingService.findClassNameWithoutFileExtension(fileName) + "();");
		lines.add("xstream = new XStream(new StaxDriver());");
		lines.add("}");
		lines.add("");
	}

	private void generateSystemUnderTest(String fileName, List<String> lines) {
		lines.add("private " + pathFindingService.findClassNameWithoutFileExtension(fileName) + " "
				+ getSystemUnderTestVariableName(fileName) + ";");
		lines.add("");
	}

	public void generateTestFile(String fileName) throws IOException {
		File outputFile = new File(pathFindingService.findFilePathToGenerateTo(fileName));
		if (outputFile.exists()) {
			FileUtils.deleteQuietly(outputFile);
		}

		LinkedList<String> lines = new LinkedList<>();
		generatePackageDeclaration(fileName, lines);
		generateImports(lines);
		generateClassStart(fileName, lines);
		generateSystemUnderTest(fileName, lines);
		generateFields(lines);
		generateSetup(fileName, lines);
		Method[] methodsOfSystemUnderTest = findMethodsOfSystemUnderTest(fileName);
		sortMethods(methodsOfSystemUnderTest);
		for (Method method : methodsOfSystemUnderTest) {
			generateTestMethod(lines, method, fileName);
		}

		generateClassEnd(lines);

		FileUtils.writeLines(outputFile, lines);

	}

	private void generateTestMethod(LinkedList<String> lines, Method method, String fileName) {
		lines.add("@Test");
		lines.add("public void test" + turnFirstLetterToUpperCase(method.getName()) + "() throws Exception {");
		lines.add("Object[] params;");

		String parameterClasses = getMethodParameterClassNamesAsString(method, lines);

		lines.add("Method testMethod = " + getSystemUnderTestVariableName(fileName) + ".getClass().getMethod(\""
				+ method.getName() + "\"," + parameterClasses + " );");
		lines.add("Object result;");
		lines.add("Object expectedResult;");

		// each array in this list is a separate testcase with an assert
		List<Object[]> paramterListForMethod = invoker.findParamterListForMethod(method, fileName);

		for (int i = 0; i < paramterListForMethod.size(); i++) {
			writeParamterObjectArrayToXMLFile(fileName, paramterListForMethod.get(i), method, i);

			// Invoke the method to get result
			Object resultObject = invoker.invokeMethod(method.getDeclaringClass(), method,
					paramterListForMethod.get(i));
			writeResultObjectToXMLFile(fileName, resultObject, method, i);

			lines.add("File fileParam" + i + " = new File(\"" + pathFindingService.findPathForXML(fileName)
					+ method.getName() + i + ".xml\");");

			lines.add("File fileResult" + i + " = new File(\"" + pathFindingService.findPathForXML(fileName)
					+ method.getName() + "Result" + i + ".xml\");");

			lines.add("params =  (Object[]) xstream.fromXML(FileUtils.readFileToString(fileParam" + i
					+ ", Charset.defaultCharset()));");
			lines.add("expectedResult =  (Object) xstream.fromXML(FileUtils.readFileToString(fileResult" + i
					+ ", Charset.defaultCharset()));");
			lines.add("try {");
			lines.add("result = testMethod.invoke(" + getSystemUnderTestVariableName(fileName) + ", params);");

			// generate assert-statement
			lines.add("assertThat(result, is(equalTo(expectedResult)));");

			lines.add("} catch (Exception | Error e) {");
			lines.add("result = e;");
			lines.add("}");
		}
		lines.add("");
		lines.add("}");
		lines.add("");
	}

	private String getMethodParameterClassNamesAsString(Method method, LinkedList<String> lines) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		String parameterClasses = "";
		for (Class<?> clazz : parameterTypes) {
			insertImportIfNotThereYet(lines, clazz);
			parameterClasses += clazz.getSimpleName() + ".class,";
		}
		parameterClasses = parameterClasses.substring(0, parameterClasses.length() - 1);
		return parameterClasses;
	}

	private String getSystemUnderTestVariableName(String fileName) {
		return pathFindingService.findClassNameWithoutFileExtension(fileName).toLowerCase();
	}

	private void insertImportIfNotThereYet(LinkedList<String> lines, Class<?> classToImport) {
		String importString = "import " + classToImport.getName() + ";";
		for (String line : lines) {
			if (line.contains(importString) || classToImport.isPrimitive()) {
				return;
			}
		}
		lines.add(2, importString);
	}

	private void sortMethods(Method[] methodsOfSystemUnderTest) {
		Arrays.sort(methodsOfSystemUnderTest, new Comparator<Method>() {

			@Override
			public int compare(Method o1, Method o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	private String turnFirstLetterToUpperCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public void writeParamterObjectArrayToXMLFile(String fileName, Object[] objectArray, Method method,
			int parameterNumber) {
		String pathForXML = pathFindingService.findPathForXML(fileName);
		XStream xstream = new XStream(new StaxDriver());
		String xml = xstream.toXML(objectArray);
		File file = new File(pathForXML + method.getName() + parameterNumber + ".xml");
		try {
			FileUtils.writeStringToFile(file, xml, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeResultObjectToXMLFile(String fileName, Object resultObject, Method method, int parameterNumber) {
		String pathForXML = pathFindingService.findPathForXML(fileName);
		XStream xstream = new XStream(new StaxDriver());
		String xml = xstream.toXML(resultObject);
		File file = new File(pathForXML + method.getName() + "Result" + parameterNumber + ".xml");
		try {
			FileUtils.writeStringToFile(file, xml, Charset.defaultCharset());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
