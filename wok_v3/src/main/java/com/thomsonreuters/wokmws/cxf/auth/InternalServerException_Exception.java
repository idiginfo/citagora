
package com.thomsonreuters.wokmws.cxf.auth;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.7.0
 * 2012-10-29T11:32:07.507-04:00
 * Generated source version: 2.7.0
 */

@WebFault(name = "InternalServerException", targetNamespace = "http://auth.cxf.wokmws.thomsonreuters.com")
public class InternalServerException_Exception extends Exception {
    
    private com.thomsonreuters.wokmws.cxf.auth.InternalServerException internalServerException;

    public InternalServerException_Exception() {
        super();
    }
    
    public InternalServerException_Exception(String message) {
        super(message);
    }
    
    public InternalServerException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerException_Exception(String message, com.thomsonreuters.wokmws.cxf.auth.InternalServerException internalServerException) {
        super(message);
        this.internalServerException = internalServerException;
    }

    public InternalServerException_Exception(String message, com.thomsonreuters.wokmws.cxf.auth.InternalServerException internalServerException, Throwable cause) {
        super(message, cause);
        this.internalServerException = internalServerException;
    }

    public com.thomsonreuters.wokmws.cxf.auth.InternalServerException getFaultInfo() {
        return this.internalServerException;
    }
}
