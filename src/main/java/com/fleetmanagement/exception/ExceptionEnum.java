package com.fleetmanagement.exception;

public enum ExceptionEnum {

	INPUT_VALIDATION_EXCEPTION("FLEET_EXCEPTION_001", "Input Validation Exception");
	
	private String errorCode;
	private String errorSummary;
	
	
	
	private ExceptionEnum(String errorCode, String errorSummary) {
		this.errorCode = errorCode;
		this.errorSummary = errorSummary;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorSummary() {
		return errorSummary;
	}
	
	
	
}
