package main;

import gui.GameFrame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.sound.sampled.Clip;

/**
 * <p> <b>Title:</b> Main</p>
 * <p><b>Description:</b> Class which contains the <code>main</code> method.
 * This is a game application: the aim of the game is to "kill" the words by 
 * writing them quickly before any word reaches the baseline.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
public class Main {
	/** Refers to the main frame */
	private static GameFrame gframe ;
	
	/** Contains the dimension of the computer screen*/
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/** 
	 * This method starts the application by creating the main frame.
	 * 
	 * @param args There are no arguments in input used.
	 */
	public static void main(String[] args) {
		createFrame();
	}
	
	/**
	 * Method which returns the refecence to the main frame <code>GameFrame</code>
	 * @return gframe
	 * @see GameFrame
	 */
	public static GameFrame getFrame(){
		return  gframe ;
	}
	
	/**
	 * Method which create the application frame at the start of the program or 
	 * it's used when the player restarts the application after a game over.
	 */
	public static void createFrame(){
		gframe = new GameFrame();
		
		int dim [] = dimensioni() ;
        gframe.setLocation(screenSize.width/2 - dim[0]/2, screenSize.height/2 - (dim[1]/2 + 15));
        gframe.setPreferredSize(new Dimension(dim[0],dim[1]));
		gframe.setVisible(true);
	}
	
	/**
	 * Method which restarts the application after a game over. It destroy the
	 * old frame and creates a new one by calling <code>createFrame</code>.
	 */
	public static void newFrame(){
		Clip clip = gframe.getMusic().getClip() ;
		clip.stop();
		gframe.dispose();
		createFrame();
	}
	
	/**
	 * Method which sets the right dimension to the game frame.
	 * 
	 * @return An array of 2 elements which contains the width and the height 
	 * of the frame.
	 */
	public static int [] dimensioni(){
		int dim[] = new int[2] ;
		dim[0] = (screenSize.width *85)/100;
		dim[1] = (screenSize.height *85)/100;
		return dim ;
	}

}
