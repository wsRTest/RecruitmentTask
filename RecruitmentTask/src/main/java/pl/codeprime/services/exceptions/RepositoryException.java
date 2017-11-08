package pl.codeprime.services.exceptions;

public class RepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4380748277171695833L;

	public RepositoryException() {
		super();
	}

	public RepositoryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

	public RepositoryException(String message) {
		super(message);
	}

	public RepositoryException(Throwable cause) {
		super(cause);
	}
	
	
	
}
