package main.com.dragonsoft.exceptions;

public class TeamNotFoundException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
