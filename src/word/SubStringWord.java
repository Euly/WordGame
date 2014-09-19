package word;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import main.Main;

/**
 * <p><b>Title:</b> SubStringWord<p>
 * <p><b>Description:</b> class which displays only selected words and it's used
 * for implementing the polymorphism in java.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
public class SubStringWord extends StringWord {
	/** The word displayed on the game panel. */
	@SuppressWarnings("unused")
	private String parola ;
	
	/** Attributes used to indicate the location of the word. */
	private int indiceLettere, y ;
	
	/** The number of letters type. */
	private int indice;
	
	/** The background color. */
	private Color Sfondo = new Color(153, 204, 255) ;
	
	/**
	 * Constructor which instantiates a new sub string word.
	 *
	 * @param parola the word to be drawn
	 */
	public SubStringWord(String parola){
		this.setWord(parola);
		this.y = Main.getFrame().getGP().getOrdinata() ;
	}
	
	/**
	 * Polymorphic method.
	 * {@inheritDoc} 
	 * 
	 * @see word.StringWord#drawWord(java.awt.Graphics, java.lang.String)
	 */
	@Override
	public void drawWord(Graphics g, String parola){
		char lettere [] = null ;
		int width [] = null ;
		this.indiceLettere = Main.getFrame().getGP().getAscissa() ; 
		        
		for( int i = 0, j = 0; i < parola.length(); i++, j++ ) { 
			lettere = new char[parola.length()] ;
			lettere[i] = parola.charAt(i);
					
			width = new int[parola.length()] ;
					
			if(i == 0)
				width[i] = 0 ;
			else 
				width[i] = g.getFontMetrics().charWidth(parola.charAt(i-1));
			
			/* Polymorphism */
			if (j < indice){
				g.setColor(Sfondo); 
			} 
			else 
				g.setColor(Color.black); 
			
		    g.setFont(new Font("Seravek ExtraLight", Font.BOLD, 35)) ;
					     
		    this.indiceLettere += width[i]; 
		    
		    g.drawString(""+lettere[i], this.indiceLettere , y ); 
		}	
	}
	
	/**
	 * Method which sets the index of letters typed.
	 * 
	 * @see word.StringWord#setIndice(int)
	 */
	@Override
	public void setIndice(int indice){
		this.indice = indice ;
	}
	
	/**
	 * Method which increase the y-axis of the word.
	 * 
	 * @see word.StringWord#increase()
	 */
	@Override
	public void increase(){
		this.y += 20 ;
	}
}