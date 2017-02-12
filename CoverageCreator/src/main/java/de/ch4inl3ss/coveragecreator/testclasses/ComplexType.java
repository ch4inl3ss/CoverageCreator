package de.ch4inl3ss.coveragecreator.testclasses;

import java.util.List;

public class ComplexType {

	private String string;

	private int number;

	private List<String> strings;

	private AnotherComplexType anotherComplexType;

	private List<AnotherComplexType> listOfAnotherComplexType;

	public AnotherComplexType getAnotherComplexType() {
		return anotherComplexType;
	}

	public List<AnotherComplexType> getListOfAnotherComplexType() {
		return listOfAnotherComplexType;
	}

	public int getNumber() {
		return number;
	}

	public String getString() {
		return string;
	}

	public List<String> getStrings() {
		return strings;
	}

	public void setAnotherComplexType(AnotherComplexType anotherComplexType) {
		this.anotherComplexType = anotherComplexType;
	}

	public void setListOfAnotherComplexType(List<AnotherComplexType> listOfAnotherComplexType) {
		this.listOfAnotherComplexType = listOfAnotherComplexType;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setString(String string) {
		this.string = string;
	}

	public void setStrings(List<String> strings) {
		this.strings = strings;
	}

}
