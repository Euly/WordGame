package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

import main.Main;

/**
 * <p><b>Title:</b> ButtonListener</p>
 * <p><b>Description:</b> The listener interface for receiving button events.
 * The class that is interested in processing a button event implements this 
 * interface, and the object created with that class is registered with a 
 * component using the component's <code>addActionListener</code> method. When
 * the button event occurs, that object's appropriate method is invoked.</p>
 *
 * @author Diana Sighinolfi
 * @version 1.0
 * @see gui.Winner
 * @see gui.GameOver
 */
public class ButtonListener implements ActionListener {
	/**
	 * Method which execute different actions following the action command of
	 * the button pressed.
	 * {@inheritDoc}
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Exit"))
			System.exit(0);
		
		if(e.getActionCommand().equals("Try Again")){
			Main.getFrame().getGP().closeDialog();
			Main.newFrame();
		}
		
		if(e.getActionCommand().equals("Next Level")){
			Main.getFrame().getGP().closeDialog();
			
			FileReader freader = null ;
		
			Main.getFrame().getGP().initializeIndex();
			Main.getFrame().getGP().initializeLev1();
			Main.getFrame().getGP().initializeDialog();
			Main.getFrame().getGP().getGPL().initializeEliminate();
			Main.getFrame().getGP().increaseSpeed();
			
			Main.getFrame().getGP().getWordManager().increaseLevel();
			int ActualLevel = Main.getFrame().getGP().getWordManager().getLevel() + 1;
			Main.getFrame().setLevel("LEVEL "+new Integer(ActualLevel).toString());
			Main.getFrame().getGP().getWordManager().readFile(freader);
			Main.getFrame().getGP().getWordManager().LoadWords();
			Main.getFrame().getGP().setGo(true);
			Main.getFrame().getGP().run();
		}
	}
}
