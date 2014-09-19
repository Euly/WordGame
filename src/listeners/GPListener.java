package listeners;

import gui.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <p><b>Title:</b> GPListener</p>
 * <p><b>Description:</b> The listener interface for receiving GamePanel events.
 * The class that is interested in processing a GamePanel event implements this
 * interface, and the object created with that class is registered with a
 * component using the component's <code>addGPListener</code> method. When the
 * GamePanel event occurs, that object's appropriate method is invoked.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 * @see gui.GamePanel#GamePanel()
 */
public class GPListener implements KeyListener {
	/** The <code>GamePanel</code> reference. */
	private GamePanel GP = null ;
	
	/** The number of words already created and showed on the game panel.*/
	private int index = 0 ;
	
	/** 
	 * A boolean that indicates if the word is selected (the player started to 
	 * type this word) or not.
	 * 
	 * @see word.StringWord#getSelected()
	 **/
	private boolean sel = false ;
	
	/**
	 * <p>Attributes that are used like index for accessing the array of 
	 * StringWords in {@link gui.GamePanel#paint(java.awt.Graphics)}.</p>
	 * <code>s</code> is the index of the StringWord after this one has been
	 * selected; <code>j</code> is the number of letters already typed minus 1.
	 * 
	 * @see word.SubStringWord#setIndice(int)
	 **/
	private int s = 100, j = 1 ;
	
	/** 
	 * The score reached by the player. It would be displayed in the label
	 * <code>punteggio</code>.
	 * 
	 * @see gui.GameFrame#getLabelSelected(int) 
	 **/
	private int punti = 0 ;
	
	/** A simple counter that indicates the number of words already killed by
	 * the player. It is initialized at the beginning of a new level. */
	private int eliminate = 0 ;
	
	/**
	 * Instantiates a new GP listener.
	 *
	 * @param GP the game panel reference
	 */
	public GPListener(GamePanel GP){
		this.GP = GP ;
		this.index = GP.getIndex() ;
	}

	/**
	 * {@inheritDoc}
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * Method which checks if the letter pressed on the keyboard has a match 
	 * with the words on the panel.
	 * 
	 * {@inheritDoc}
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int i ;
		this.index = GP.getIndex() ;
		
		for(i = 0 ; i < index && i < 24 ; i++) {
			if(GP.getParole(i) == null)
				continue ;
				
			if (e.getKeyChar() == GP.getParole(i).getWord().charAt(0) && !sel){
				GP.getParole(i).setSelected(true) ;
				s = i ;
				sel = true ;
			}
		}
		
		if(s != 100){ 
			if(j < GP.getParole(s).getWord().length() &&  e.getKeyChar() == GP.getParole(s).getWord().charAt(j)){
				j++ ;
				GP.getParole(s).setIndice(j);
			}
			if(j == GP.getParole(s).getWord().length()){
				punti += GP.getParole(s).getWord().length() ;
				GP.setParole(s, null);
				eliminate ++ ;
				sel = false ;
				s = 100 ;
				j = 1 ;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * Gets the score for displaying it in the right label.
	 *
	 * @return the score of the player
	 */
	public int getPunti(){
		return punti ;
	}
	
	/**
	 * Gets the eliminate words.
	 *
	 * @return the number of words already eliminated
	 */
	public int getEliminate(){
		return eliminate ;
	}
	
	/**
	 * Method which initialize the <code>eliminate</code> attribute, the number
	 * of words eliminated by the player, before a new level of game starts.
	 */
	public void initializeEliminate(){
		this.eliminate = 0 ;
	}
}
