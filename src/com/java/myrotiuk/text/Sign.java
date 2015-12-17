package com.java.myrotiuk.text;

/**
 * Class<code> Sign</code> for creating an instance of Sign from parsed sentence
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class Sign extends ElementOfSentence {

    /**
     * Field for storing a value of an instance
     */
	private String sign;
    /**
     * Parameterized constructor that has parameter
     *
     * @param nameOfSign for sign as sign
     * @param sign sign that will be created as an object
     */
	public Sign(String nameOfSign, String sign){
		super(nameOfSign);
		this.sign = sign;
	}
	
    /**
     * Class<code> TypeOfSign</code> for storing enumerated constant of sign
     */
	public enum TypeOfSign{
		POINT, QUESTION_MARK, EXCLAMATION_MARK, COMMA, SEMICOLON, COLON;
	}
	
    /**
     * Method for getting a value of an instance of Sign
     *
     * @return value of an instance
     */
	public String getElement(){
		return this.toString();
	}

	public String toString() {
		return this.sign;
	}
	
}
