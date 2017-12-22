package com.demo.hm.exception;

public class ValidationException extends RuntimeException {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public ValidationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



}
