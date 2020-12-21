package com.fleetmanagement.exception;

public class FleetException extends GenericException{
	
	private static final long serialVersionUID = 1L;
	
	private String methodInfo;
	private Exception exception;
	
	private String errorCode;
	private String errorMessage;
	private String errorSummary;
	
	
	public FleetException(ExceptionEnum exceptionEnum, String errorMessage) {
		super();
		this.errorCode = exceptionEnum.getErrorCode();
		this.errorMessage = errorMessage;
		this.errorSummary = exceptionEnum.getErrorSummary();
	}
	
	public FleetException(String methodInfo,
			Exception exception) {
		super();
		this.methodInfo = methodInfo;
		this.exception = exception;
	}

	public String getMethodInfo() {
		return methodInfo;
	}

	public void setMethodInfo(String methodInfo) {
		this.methodInfo = methodInfo;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorSummary() {
		return errorSummary;
	}	
	
	
}
