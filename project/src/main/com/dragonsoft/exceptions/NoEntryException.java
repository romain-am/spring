package main.com.dragonsoft.exceptions;

public class NoEntryException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoEntryException(String errorMessage) {
        super(errorMessage);
    }
}