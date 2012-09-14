package com.thomsonreuters.wokmws.cxf.auth;

import javax.xml.ws.WebFault;

/**
 * This class was generated by Apache CXF 2.6.1 2012-08-13T08:29:43.653-04:00
 * Generated source version: 2.6.1
 */

@WebFault(name = "QueryException", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com")
public class QueryException_Exception extends Exception {
	private static final long serialVersionUID = 1L;

	private com.thomsonreuters.wokmws.cxf.auth.QueryException queryException;

	public QueryException_Exception() {
		super();
	}

	public QueryException_Exception(String message) {
		super(message);
	}

	public QueryException_Exception(String message, Throwable cause) {
		super(message, cause);
	}

	public QueryException_Exception(String message,
			com.thomsonreuters.wokmws.cxf.auth.QueryException queryException) {
		super(message);
		this.queryException = queryException;
	}

	public QueryException_Exception(String message,
			com.thomsonreuters.wokmws.cxf.auth.QueryException queryException,
			Throwable cause) {
		super(message, cause);
		this.queryException = queryException;
	}

	public com.thomsonreuters.wokmws.cxf.auth.QueryException getFaultInfo() {
		return this.queryException;
	}
}