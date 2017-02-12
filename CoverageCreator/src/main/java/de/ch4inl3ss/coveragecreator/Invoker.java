package de.ch4inl3ss.coveragecreator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;

import de.ch4inl3ss.coveragecreator.constants.Parameters;

public class Invoker {

	private ClassService classService = new ClassService();

	private Object[] fillComplexType(Class<?> clazz, String fileName) {
		List<Object> complexObjects = new ArrayList<>();
		Map<Method, List<Object[]>> parameterLists = new HashMap<>();
		for (Method method : classService.findSetterMethods(clazz)) {
			List<Object[]> paramterListForMethod = findParamterListForMethod(method, fileName);
			parameterLists.put(method, paramterListForMethod);
		}

		// TODO find a better way to create the right number of instances
		for (int i = 0; i < 5; i++) {
			try {
				Object instance = clazz.newInstance();
				for (Method method : parameterLists.keySet()) {
					// System.out.println(method);
					// System.out.println(ReflectionToStringBuilder.toString(parameterLists.get(method).get(i)));
					method.invoke(instance, parameterLists.get(method).get(i));
				}
				complexObjects.add(instance);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {

				e.printStackTrace();
			}
		}

		return complexObjects.toArray(new Object[complexObjects.size()]);
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> findParametersForClass(Method method, String fileName, Class<?> clazz) {
		List<Object[]> parameters = new ArrayList<>();
		Object[] parametersArray = Parameters.getParameters(clazz);
		if (parametersArray == null) {
			if (clazz.equals(String.class)) {
				parametersArray = findStringsInClass(fileName);
				parametersArray = ArrayUtils.addAll(parametersArray, Parameters.stringValues);
			} else if (clazz.equals(List.class)) {
				Class<?> typeOfList = classService.findTypeOfList(method);
				List<Object[]> listValues = findParametersForClass(method, fileName, typeOfList);
				List<List<Object>> lists = new ArrayList<>();
				for (Object[] objects : listValues) {
					for (Object object : objects) {
						@SuppressWarnings("rawtypes")
						List list = new ArrayList<>();
						list.add(object);
						lists.add(list);
					}
				}
				parametersArray = lists.toArray(new Object[lists.size()]);
			} else {
				parametersArray = fillComplexType(clazz, fileName);
			}
		}
		parameters.add(parametersArray);
		return parameters;
	}

	/**
	 * One object-array in the list is one complete parameterset for the method
	 *
	 * @param method
	 * @param fileName
	 * @return
	 */
	public List<Object[]> findParamterListForMethod(Method method, String fileName) {
		Class<?>[] parameterTypes = method.getParameterTypes();
		List<Object[]> parameters = new ArrayList<>();
		for (Class<?> clazz : parameterTypes) {
			parameters.addAll(findParametersForClass(method, fileName, clazz));
		}

		List<Object[]> resultParameterSet = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			List<Object> parameterSet = new ArrayList<>();
			for (Object[] parameterArray : parameters) {
				parameterSet.add(parameterArray[i]);
			}
			resultParameterSet.add(parameterSet.toArray(new Object[parameterSet.size()]));
		}

		return resultParameterSet;
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
