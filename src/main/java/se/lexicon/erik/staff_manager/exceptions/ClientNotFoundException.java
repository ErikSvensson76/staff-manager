package se.lexicon.erik.staff_manager.exceptions;

public class ClientNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException() {
		super();		
	}

	public ClientNotFoundException(String message) {
		super(message);		
	}
}
