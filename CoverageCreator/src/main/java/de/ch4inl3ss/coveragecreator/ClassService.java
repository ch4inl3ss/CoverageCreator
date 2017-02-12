package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;

public class ClassService {

	private PathFindingService pathFindingService = new PathFindingService();

	public String findClassFileDirectorToFile(String fileName) {
		String withTargetDirectory = fileName.replace("src/main/java", "target/classes/");
		String withoutClassName = withTargetDirectory.replace(pathFindingService.findClassName(fileName), "");
		String withoutPackage = withoutClassName.split("target/classes/")[0];
		return withoutPackage + "target/classes/";
	}

	public String findClassFileToFile(String fileName) {
		String withTargetDirectory = fileName.replace("src/main/java", "target/classes");
		String withClassFile = withTargetDirectory.replace(".java", ".class");

		return withClassFile;
	}

	public Method[] findMethods(String fileName, String className) {
		try {
			return returnMethodsOfClassInClassPath(className);
		} catch (ClassNotFoundException e) {
			// in this case, the Class is not on the Classpath

			// DiagnosticCollector<JavaFileObject> diagnostics = new
			// DiagnosticCollector<>();
			// JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			// StandardJavaFileManager fileManager =
			// compiler.getStandardFileManager(diagnostics, null, null);

			Method[] methods = new Method[0];
			methods = findMethodsOfClassNotInClassPath(className, fileName);

			return methods;
		}
	}

	private Method[] findMethodsOfClassNotInClassPath(String className, String fileName) {
		File classFile = new File(findClassFileDirectorToFile(fileName));

		try {
			URL url = classFile.toURI().toURL();
			URL[] urls = new URL[] { url };
			ClassLoader classLoader = URLClassLoader.newInstance(urls);
			Class<?> clazz = classLoader.loadClass(className);
			return findRealMethodsInClass(clazz);

		} catch (MalformedURLException | ClassNotFoundException e) {
			e.printStackTrace();
			return new Method[0];
		}

	}

	private Method[] findRealMethodsInClass(@SuppressWarnings("rawtypes") Class clazz) {
		Method[] methods = clazz.getMethods();
		List<Method> realMethods = new ArrayList<>();
		for (Method method : methods) {
			if (!ArrayUtils.contains(Object.class.getMethods(), method)) {
				realMethods.add(method);
			}
		}
		return realMethods.toArray(new Method[realMethods.size()]);
	}

	private Method[] returnMethodsOfClassInClassPath(String className) throws ClassNotFoundException {
		@SuppressWarnings("rawtypes")
		Class clazz = Class.forName(className);
		return findRealMethodsInClass(clazz);
	}

}
