package com.java.myrotiuk.text;

/**
 * Class<code> ElementOfSentence</code> is an abstract class that is super class
 * for element such as Word and Sign
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public abstract class ElementOfSentence {
	
    /**
     * Field for storing name of sentence element
     */
	private String nameOfElement;
	 
    /**
     * Parameterized constructor that has parameter
     * @param nameOfSentenceElement name of sentence element
     */
	public ElementOfSentence(String nameOfSentenceElement){
		this.nameOfElement = nameOfSentenceElement;
	}
	
	/**
	 * Method that have to be overridden by element such as Word and Sign 
	 * @return current element
	 */
	public abstract String getElement();
	
    /**
     * Method for getting name of sentence element
     * @return name of sentence element
     */
	public String getNameOfElement(){
		return this.nameOfElement;
	}

}
