package main.com.dragonsoft.exceptions;

public class DuplicateEntryException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateEntryException(String errorMessage) {
        super(errorMessage);
    }
}
