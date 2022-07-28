package com.helpdesk.HelpDesk.exception;

public class DataIntegrityValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataIntegrityValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityValidationException(String message) {
		super(message);
	}
	
}
