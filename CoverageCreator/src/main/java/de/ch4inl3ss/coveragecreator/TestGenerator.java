package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
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

	private void generateImports(List<String> lines) {
		lines.add("import org.junit.Before;");
		lines.add("import org.junit.Test;");
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

		List<String> lines = new ArrayList<>();
		generatePackageDeclaration(fileName, lines);
		generateImports(lines);
		generateClassStart(fileName, lines);
		generateSystemUnderTest(fileName, lines);
		generateSetup(fileName, lines);

		for (Method method : findMethodsOfSystemUnderTest(fileName)) {
			generateTestMethod(lines, method, fileName);
		}

		generateClassEnd(lines);

		FileUtils.writeLines(outputFile, lines);

	}

	private void generateTestMethod(List<String> lines, Method method, String fileName) {
		lines.add("@Test");
		lines.add("public void test" + turnFirstLetterToUpperCase(method.getName()) + "() {");

		// generate method-call
		// lines.add(getSystemUnderTestVariableName(fileName) + "." +
		// method.getName() + "();");

		// Map contains the
		// invoker.invokeMethod(classS, method, parameters)

		// each array in this list is a separate testcase with an assert
		List<Object[]> paramterListForMethod = invoker.findParamterListForMethod(method, fileName);
		writeObjectArrayToXMLFile(fileName, paramterListForMethod.get(0), method, 0);

		lines.add("");
		lines.add("}");
	}

	private String getSystemUnderTestVariableName(String fileName) {
		return pathFindingService.findClassNameWithoutFileExtension(fileName).toLowerCase();
	}

	private String turnFirstLetterToUpperCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	public void writeObjectArrayToXMLFile(String fileName, Object[] objectArray, Method method, int parameterNumber) {
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

}
