package com.thomsonreuters.wokmws.cxf.auth;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 2.6.1 2012-08-13T08:29:43.736-04:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "InvalidInputException", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com")
public class InvalidInputException_Exception extends Exception {
	private static final long serialVersionUID = 1L;

	private com.thomsonreuters.wokmws.cxf.auth.InvalidInputException invalidInputException;

	public InvalidInputException_Exception() {
		super();
	}

	public InvalidInputException_Exception(String message) {
		super(message);
	}

	public InvalidInputException_Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidInputException_Exception(
			String message,
			com.thomsonreuters.wokmws.cxf.auth.InvalidInputException invalidInputException) {
		super(message);
		this.invalidInputException = invalidInputException;
	}

	public InvalidInputException_Exception(
			String message,
			com.thomsonreuters.wokmws.cxf.auth.InvalidInputException invalidInputException,
			Throwable cause) {
		super(message, cause);
		this.invalidInputException = invalidInputException;
	}

	public com.thomsonreuters.wokmws.cxf.auth.InvalidInputException getFaultInfo() {
		return this.invalidInputException;
	}
}
