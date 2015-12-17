package com.java.myrotiuk.text;

/**
 * Class<code> ElementOfText</code> is an abstract class that is super class for
 * Sentence and Listing
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public abstract class ElementOfText {

    /**
     * Field for storing name of text element
     */
	private String nameOfElement;
	
	/**
     * Parameterized constructor that has parameter
     *
     * @param nameOfElement name of text element
     */
	public ElementOfText(String nameOfElement){
		this.nameOfElement = nameOfElement;
	}
	
	/**
     * Abstract method for getting through supper class an element of concrete element of text
     * @return concrete element of text
     */
	public abstract String getElement();
	

    /**
     * Method for getting name of text element
     *
     * @return name of text element
     */
	public String getNameOfElement(){
		return nameOfElement;
	}
}
