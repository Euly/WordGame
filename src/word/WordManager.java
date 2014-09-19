package word;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * <p><b>Title:</b> WordManager</p>
 * <p><b>Description:</b> class which reads the words from a text file and saves 
 * them in a linked list in order to extract them at the instantiation of a new
 * {@link StringWord}.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
public class WordManager {
	/** The linked list which contains the words. */
	private LinkedList<String> WordList ;
	
	/** An array of strings which contains the words before they're add to the
	 * linked list. */
	private String [] Words ;
	
	/** An array which contains the names of the files to be read for each level
	 * of game. */
	private String [] Levels = {"FirstLevel.txt", "SecondLevel.txt", "ThirdLevel.txt"} ;
	
	/** Index of array Levels which referred to different text files. */
	private int indexLevel = 0 ;
	
	/** Index used in file texts referred to words already read.*/
	private int indexWords = 0 ;
 
	/** The FileReader used for accessing the text file. */
	private FileReader freader = null ;
	
	/**
	 * Constructor without parameters which instantiates a new word manager.
	 */
	public WordManager(){
		WordList = new LinkedList<String>() ;
		Words = new String[24] ; 
		readFile(freader) ;
		LoadWords() ;
	}
	
	/**
	 * Method which reads the file and append each character in a string of the
	 * array <code>Words</code>.
	 *
	 * @param freader the file reader
	 * @exception FileNotFoundException if the txt file is't in the same 
	 * directory of this class
	 * @exception IOException if an error in reading the file occurred 
	 */
	public void readFile(FileReader freader){
		StringBuffer sbuffer = new StringBuffer() ;
		boolean space = false ; 
		
		try{
			freader = new FileReader(Levels[indexLevel]);
		}catch(FileNotFoundException e){
			System.out.println("This file doesn't exist. Try with another file.");
			System.exit(1); /* Returns 1 in case of failure. */
		}
	
		try{
			while(freader.ready()){
				char c = (char)freader.read();
				space = (c == ' ' || c == '\n' || c == '\t');
				
				if(!space) 
					sbuffer.append(c) ;
				else{
					Words[indexWords++] = new String(sbuffer.toString()) ;
					sbuffer.delete(0, sbuffer.length()) ;
				}
			}
		}
		catch(IOException e) {
			System.out.println("An error in reading the text file occurred. "+e) ;
			System.exit(2); /* Returns 2 in case of failure. */
		}
	}
	
	/** Method which adds the word from the file who refers to the current level
	 * to the WordList in order to be used in StringWord class. */
	public void LoadWords(){
		for(int i = 0 ; i < Words.length ; i++){
			WordList.add(i, Words[i]) ;
		}
	}
	
	
	/** Method which increase the current level of game. If the player finishes
	 * the third level, the game starts again from the first one. 
	 * */
	public void increaseLevel(){
		indexWords = 0 ;
		if(indexLevel < 2)
			indexLevel ++ ;
		else 
			indexLevel = 0 ;
	}
	
	/**
	 * Gets the current level of game.
	 *
	 * @return the current level
	 */
	public int getLevel(){
		return indexLevel ;
	}
	
	/**
	 * Method which extracts a word from the linked list <code>WordList</code>.
	 *
	 * @return the word extracted
	 */
	public String extractWord(){
		String Word = "" ;
		for(int i = 0 ; i < Words.length ; i++ ){
			boolean ok = WordList.contains(Words[i]) ;
			if(ok){
				Word = Words[i] ;
				WordList.remove(Words[i]) ;
				return Word ;
			} 
		}
		if(WordList.isEmpty())
			return "EmptyList" ;
		
		return "Error" ;
	}
}
