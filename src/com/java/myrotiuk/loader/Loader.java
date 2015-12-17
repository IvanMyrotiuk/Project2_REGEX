package com.java.myrotiuk.loader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import com.java.myrotiuk.text.ElementOfSentence;
import com.java.myrotiuk.text.ElementOfText;
import com.java.myrotiuk.text.Sentence;
import com.java.myrotiuk.text.Text;
import com.java.myrotiuk.text.Word;

/**
 * Class<code> Loader</code> for loading our program of making Salads
 *
 * @version 1.0
 * @author Ivan Myrotiuk
 * @since 3-12-2015
 */
public final class Loader {

    /**
     * Method that load our logic of making Salad
     */
	public static void load(){
		//--->reading text from the file
		File file = new File("./src/Text.txt");
		
		StringBuilder text = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			
			String strLine = null;
			while((strLine = br.readLine()) != null){
				text.append(strLine);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String finalText = text.toString();
		System.out.println("<-------Text as it is:------->\n"+finalText);
		
		Text textToWorkWith = new Text(finalText);
		System.out.println("\n<-------Text without tabulation and with correct end of sentence:------->\n"+textToWorkWith);
		
		System.out.println("\n<-------Sentences and Listings:------->");
		for(ElementOfText elementOfText: textToWorkWith.getElementsOfText()){
			System.out.println(elementOfText.getElement());
		}
		
		System.out.println("\n<-------Unique Words in the first sentence for all text:------->");
		int k = 0;
		for(ElementOfSentence elementOfSentence: textToWorkWith.getUniqueWordsFromAllTextInFirstSentence()){
			k++;
			System.out.print(k+"."+elementOfSentence+" ");
		}
		
		System.out.println("\n\n<-------Print Sentences in order of growth:------->");
		for(ElementOfText sentence: textToWorkWith.getSentencesInOrderOfGrowth()){
			System.out.println(sentence);
		}
		
		System.out.println("\n\n<-------Print Sentences with question mark:------->");
		for(Sentence sentence: textToWorkWith.getSentencesWithQuestionMark()){
			System.out.println(sentence);
		}
		
		System.out.println("\n<-------Unique words with acquired length from sentences with question mark:------->\n ");
		int l = 0;
		for(Sentence sentence : textToWorkWith.getSentencesWithQuestionMark()){
			l++;
			System.out.print("Sentence #"+l);
			for(Word word : sentence.chooseUniqueWords(5)){
				System.out.print(" "+word);
			}
			System.out.println();
		}
		
		System.out.println("==========================================================");
	    System.out.println("||    All right reserved Myrotiuk Ivan's corporation\u2122    ||");
	    System.out.println("==========================================================");

	}
}
