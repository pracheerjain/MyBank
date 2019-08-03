package hcl.mybankapp.mybankapp.exception;

public class UserIsInactiveException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIsInactiveException(String message) {
		super(message);
	}
}
