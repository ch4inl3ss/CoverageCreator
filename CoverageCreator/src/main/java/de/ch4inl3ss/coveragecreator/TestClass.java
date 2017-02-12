package de.ch4inl3ss.coveragecreator;

import de.ch4inl3ss.coveragecreator.testclasses.AnotherComplexType;
import de.ch4inl3ss.coveragecreator.testclasses.ComplexType;

public class TestClass {

	public void changeComplexType(ComplexType complexType) {
		if (complexType.getString().equals("test")) {
			complexType.setAnotherComplexType(new AnotherComplexType());
			complexType.getAnotherComplexType().setString("asdf");
		}
		complexType.setNumber(2);
	}

	public long fib(int n) {
		if (n <= 1) {
			return 1;
		}
		return fib(n - 1) + fib(n - 2);
	}

	public String hello(String s) {
		if (s.equals("hello")) {
			return "bye";
		}
		return s;
	}

}
