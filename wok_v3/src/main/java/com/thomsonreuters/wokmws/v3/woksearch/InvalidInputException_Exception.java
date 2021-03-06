
package com.thomsonreuters.wokmws.v3.woksearch;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-10-29T13:06:12.232-04:00
 * Generated source version: 2.7.0
 */

@WebFault(name = "InvalidInputException", targetNamespace = "http://woksearch.v3.wokmws.thomsonreuters.com")
public class InvalidInputException_Exception extends Exception {
    
    private com.thomsonreuters.wokmws.v3.woksearch.InvalidInputException invalidInputException;

    public InvalidInputException_Exception() {
        super();
    }
    
    public InvalidInputException_Exception(String message) {
        super(message);
    }
    
    public InvalidInputException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException_Exception(String message, com.thomsonreuters.wokmws.v3.woksearch.InvalidInputException invalidInputException) {
        super(message);
        this.invalidInputException = invalidInputException;
    }

    public InvalidInputException_Exception(String message, com.thomsonreuters.wokmws.v3.woksearch.InvalidInputException invalidInputException, Throwable cause) {
        super(message, cause);
        this.invalidInputException = invalidInputException;
    }

    public com.thomsonreuters.wokmws.v3.woksearch.InvalidInputException getFaultInfo() {
        return this.invalidInputException;
    }
}
