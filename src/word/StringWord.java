package word;

import gui.GamePanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Main;

/**
 * <p><b>Title:</b> StringWord</p>
 * <p><b>Description:</b> class which displays a single word on the game panel.
 * </p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 * @see gui.GamePanel
 */
public class StringWord {
	/** Attributes used to indicate the location of the word.
	 * <code>indice</code> is never used in this class but it is necessary to 
	 * define it because of a polymorphic method. */
	@SuppressWarnings("unused")
	private int x, y, xStart, indiceLettere, indice = 0 ;
	
	/** The word displayed on the game panel. */
	private String parola ;
	
	/** A boolean that indicates if the word is selected (the player started to 
	 * type this word) or not. */
	private boolean isSelected = false ;
	
	/**
	 * Constructor without parameters. It is necessary to define it because of 
	 * the subclass {@link SubStringWord}.
	 */
	public StringWord(){}
	
	/**
	 * Constructor which instantiates a new string word.
	 *
	 * @param WM the word manager reference used for set the word
	 */
	public StringWord(WordManager WM){
		GamePanel.pos = (int)(Math.random()*4) + 1 ;
		parola = WM.extractWord() ; 
		this.selectStartPosition();
	}
	
	/**
	 * Method that draws word on the panel.
	 *
	 * @param g the {@link java.awt.Graphics} from the {@link 
	 * gui.GamePanel#paint} method
	 * @param parola the word to be drawn
	 * @see gui.GamePanel
	 */
	public void drawWord(Graphics g, String parola){	
		char lettere [] = null ;
		int width [] = null ;
		this.indiceLettere = this.x ;
		        
		for( int i = 0; i < parola.length(); i++ ) { 
			lettere = new char[parola.length()] ;
			lettere[i] = parola.charAt(i);
			width = new int[parola.length()] ;
					
			if(i == 0)
				width[i] = 0 ;
			else 
				width[i] = g.getFontMetrics().charWidth(parola.charAt(i-1));
			
		    g.setColor(Color.black);  
		    g.setFont(new Font("Seravek ExtraLight", Font.BOLD, 35)) ;
					     
		    this.indiceLettere += width[i]; 
		    
		    g.drawString(""+lettere[i], this.indiceLettere , this.y ); 
		}
	}

	/**
	 * Method which increase the y-axis of the word.
	 */
	public void increase(){
		this.y += 20 ;
	}
	
	/**
	 * Indicates that the word drawn is selected or not.
	 *
	 * @return true if the word is selected, false otherwise.
	 */
	public boolean getSelected(){
		return isSelected ;
	}
	
	/**
	 * Selects the word.
	 *
	 * @param isSelected true for selecting the word
	 */
	public void setSelected(boolean isSelected){
		this.isSelected = isSelected ;
	}
	
	/**
	 * Sets the <code>indice</code> in the subclass.
	 * 
	 * @see SubStringWord
	 * @param indice the number of letters typed by the player
	 */
	public void setIndice(int indice){
		this.indice = indice ;
	}
	
	/**
	 * Gets the word.
	 *
	 * @return the word
	 */
	public String getWord(){
		return this.parola ;
	}
	
	/**
	 * Sets the word.
	 *
	 * @param parola the new word
	 */
	public void setWord(String parola){
		this.parola = parola;
	}
	
	/**
	 * Gets the x-axis of the word drawn.
	 *
	 * @return the x-axis of the word
	 */
	public int getX(){
		return this.x;
	}
	
	/**
	 * Gets the y-axis of the word drawn.
	 *
	 * @return the y-axis of the word
	 */
	public int getY(){
		return this.y ;
	}
	
	/**
	 * Method which selects a random start position for the word.
	 */
	public void selectStartPosition() {
		int [] dim = Main.dimensioni() ;
		this.xStart = (dim[0]/4) ;
		
		switch(GamePanel.pos){
			case 1: this.x = xStart - 200 ; break ;
			case 2: this.x = xStart + 50 ; break ;
			case 3: this.x = xStart * 2 + 50 ; break ;
			case 4: this.x = xStart * 3 + 50 ; break ;
		}
		
		this.indiceLettere = this.x;
		this.y = 50;
	}
}