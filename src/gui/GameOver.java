package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
 * <p><b>Title:</b> GameOver</p>
 * <p><b>Description:</b> class which creates a new frame when the player 
 * doesn't win.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameOver extends JDialog {
	/** The panel of the JDialog. */
	private JPanel contentPanel = new JPanel();
	
	/** The ImageIcon which displays a gif image. */
	private ImageIcon gameover ;
	
	/** The label used to display the ImageIcon <code>gameover</code>. */
	private JLabel imglabel ;
	
	/** The buttons "Try Again" and "Exit". */
	private JButton tryAgain, exit ;
	
	/** The button listeners associated with the "Exit" and "Try Again" buttons. */
	private ButtonListener ExitListener, TryAgainListener ;
	
	/**
	 * Constructor without parameters which instantiates a new game over dialog.
	 * 
	 * @throws Exception if a problem with the gif image occurs
	 */
	public GameOver() {
		contentPanel.setBackground(Color.BLACK);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension(528, 272));
		setLocation(screenSize.width/2 - 528/2, screenSize.height/2 - 300/2);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		tryAgain = new JButton("Try Again");
		buttonPane.add(tryAgain);
		getRootPane().setDefaultButton(tryAgain);
		TryAgainListener = new ButtonListener() ;
		tryAgain.addActionListener(TryAgainListener);
		
		exit = new JButton("Exit");
		buttonPane.add(exit);
		ExitListener = new ButtonListener();
		exit.addActionListener(ExitListener);
		
		try {
			gameover = new ImageIcon(this.getClass().getResource("GameOver.gif"));
			contentPanel.setLayout(new MigLayout("", "[496px]", "[124px][][][]"));
			
			imglabel = new JLabel();
			imglabel.setBackground(new Color(238, 238, 238));
			imglabel.setIcon(gameover);
			contentPanel.add(imglabel, "cell 0 1,alignx left,aligny top") ;
			this.setVisible(true);
		
		} catch(Exception e){
			System.out.println("Game::GameOver:: "+e.getMessage());
		}
	}	
}
