package main.com.dragonsoft.exceptions;

public class NoPageAssociatedToRoleException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoPageAssociatedToRoleException(String errorMessage) {
        super(errorMessage);
    }

}
