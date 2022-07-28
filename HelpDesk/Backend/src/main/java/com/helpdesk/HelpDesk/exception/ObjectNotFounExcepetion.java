package com.helpdesk.HelpDesk.exception;

public class ObjectNotFounExcepetion extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjectNotFounExcepetion(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFounExcepetion(String message) {
		super(message);
	}
	
}
