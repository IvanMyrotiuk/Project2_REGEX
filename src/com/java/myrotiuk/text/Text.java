package com.java.myrotiuk.text;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.java.myrotiuk.elementsFactoryCache.ElementsFactory;
import com.java.myrotiuk.text.Sentence.SentenceEndWith;

/**
 * Class<code> Text</code> for creating an instance of text
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public class Text {
	
    /**
     * Field for storing text
     */
	private String text;
    /**
     * Field for storing elements of text
     */
	private List<ElementOfText> elementsOfText = new LinkedList<>();
	
    /**
     * Parameterized constructor that has parameter
     * Also there is calling method for making elements of text
     *
     * @param text text that will be created as an object
     */
	public Text(String text){
		this.text = changeAllWhiteSpacesToOne(text);
		mekeElementsOfText();
	}
	
	/**
     * Method for replacing all sequence of wite spaces and tabulations
     */
	private String changeAllWhiteSpacesToOne(String text){
		
		//---change tabulation and many white spaces to one space
		Pattern patternToChangeSpaces = Pattern.compile("\\s+");
		Matcher matcherText = patternToChangeSpaces.matcher(text);
		String finalText = matcherText.replaceAll(" ");
		
		//---situation when dog .-> make dog.
		patternToChangeSpaces = Pattern.compile("([\\wа-я])([ ])([?.!])");
		matcherText = patternToChangeSpaces.matcher(finalText);
		finalText = matcherText.replaceAll("$1$3");
		
		//---situation when dog.Is make -> dog. Is
		patternToChangeSpaces = Pattern.compile("([?.!])([A-ZА-Я])");
		matcherText = patternToChangeSpaces.matcher(finalText);
		finalText = matcherText.replaceAll("$1 $2");
		
		return finalText;
	}
	/**
	 * Method for making elements of text such as Listings and Sentences
	 */
	private void mekeElementsOfText(){
		
		String SentencesAndListingPattern = "([A-ZА-Я][^?.!]+([.?!]|(\\.\\.\\.))[ ])"//sentence
			 		+ "|((\\w{2,} )+( )?[\\{].*[\\}])"//listing 1 for class
			 		+ "|([a-z]{3,}[A-Za-z[ ]?]+\\([\\w ,\\[\\]]+?\\)( )?\\{ .* \\})";//listing2 for methods
		
		Pattern patternForSeparatingText = Pattern.compile(SentencesAndListingPattern);
		Matcher matcherForListingAndSentence = patternForSeparatingText.matcher(text);
		
		while(matcherForListingAndSentence.find()){
			
			if(matcherForListingAndSentence.group().length() != 0){
				
				String matchedTextElement = matcherForListingAndSentence.group().trim();
				
				//ElementsFactory uniqueReferencesOnElements = new ElementsFactory();
				
				if(matchedTextElement.endsWith("}")){
					elementsOfText.add(new Listing("listing", matchedTextElement));
				}else if(matchedTextElement.endsWith(".")){
					elementsOfText.add(new Sentence("sentence", matchedTextElement, Sentence.SentenceEndWith.POINT));//, uniqueReferencesOnElements));
				}else if(matchedTextElement.endsWith("!")){
					elementsOfText.add(new Sentence("sentence", matchedTextElement, SentenceEndWith.EXCLAMATION_MARK));//, uniqueReferencesOnElements));
				}else if(matchedTextElement.endsWith("?")){
					elementsOfText.add(new Sentence("sentence", matchedTextElement, Sentence.SentenceEndWith.QUESTION_MARK));//, uniqueReferencesOnElements));
				}else if(matchedTextElement.endsWith("...")){
					elementsOfText.add(new Sentence("sentence", matchedTextElement, SentenceEndWith.THREE_POINT));//, uniqueReferencesOnElements));
				}
			}
		}
	}
	
	/**
	 * Method for getting unique elements of first sentences
	 * As first sentences could be after several listings we need figure out which one is the first sentence
	 * therefore we scan all elements of text and find first sentence and remember it 
	 * after that we scan another sentences and call makeElementsOfSentence() method 
	 * that make elements for sentence and choose unique words that do not happen in previous sentences
	 * than the same at the end we do with first remembered sentence and call 
	 * uniqueWordsInCurrentSentenceForAllText() method that return unique words for all text from 
	 * first sentence that do not happen in all text
	 * @return unique words for all text from first sentence that do not happen in all text
	 */
	public List<ElementOfSentence> getUniqueWordsFromAllTextInFirstSentence(){
		int countSentence = 0;
		int numberOfFirstSentence = 0;
		for(int i = 0; i < elementsOfText.size(); i++ ){
			
			ElementOfText textElement = elementsOfText.get(i);
			
			if("sentence".equals(textElement.getNameOfElement()) && countSentence == 0){
				countSentence++;
				numberOfFirstSentence = i;
				continue;
			}
			if( textElement instanceof Sentence && countSentence == 1){//"sentence".equals(nameOfTextElement)
				((Sentence)textElement).makeElementsOfSentence();
			}
		}
		
		ElementOfText firstMySentenceElementOfText = elementsOfText.get(numberOfFirstSentence);
		Sentence firstMySentence = (Sentence)firstMySentenceElementOfText;
		firstMySentence.makeElementsOfSentence();
		
		return firstMySentence.uniqueWordsInCurrentSentenceForAllText();
		
	}
	
	/**
	 * Method for getting sentences in order of growth
	 * @return sentences in order of growth
	 */
	public List<? extends ElementOfText> getSentencesInOrderOfGrowth(){
		
		List<Sentence> listOfSentences = new LinkedList<>();
		
		for(ElementOfText elementOfText: elementsOfText){
			if(elementOfText instanceof Sentence){
				listOfSentences.add((Sentence)elementOfText);
			}
		}
		
		Collections.sort(listOfSentences, new Comparator<Sentence>() {
			
			public int compare(Sentence s1, Sentence s2){
				return s1.getNumberOfWordInTheSEntence() - s2.getNumberOfWordInTheSEntence();
			}
		});
		
		return listOfSentences;
	}
	
	/**
	 * Method for getting sentences with question mark 
	 * @return sentences with question mark
	 */
	public List<Sentence> getSentencesWithQuestionMark(){
		List<Sentence> listOfSentencesWithQuestionMark = new LinkedList<>();
		
		for(ElementOfText elementOfText: elementsOfText){
			if(elementOfText instanceof Sentence){
				Sentence sentence = (Sentence)elementOfText;
				if(sentence.getEndOfSentence().equals(Sentence.SentenceEndWith.QUESTION_MARK)){
					listOfSentencesWithQuestionMark.add(sentence);
				}
			}
		}
		return listOfSentencesWithQuestionMark;
	}
	
	/**
     * Method for getting all elements of a text
     * @return all elements of a text
     */
	public List<ElementOfText> getElementsOfText(){
		return this.elementsOfText;
	}
	
    /**
     * Method for getting a value of an instance of Text
     *
     * @return a value of an instance of Text
     */
	public String toString() {
		return this.text;
	}
}
