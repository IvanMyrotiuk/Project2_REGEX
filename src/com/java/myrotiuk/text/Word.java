package com.java.myrotiuk.text;

/**
 * Class<code> Word</code> for creating an instance of word from parsed sentence
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class Word extends ElementOfSentence{

    /**
     * Field for storing a value of an instance
     */
	private String word;
	

    /**
     * Parameterized constructor that has parameter
     * @param nameOfWord for word as word
     * @param word word that will be created as an object
     */
	public Word(String nameOfWord, String word){
		super(nameOfWord);
		this.word = word;
	}
	
    /**
     * Method for getting a value of an instance of Word
     *
     * @return value of an instance
     */
	public String getElement(){
		return this.toString();
	}
	
	public String toString() {
		return this.word;
	}
}
