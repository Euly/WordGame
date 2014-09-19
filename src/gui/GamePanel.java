package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.GPListener;
import main.Main;
import word.StringWord;
import word.SubStringWord;
import word.WordManager;

/**
 * <p><b>Title:</b> GamePanel</p>
 * <p><b>Description:</b> class which displays the words in order to play.
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	/** The number of words already created and showed minus 1 (index of an 
	 * array). */
	private int index = 0 ;
	
	/** Attribute which lets create a new word only after 3 times. */
	private int lev1 = 0 ;
	
	/** Attributes used for returning the x-axis and the y-axis of a word. */
	private int ascissa, ordinata ;
	
	/** Attribute that indicates one of the four positions in which the new 
	 * word will be displayed. */
	public static int pos ;
	
	/** Array which contains the application frame dimension. */
	private static int dim [] = Main.dimensioni() ;
	
	/** A boolean which can stop the game if it's set to false or run it if 
	 * it's set to true. */
	private boolean go = true ;
	
	/** The speed of the words. */
	private int speed = 1000 ;
	
	/** Array which contains the words. */
	private StringWord [] parole = new StringWord[24] ;
	
	/** The WordManager reference in order to obtain the words. */
	private WordManager WM ; 
	
	/** The background color of the panel. */
	private Color Sfondo = new Color(153, 204, 255) ;
	
	/** Attributes used to get the labels <code>typed</code> and <code>
	 * punteggio</code>. */
	private JLabel sel, pt ;
	
	/** The GamePanel events listener. */
	private GPListener GPL = null ;
	
	/** JDialog which implements either the <code>GameOver</code> or the <code>
	 * Winner</code> dialog using the java generics.
	 * 
	 * @see gui.GameDialog
	 * @see gui.GameOver
	 * @see gui.Winner 
	 * */
	private GameDialog <JDialog> dialog = null ;

	
	/**
	 * Constructor without parameters which instantiates a new game panel.
	 * It calls the <code>super.constructor</code> and add a new KeyListener.
	 * 
	 * @see listeners.GPListener
	 */
	public GamePanel() {
		super();
		WM = new WordManager() ;
		this.addKeyListener(GPL = new GPListener(this));	
	}
	
	
	/**
	 * Method which paints all the components to the panel and therefore allows
	 * the game. It is called by the {@link #run()} method continuously.
	 *  
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint (Graphics g){
		sel = Main.getFrame().getLabelSelected(1) ;
		pt = Main.getFrame().getLabelSelected(2) ;
		pt.setText(new Integer(GPL.getPunti()).toString());
		g.setColor(Sfondo);
		g.fillRect(0,0, dim[0],  dim[1]- (dim[1]/5)+2);
		
		int s;
		
		if(lev1 % 3 == 0) {
			if(index < 24)
				parole[index] = new StringWord(WM) ;  	
			index ++ ;
		}

		for(s = 0 ; s < index && s < 24; s++){
			if(parole[s] == null)
				continue ;
			
			if(parole[s].getY() >= (dim[1]- (dim[1]/6)) && dialog == null){
				go = false ;
				dialog = new GameDialog <JDialog> (new GameOver()) ;
			}
				
			if (parole[s].getSelected()){
				sel.setText(parole[s].getWord());
				ascissa = parole[s].getX() ;
				ordinata = parole[s].getY() ;
				parole[s] = new SubStringWord(parole[s].getWord()) ;
				parole[s].setIndice(1);
			}
			
			parole[s].drawWord(g, parole[s].getWord());
			parole[s].increase();
		}
	
	
		if(GPL.getEliminate() == 24 && dialog == null){
			this.setGo(false) ;
			dialog = new GameDialog <JDialog> (new Winner()) ;
		}
			
		lev1++;
		this.run(); 
	}
	
	/**
	 * Gets the word at index <code>i</code>.
	 *
	 * @param i the index of word returned
	 * @return the string at <code>i</code> index of the array <code>parole
	 * </code>
	 */
	public StringWord getParole(int i){
		return parole[i] ;
	}
	
	/**
	 * Sets the <code>i</code> element of the array <code>parole</code> with a 
	 * new string.
	 *
	 * @param i the index of the word that has to been changed
	 * @param value the string which contains the word
	 */
	public void setParole(int i, StringWord value){
		parole[i] = value ;
	}
	
	/**
	 * Gets the number of words in game minus one.
	 *
	 * @return the number of words already creates during the game minus one
	 */
	public int getIndex(){
		return index ;
	}
	
	/**
	 * Initialize <code>index</code> in order to begin a new level of game 
	 * (there are no words displayed at the beginning of a new level).
	 */
	public void initializeIndex(){
		this.index = 0 ;
	}
	
	/**
	 * Initialize <code>lev1</code> in order to begin a new level of game.
	 */
	public void initializeLev1(){
		this.lev1 = 0 ;
	}
	
	/**
	 * Initialize <code>dialog</code> in order to begin a new level of game.
	 */
	public void initializeDialog(){
		dialog = null ;
	}
	
	/**
	 * Called on an instance of a <code>StringWord</code> gets the x-axis.
	 *
	 * @return the x-axis of a word
	 */
	public int getAscissa(){
		return ascissa ;
	}
	
	/**
	 * Called on an instance of a <code>StringWord</code> gets the y-axis.
	 *
	 * @return the y-axis of a word
	 */
	public int getOrdinata(){
		return ordinata ;
	}
	
	/**
	 * Gets the word manager reference associated with the game panel.
	 *
	 * @return the word manager
	 */
	public WordManager getWordManager(){
		return this.WM ;
	}
	
	/**
	 * Gets the <code>GPL</code>.
	 *
	 * @return the KeyListener of the panel
	 */
	public GPListener getGPL(){
		return this.GPL ;
	}
	
	/**
	 * Method which close the dialog opened (the <code>GameOver</code> one or 
	 * the <code>Winner</code> one).
	 */
	public void closeDialog(){
		if(dialog != null)
			dialog.getDialog().dispose();
	}
	
	/**
	 * Starts or stops the current game.
	 *
	 * @param go boolean which allows the game or stops it
	 */
	public synchronized void setGo(boolean go){
		this.go = go ;
	}
	
	/**
	 * Increase the <code>speed</code> of the game.
	 */
	public void increaseSpeed(){
		if(speed > 0)
			speed = speed - 200 ;
	}
	
	/**
	 * Method which runs the application in loop until the <code>go</code> is 
	 * set to true. 
	 * It calls {@link #paint(java.awt.Graphics)} using <code>repaint()</code> 
	 * and it pauses the current thread (Event Dispatch Thread) using the <code>
	 * speed</code>.
	 * 
	 * @exception InterruptedException if the thread couldn't been slept
	 * @see java.awt.Component#repaint()
	 */
	public void run(){
		if (go){
			try {
				this.repaint();
				Thread.sleep(this.speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	} 
}
