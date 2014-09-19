package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.Main;

/**
 * <p><b>Title:</b> MusicButton</p>
 * <p><b>Description:</b> The listener interface for receiving either button or
 * slider events.
 * The class that is interested in processing a button event creates an object 
 * which is registered with a component using the component's 
 * <code>addActionListener</code> method. If the class is processing a slider 
 * event, it must create an object  which is registered with a component using 
 * the component's <code>addChangeListener</code> method.
 * When the button or slider event occurs, that object's appropriate method is 
 * invoked.</p>
 * 
 * @author Diana Sighinolfi
 * @version 1.0
 * @see gui.GameFrame#getMusic()
 * @see gui.GameFrame#getVolume()
 */
@SuppressWarnings("serial")
public class MusicButton extends JToggleButton implements ActionListener, ChangeListener {
	/** The clip which contains the music. */
	Clip clip = null ;
	
	/** The Music control. */
	FloatControl MusicControl = null ;
	
	/** The attributes which contains the icons of the button. */
	Icon on = new ImageIcon("On.png"), 
		 off = new ImageIcon("Off.png");
	
	/**
	 * Constructor without parameters which instantiates a new music button.
	 */
	public MusicButton(){
		super(new ImageIcon("On.png"));
		this.addActionListener(this);
	}
	
	/**
	 * Method checks if the button is selected or not in order to allow the 
	 * music or not.
	 * {@inheritDoc}
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (this.isSelected()){
            this.setIcon(on);
            clip.loop(clip.LOOP_CONTINUOUSLY );
        } else {
        	 this.setIcon(off);
        	clip.stop();
        }
		Main.getFrame().getGP().requestFocusInWindow() ; 
	}
	
	/**
	 * Method which play the music by reading an audio file.
	 *
	 * @param s the name of file wav
	 * @throws Throwable {@link gui.GameFrame#GameFrame()}
	 * @exception UnsupportedAudioFileException if the file has a format 
	 * unsupported
	 * @exception LineUnavailableException if a line cannot be opened because it 
	 * is unavailable
	 * @exception IOException if an error during the opening of the clip occurs 
	 */
	@SuppressWarnings("static-access")
	public void music(String s) throws Throwable {
		AudioInputStream audio = null;
		File file = new File(s);
		
			try{
				audio = AudioSystem.getAudioInputStream(file) ;}
			catch(UnsupportedAudioFileException e) {
				System.out.println(e.getMessage());
			}
		
			try{
				clip = AudioSystem.getClip();}
			catch(LineUnavailableException e) {
				System.out.println(e.getMessage());
			}
			
			try{
				clip.open(audio);}
			catch(IOException e) {
				System.out.println(e.getMessage());
			} 
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
		
	}
	
	/**
	 * Gets the audio clip.
	 *
	 * @return the audio clip reference
	 */
	public Clip getClip(){
		return clip ;
	}
	

	/**
	 * Method which sets the volume of the audio clip if the state of the slider
	 * component in {@link gui.GameFrame#GameFrame()} changes.
	 * {@inheritDoc}
	 * 
	 * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
	 */
	@Override
	 public void stateChanged(ChangeEvent e){
		setVolume();
		Main.getFrame().getGP().requestFocusInWindow() ; 
    }

	/**
	 * Sets the volume of the music.
	 */
	public void setVolume() {
        double value = Main.getFrame().getVolume().getValue() / 100.0;

            try {
            	MusicControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            	float dB = (float)(Math.log(value==0.0?0.0001:value)/Math.log(10.0)*20.0);
            	MusicControl.setValue(dB);
            } catch (Exception ex) {
            	ex.getMessage();
            }
        }	
}
