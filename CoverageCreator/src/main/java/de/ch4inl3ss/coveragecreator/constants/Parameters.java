package de.ch4inl3ss.coveragecreator.constants;

public class Parameters {
	public static final Object[] intValues = { Integer.MIN_VALUE, -1000, -100, -10, -1, 0, 1, 10, 100, 1000,
			Integer.MAX_VALUE };

	public static final Object[] stringValues = { null, "", "-10", "-1", "0", "1", "10", "asdf" };

	public static final Object[] longValues = { Long.MIN_VALUE, -1000l, -100l, -10l, -1l, 0l, 1l, 10l, 100l, 1000l,
			Long.MAX_VALUE };

	// TODO all primitives

	public static Object[] getParameters(Class<?> clazz) {
		if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
			return intValues;
		} else if (clazz.equals(Long.class) || clazz.equals(long.class)) {
			return longValues;
		}

		return null;
	}
}
