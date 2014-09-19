package gui;

/**
 * <p><b>Title:</b> GameDialog</p>
 * <p><b>Description:</b> class which could be either a {@link GameOver} dialog
 * or a {@link Winner} because it has a generic type.
 *
 * @author Diana Sighinolfi
 * @version 1.0 
 * @param <dialog> the generic type
 */
public class GameDialog <dialog> {
	/** An object of the generic type. */
	private dialog D = null ;
	
	/**
	 * Constructor which takes a JDialog and instantiates a new game dialog by
	 * assigning it to the generic object defined before.
	 *
	 * @param D the JDialog (<code>GameOver</code> or <code>Winner</code>)
	 */
	public GameDialog(dialog D){
		this.D = D ;
	}
	
	/**
	 * Gets the dialog.
	 *
	 * @return the generic dialog
	 */
	public dialog getDialog(){
		return D ;
	}
}
