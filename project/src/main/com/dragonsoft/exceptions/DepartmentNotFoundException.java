package main.com.dragonsoft.exceptions;

public class DepartmentNotFoundException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
