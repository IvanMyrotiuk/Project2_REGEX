package com.java.myrotiuk.elementsFactoryCache;

import java.util.HashMap;
import java.util.Map;

import com.java.myrotiuk.text.ElementOfSentence;
import com.java.myrotiuk.text.Sign;
import com.java.myrotiuk.text.Word;

/**
 * Class<code> ElementsFactory</code> for making unique references into elements of sentence
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class ElementsFactory {

	private Map<String, ElementOfSentence> elementsOfSentences;

	private boolean createdNewWord ;
	
	public ElementsFactory() {
		elementsOfSentences = new HashMap<>();
	}

    /**
     * Method for checking if there is already exist an element if no create new and add to the collection
     * if exist such an element return reference to that element
     * @param nameElement name of element mast to be checked
     * @param element element of sentence that mast to be created
     * @return references onto unique elements
     */
	public ElementOfSentence getElement(String nameElement, String element) {

		createdNewWord = false;
		
		if (!elementsOfSentences.containsKey(element)) {
			
			if (nameElement.equals("sign")) {
				//System.out.println("----sign not contained "+element);
				ElementOfSentence newElemOfSentence = new Sign(nameElement, element);
				elementsOfSentences.put(element, newElemOfSentence);
			} else if (nameElement.equals("word")) {
				
				ElementOfSentence newElemOfSentence = new Word(nameElement, element);
				elementsOfSentences.put(element, newElemOfSentence);
				createdNewWord = true;
			}
		}
		return elementsOfSentences.get(element);
	}
	/**
	 * Method for getting prove if the element that has been created is a new element in collection 
	 * @return true if new element was created otherwise false if no 
	 */
	public boolean isCreatedNewWord(){
		return this.createdNewWord;
	}
}
