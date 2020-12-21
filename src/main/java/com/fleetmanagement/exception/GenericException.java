package com.fleetmanagement.exception;

public class GenericException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorSummary;
	private String errorMessage;
	
	public GenericException() {
		
	}

	public GenericException(String errorCode, String errorSummary, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorSummary() {
		return errorSummary;
	}
	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
		
}
