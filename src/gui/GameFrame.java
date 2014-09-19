package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import listeners.MusicButton;
import main.Main;
import net.miginfocom.swing.MigLayout;

/**
 * <p><b>Title:</b> GameFrame</p>
 * <p><b>Description:</b> class which organizes the GUI of the application.
 *
 * @author Diana Sighinolfi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	/** The JPanel which is used for displaying the words. */
	private GamePanel gpanel ;
	
	/** The JPanel which contains the current information of the game. */
	private JPanel infogame ;
	
	/** The JLabel which displays the word selected at the moment.  */
	private JLabel typed ;
	
	/** The JLabel which displays the current level of game. */
	private JLabel level ;
	
	/** The JToggleButton for enabling of disabling the background music. */
	private JToggleButton Music;
	
	/** The invisible Component which creates a distance between two labels. */
	private Component separatore;
	
	/** The JSlides that could set the music volume. */
	private JSlider volume;
	
	/** The JLable which displays the player score. */
	private JLabel punteggio;

	/**
	 * Constructor without parameters which instantiates a new game frame.
	 * 
	 * @throws Throwable returns a message if the application couldn't play the 
	 * music.
	 * @see listeners.MusicButton#music
	 */
	public GameFrame() {
		super("Word Game") ;
		int dim [] = Main.dimensioni() ;
		setBounds(20, 10, dim[0], dim[1]) ;
		setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		setResizable(false);
		
		getContentPane().setLayout(null) ;
		getContentPane().setLayout(new BorderLayout()) ;
		
		gpanel = new GamePanel();
		gpanel.setPreferredSize(new Dimension(dim[0], dim[1]- (dim[1]/5)+2));
		gpanel.setBackground(new Color(153, 204, 255));
		getContentPane().add(gpanel, BorderLayout.NORTH);
		
		infogame = new JPanel();
		infogame.setPreferredSize(new Dimension(dim[0], dim[1]/6));
		infogame.setBackground(new Color(204, 153, 102));
		getContentPane().add(infogame, BorderLayout.SOUTH);
		infogame.setLayout(new MigLayout("", "[103px][][grow][][]", "[44px][][][][][][][][]"));
		
		JLabel selezionata = new JLabel("Word selected:");
		selezionata.setHorizontalAlignment(SwingConstants.LEFT);
		selezionata.setFont(new Font("Calibri", Font.PLAIN, 30));
		infogame.add(selezionata, "cell 0 0,alignx center,aligny top");
		
		separatore = Box.createVerticalStrut(20);
		separatore.setPreferredSize(new Dimension(30, 30));
		infogame.add(separatore, "cell 1 0");
		
		typed = new JLabel("");
		typed.setFont(new Font("Calibri", Font.PLAIN, 30));
		typed.setBackground(new Color(255, 255, 255));
		infogame.add(typed, "cell 2 0,alignx left,aligny top");
		
		Music = new MusicButton();
		Music.setSelected(true);
			try {
				((MusicButton) Music).music("Musica.wav") ;
			} catch (Throwable e) {
				System.out.println("GameFrame::Music "+e.getMessage());
			}
		infogame.add(Music, "cell 4 1,alignx right,aligny top");
		
		volume = new JSlider();
		volume.setLabelTable(volume.createStandardLabels(20));
		volume.setPaintTicks(true);
		volume.setPaintLabels(true);
		volume.setEnabled(true);
		volume.addChangeListener((ChangeListener) Music);
		infogame.add(volume, "cell 3 1");
		
		level = new JLabel("LEVEL 1");
		level.setVerticalAlignment(SwingConstants.TOP);
		level.setFont(new Font("Calibri", Font.BOLD, 30));
		infogame.add(level, "cell 0 1");
		
		JLabel score = new JLabel("Score: ");
		score.setFont(new Font("Calibri", Font.PLAIN, 30));
		infogame.add(score, "flowx,cell 2 1");
		
		punteggio = new JLabel("0");
		punteggio.setFont(new Font("Calibri", Font.PLAIN, 30));
		infogame.add(punteggio, "cell 2 1");
		
		this.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {gpanel.requestFocusInWindow();}
			public void windowClosing(WindowEvent e) {e.getWindow().dispose();}
			});
	}

	/**
	 * Gets the game panel (north).
	 *
	 * @return the gpanel reference
	 */
	public GamePanel getGP(){
		return gpanel;
	}
	
	/**
	 * If i is 1 gets the label <code>typed</code>, else gets the label <code>
	 * punteggio</code>.
	 *
	 * @param i number for selected the label.
	 * @return the reference to the right label
	 */
	public JLabel getLabelSelected(int i){
		if(i == 1)
			return typed ;
		else
			return punteggio ;
	}
	
	/**
	 * Sets the label of the level.
	 *
	 * @param text the new level
	 */
	public void setLevel(String text){
		level.setText(text);
	}

	/**
	 * Gets the actual volume.
	 *
	 * @return the music volume
	 */
	public JSlider getVolume(){
		return volume ;
	}

	/**
	 * Gets the music button.
	 *
	 * @return the music button
	 */
	public MusicButton getMusic(){
		return (MusicButton) Music ;
	}
}
