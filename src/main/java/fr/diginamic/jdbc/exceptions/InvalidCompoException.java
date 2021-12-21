package fr.diginamic.jdbc.exceptions;

/** Cette classe contient l'exception qui est levée lorsqu'une erreur est détectée au niveau des traitements des compos  
 * @author Jean-Philippe FRANCISCO
 */
public class InvalidCompoException extends Exception {
	
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** Constructeur de l'exception  */
	public InvalidCompoException() {
	}

	/** Constructeur de l'exception
	 * @param message Message
	 */
	public InvalidCompoException(String message) {
		super(message);
	}

	/** Constructeur de l'exception
	 * @param cause Cause
	 */
	public InvalidCompoException(Throwable cause) {
		super(cause);
	}

	/** Constructeur de l'exception
	 * @param message Message
	 * @param cause Cause
	 */
	public InvalidCompoException(String message, Throwable cause) {
		super(message, cause);
	}

	/** Constructeur de l'exception
	 * @param message Message
	 * @param cause Cause
	 * @param enableSuppression EnableSuppression
	 * @param writableStackTrace WritableStackTrace
	 */
	public InvalidCompoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
