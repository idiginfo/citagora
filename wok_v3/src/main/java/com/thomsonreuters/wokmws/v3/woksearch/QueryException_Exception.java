
package com.thomsonreuters.wokmws.v3.woksearch;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-10-29T13:06:12.186-04:00
 * Generated source version: 2.7.0
 */

@WebFault(name = "QueryException", targetNamespace = "http://woksearch.v3.wokmws.thomsonreuters.com")
public class QueryException_Exception extends Exception {
    
    private com.thomsonreuters.wokmws.v3.woksearch.QueryException queryException;

    public QueryException_Exception() {
        super();
    }
    
    public QueryException_Exception(String message) {
        super(message);
    }
    
    public QueryException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public QueryException_Exception(String message, com.thomsonreuters.wokmws.v3.woksearch.QueryException queryException) {
        super(message);
        this.queryException = queryException;
    }

    public QueryException_Exception(String message, com.thomsonreuters.wokmws.v3.woksearch.QueryException queryException, Throwable cause) {
        super(message, cause);
        this.queryException = queryException;
    }

    public com.thomsonreuters.wokmws.v3.woksearch.QueryException getFaultInfo() {
        return this.queryException;
    }
}
