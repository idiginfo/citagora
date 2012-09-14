package com.thomsonreuters.wokmws.cxf.woksearchlite;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 2.6.1 2012-08-13T08:31:40.776-04:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "InvalidInputException", targetNamespace = "http://woksearchlite.cxf.wokmws.thomsonreuters.com")
public class InvalidInputException_Exception extends Exception {
	private static final long serialVersionUID = 1L;

	private com.thomsonreuters.wokmws.cxf.woksearchlite.InvalidInputException invalidInputException;

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
			com.thomsonreuters.wokmws.cxf.woksearchlite.InvalidInputException invalidInputException) {
		super(message);
		this.invalidInputException = invalidInputException;
	}

	public InvalidInputException_Exception(
			String message,
			com.thomsonreuters.wokmws.cxf.woksearchlite.InvalidInputException invalidInputException,
			Throwable cause) {
		super(message, cause);
		this.invalidInputException = invalidInputException;
	}

	public com.thomsonreuters.wokmws.cxf.woksearchlite.InvalidInputException getFaultInfo() {
		return this.invalidInputException;
	}
}