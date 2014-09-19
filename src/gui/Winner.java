package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import listeners.ButtonListener;
import net.miginfocom.swing.MigLayout;

/**
 * <p><b>Title:</b> Winner</p>
 * <p><b>Description:</b> class which creates a new frame when the player 
 * reaches the end of a level.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Winner extends JDialog {
	/** The panel of the JDialog. */
	private final JPanel contentPanel = new JPanel();
	
	/** The ImageIcon which displays a gif image. */
	private ImageIcon you_win ;
	
	/** The label used to display the ImageIcon <code>you_win</code>. */
	private JLabel imglabel ;
	
	/** The buttons "Next Level" and "Exit". */
	private JButton nextLevel, exit ;
	
	/** The button listeners associated with the "Exit" and "Next Level" 
	 * buttons. */
	private ButtonListener ExitListener, NextLevelListener ;
	
	/**
	 * Constructor without parameters which instantiates a new winner dialog.
	 * 
	 * @throws Exception if a problem with the gif image occurs
	 */
	public Winner() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(400, 200));
		setLocation(screenSize.width/2 - 400/2, screenSize.height/2 - 180/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		nextLevel = new JButton("Next Level");
		buttonPane.add(nextLevel);
		getRootPane().setDefaultButton(nextLevel);
		NextLevelListener = new ButtonListener() ;
		nextLevel.addActionListener(NextLevelListener);
		
		exit = new JButton("Exit");
		buttonPane.add(exit);
		ExitListener = new ButtonListener();
		exit.addActionListener(ExitListener);
		
		try {
			you_win = new ImageIcon(this.getClass().getResource("winner.gif"));
			contentPanel.setLayout(new MigLayout("", "[496px]", "[124px][][][]"));
			
			imglabel = new JLabel();
			imglabel.setIcon(you_win);
			contentPanel.add(imglabel, "cell 0 1,alignx center,aligny center") ;
			this.setVisible(true);
			
		} catch(Exception e){
			System.out.println("Game::Winner:: "+e.getMessage());
		}
	}
}