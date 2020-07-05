package main.com.dragonsoft.exceptions;

public class EmailFormatException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailFormatException(String errorMessage) {
        super(errorMessage);
    }
}