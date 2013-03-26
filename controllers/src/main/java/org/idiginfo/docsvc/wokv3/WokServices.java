package org.idiginfo.docsvc.wokv3;

import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfor.docservices.wokv3client.AuthenticationClient;
import org.idiginfor.docservices.wokv3client.SearchClientTest;

import com.thomsonreuters.wokmws.v3.woksearch.*;

/**
 * A very simple high-level client program to access the WokSearchLite Web
 * service. The search operation is called.
 * 
 * <p/>
 * The very simple high-level AuthenticationClient is also used to create an ISI
 * WOK WS session identifier and to close the session when we are finished.
 * 
 * <p/>
 * This client program also illustrates one way to create the WokSearchLite
 * port/client {@link #createPort(URL)} using standard JAX-WS APIs.
 * 
 */
public class WokServices {
    /*
     * comment start: add constant WSDL_URL.
     */
    public static String SERVICE_ENDPOINT_URL = "http://woksearch.v3.wokmws.thomsonreuters.com";
    public static String REMOTE_WSDL_URL = SERVICE_ENDPOINT_URL + "?wsdl";
    public static URL LOCAL_WSDL_URL = WokSearchService.WSDL_LOCATION;
    /* comment end */
    private static final QName SERVICE_NAME = WokSearchService.SERVICE;

    WokSearch port = null;
    AuthenticationClient auth = new AuthenticationClient();
    public static final String DATABASE_ID = "WOS";
    public static final String EDITION = "SCI";
    private static final String COLLECTION_ID = "WOS";
    private static final String QUERY_LANGUAGE = "en";

    public WokServices() {
    }

    /**
     * Create the port for WokSearchLite Web service using the
     * WokSearchLiteService class.
     * 
     * @param wsdlURL
     *            the url of the WSDL
     * 
     * @return the port (e.g. instance of the WokSearchLite interface) for the
     *         WokSearchLite Web service
     * 
     * @throws Exception
     */
    WokSearch createPort(URL wsdlURL) throws Exception {
	try {

	    WokSearchService ss = new WokSearchService(wsdlURL, SERVICE_NAME);
	    port = ss.getWokSearchPort();
	    return port;
	} catch (Exception e) {
	    // do error handling. Here we just print stack trace and rethrow
	    // exception
	    e.printStackTrace();
	    throw e;
	}
    }

    void setSessionCookie(WokSearch port, String sid) throws Exception {
	Client client = ClientProxy.getClient(port);
	client.getInInterceptors().add(new LoggingInInterceptor());
	client.getOutInterceptors().add(new LoggingOutInterceptor());

	BindingProvider bp = (BindingProvider) port;
	Map<String, Object> context = bp.getRequestContext();
	context.put(javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
		true);
	Cookie cookie = new Cookie("SID", sid);
	HTTPConduit http = (HTTPConduit) client.getConduit();
	HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
	httpClientPolicy.setCookie(cookie.getName() + "=" + cookie.getValue());
	http.setClient(httpClientPolicy);
    }

    public String authenticate() throws Exception {
	URL wsdlURL = LOCAL_WSDL_URL;
	createPort(wsdlURL);

	// Get the session identifier using the Authentication client
	// use local WSDL
	auth.createPort(AuthenticationClient.LOCAL_WSDL_URL);
	String sid = auth.getSessionIdentifier();
	auth.setSessionCookie();
	/*
	 * Set up the request context properly, including setting the SID cookie
	 * This setup is required for all operations to work.
	 */
	setSessionCookie(port, sid);
	return sid;
    }

    public FullRecordSearchResults runSearch(ApiParams params) {
	FullRecordSearchResults results = null;
	// use the local WSDL used by WSDL2Java to generate the client code
	for (int i = 0; i < 2; i++) { // try twice
	    try {
		String sid = authenticate();
	    } catch (Exception e) { // problems with authentication
		if (i == 0) {
		    // ignore first error
		    continue;
		} else {
		    e.printStackTrace();
		    return null;
		}
	    }
	    QueryParameters queryParameters = new QueryParameters();
	    RetrieveParameters retrieveParameters = new RetrieveParameters();

	    // initialize QueryParameters & RetrieveParameters
	    queryParameters.setDatabaseId(DATABASE_ID);
	    EditionDesc desc_sci = new EditionDesc();
	    desc_sci.setCollection(COLLECTION_ID);
	    desc_sci.setEdition(EDITION);
	    queryParameters.getEditions().add(desc_sci);
	    queryParameters.setQueryLanguage(QUERY_LANGUAGE);
	    queryParameters.setUserQuery("TS=(" + params.getKeyword() + ")");

	    retrieveParameters.setFirstRecord(params.getFirstResult());
	    retrieveParameters.setCount(params.getNumResults());
	    // submit the query
	    // try twice
	    try {
		results = port.search(queryParameters, retrieveParameters);
		return results;
	    } catch (Exception e) {
		if (i == 0) {
		    // ignore first error
		} else {
		    e.printStackTrace();
		    return null;
		}
	    }
	}
	try {
	    auth.closeSession();
	} catch (Exception e) {
	    // don't know why this fails
	    e.printStackTrace();
	}
	return results;
    }
}