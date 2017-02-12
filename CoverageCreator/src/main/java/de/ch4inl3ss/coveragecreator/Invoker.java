package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import de.ch4inl3ss.coveragecreator.constants.Parameters;

public class Invoker {

	public List<Object[]> findParamterListForMethod(Method method, String fileName) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		List<Object[]> parameters = new ArrayList<>();
		for (Class<?> clazz : parameterTypes) {
			Object[] parametersArray = Parameters.getParameters(clazz);
			if (parametersArray == null) {
				if (clazz.equals(String.class)) {
					parametersArray = findStringsInClass(fileName);
				} else {
					// clazz is a complex type!
				}
			}
			parameters.add(parametersArray);
		}

		return parameters;
	}

	public Object[] findStringsInClass(String fileName) {
		List<Object> stringsInClass = new ArrayList<>();
		File file = new File(fileName);
		try {
			List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());
			for (String line : lines) {
				String[] strings = line.split("\"");
				for (int index = 1; index < strings.length; index++) {
					if (index % 2 != 0) {
						stringsInClass.add(strings[index]);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stringsInClass.toArray(new Object[stringsInClass.size()]);
	}

	public Object invokeMethod(Class<?> clazz, Method method, Object... parameters) {
		Object instance;
		try {
			instance = clazz.newInstance();
			Object result = method.invoke(instance, parameters);
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			// No instantiation possible
			e.printStackTrace();
			return null;
		} catch (IllegalArgumentException | InvocationTargetException e) {
			return e.getCause();
		} catch (Exception | Error e) {
			// TODO this seems evil. Is this ok to do?
			return e;
		}
	}

}
