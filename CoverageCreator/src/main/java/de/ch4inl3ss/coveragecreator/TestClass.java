package de.ch4inl3ss.coveragecreator;

public class TestClass {

	public long fib(int n) {
		if (n <= 1) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}
}
