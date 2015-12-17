package com.java.myrotiuk.text;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.myrotiuk.elementsFactoryCache.ElementsFactory;

/**
 * Class<code> Sentence</code> for creating an instance of sentence from parsed
 * text
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class Sentence extends ElementOfText{

    /**
     * Field sentence for storing concrete sentence
     */
	private String sentence;
    /**
     * Field sentenceForWord for storing concrete sentence for parsing it to
     * elements of sentence
     */
	private String sentenceForWord;
	
    /**
     * Field for storing end of sentence
     */
	private SentenceEndWith endWith;
	/**
	 * Field uniqueReferencesOnElements for storing an instance of class ElementsFactory
	 * shared between all sentences where is checked if there was already created such element 
	 * if so return reference if no create and return reference 
	 */
	private static ElementsFactory uniqueReferencesOnElements = new ElementsFactory();
	/**
	 * Field uniqueWordsForAllText for storing unique words that do not match with other words 
	 * that is stored in previous sentences
	 */
	private List<ElementOfSentence> uniqueWordsForAllText = new LinkedList<>(); 
	
	/**
	 * Field countWords for storing number of words in the sentence
	 */
	private int countWords = 0;
	
    /**
     * Field elementsOfSentence for storing list of elements of sentence that we have after
     * parsing sentence
     */
	private List<ElementOfSentence> elementsOfSentence;
	
	/**
     * Parameterized constructor that has parameter
     *
     * @param sentence sentence that will be created as an object
     * @param nameOfSentence for sentence as sentence
     * @param sentenceEndWith constant with which sentence ends of SentenceEndWith type
     */
	public Sentence(String nameOfSentence, String sentence, SentenceEndWith sentenceEndWith){ 
		super(nameOfSentence);
		this.sentence = sentence;
		this.sentenceForWord = sentence;
		this.endWith = sentenceEndWith;
	}
	
	/**
     * Class<code> SentenceEndWith</code> for storing enumerated constant with
     * which sentence ends.
     */
    public enum SentenceEndWith {

        QUESTION_MARK, POINT, THREE_POINT, EXCLAMATION_MARK
    }
    
    /**
     * Method for getting sign of the end of sentence
     *
     * @return sign of the end of sentence
     */
    public SentenceEndWith getEndOfSentence() {
    	return this.endWith;
    }
	
    /**
     * Method for making element of sentence and choosing unique words for current sentence
     * that do not happen in previous sentences
     */
    public void makeElementsOfSentence(){
    	elementsOfSentence = new LinkedList<>();
    	
    	Pattern addSpaceForBetterDelim = Pattern.compile("([\\wА-Яа-я])([,.-\\\\\"?!\\[\\]\\(\\)(\\.\\.\\.)])");//-[^\\wА-Яа-я]
    	Matcher sentenceToMatch = addSpaceForBetterDelim.matcher(sentenceForWord); 
    	sentenceForWord = sentenceToMatch.replaceAll("$1 $2");
    	
    	String delims = " ";
    	StringTokenizer elementToken = new StringTokenizer(sentenceForWord, delims);
    	while(elementToken.hasMoreTokens()){
    		String element = elementToken.nextToken();
    		ElementOfSentence elemOfSentence = null;
    		
    		if (element.matches("[А-Яа-я\\w]+")){
    			
    			elemOfSentence = uniqueReferencesOnElements.getElement("word", element);
    			boolean createdNewWordElementOfSentence = uniqueReferencesOnElements.isCreatedNewWord();
    			if (createdNewWordElementOfSentence) {
    				uniqueWordsForAllText.add(elemOfSentence);//for the last sentence
				}
    			if(elemOfSentence.getElement().matches("[А-Яа-я\\w]{2,}")){
    				countWords++;
    			}
    		}else{
    			
    			elemOfSentence = uniqueReferencesOnElements.getElement("sign", element); 
    		}
//    		if(".".equals(element) || "?".equals(element) || "!".equals(element) || element.equals(",") 
//    				|| element.equals("-") || element.equals("(") || element.equals(")") || element.equals("[") || element.equals("]")){//...
//    		} 
    		elementsOfSentence.add(elemOfSentence);
    	}
    }
    
    /**
     * Method for getting unique words that do not match with other words 
	 * that is stored in previous sentences
     * @return unique words from current sentence that do not happen in previous sentences
     */
    public List<ElementOfSentence> uniqueWordsInCurrentSentenceForAllText(){
    	return this.uniqueWordsForAllText;
    }
    
    /**
     * Method that allow to choose unique elements from a list of words that
     * have concrete size
     *
     * @param sizeOfWords concrete size of word that has to be chosen
     * @return list of unique words with special size
     */
    public List<Word> chooseUniqueWords(int sizeOfWord){
    	List<Word> listOfWords = new LinkedList<>();
    	for(ElementOfSentence element: elementsOfSentence){
    		if((element instanceof Word) && element.getElement().length() == sizeOfWord
    				&& !listOfWords.contains(element)){
    			listOfWords.add((Word)element);
    		}
    	}
    	return listOfWords;
    }
    
    /**
     *  Method for getting a number of words in the sentence
     * @return a number of words
     */
    public int getNumberOfWordInTheSEntence(){
    	return this.countWords;
    }
    
    /**
     * Method for getting a value of a sentence
     *
     * @return a value of a sentence
     */
	public String getElement(){
		return this.toString();
	}
	
	/**
     * Method for getting all elements of sentence
     *
     * @return all elements of sentence
     */
	public List<ElementOfSentence> getElementsOfSentence(){
		if(elementsOfSentence == null){
			makeElementsOfSentence();
		}
		return this.elementsOfSentence;
	}
	
	public String toString() {
		return this.sentence;
	}
	
}
