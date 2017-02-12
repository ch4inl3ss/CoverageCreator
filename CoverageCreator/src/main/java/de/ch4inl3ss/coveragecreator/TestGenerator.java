package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class TestGenerator {

	private PathFindingService pathFindingService = new PathFindingService();

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
		lines.add(pathFindingService.findClassNameWithoutFileExtension(fileName).toLowerCase() + " = new "
				+ pathFindingService.findClassNameWithoutFileExtension(fileName) + "();");
		lines.add("}");
		lines.add("");
	}

	private void generateSystemUnderTest(String fileName, List<String> lines) {
		lines.add("private " + pathFindingService.findClassNameWithoutFileExtension(fileName) + " "
				+ pathFindingService.findClassNameWithoutFileExtension(fileName).toLowerCase() + ";");
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
			lines.add("@Test");
			lines.add("public void test" + turnFirstLetterToUpperCase(method.getName()) + "() {");
			lines.add("");
			lines.add("}");
		}

		generateClassEnd(lines);

		FileUtils.writeLines(outputFile, lines);

	}

	private String turnFirstLetterToUpperCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}
}
