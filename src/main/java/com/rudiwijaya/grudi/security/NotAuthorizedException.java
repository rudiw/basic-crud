package com.rudiwijaya.grudi.security;

/**
 * @author ceefour
 *
 */
@SuppressWarnings("serial")
public class NotAuthorizedException extends SecurityException {

	/**
	 * 
	 */
	public NotAuthorizedException() {
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NotAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public NotAuthorizedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public NotAuthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param cause
	 * @param message
	 * @param args
	 */
	public NotAuthorizedException(Throwable cause, String message, Object... args) {
		super(cause, message, args);
	}

}
