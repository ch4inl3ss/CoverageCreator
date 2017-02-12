package de.ch4inl3ss.coveragecreator;

public class ActualTestClass implements Runnable {

	public void run() {
		new TestClass().fib(20);
	}
}
