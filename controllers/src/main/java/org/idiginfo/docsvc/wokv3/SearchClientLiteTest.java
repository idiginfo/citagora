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

import com.thomsonreuters.wokmws.v3.woksearchlite.EditionDesc;
import com.thomsonreuters.wokmws.v3.woksearchlite.LiteRecord;
import com.thomsonreuters.wokmws.v3.woksearchlite.QueryParameters;
import com.thomsonreuters.wokmws.v3.woksearchlite.RetrieveParameters;
import com.thomsonreuters.wokmws.v3.woksearchlite.SearchResults;
import com.thomsonreuters.wokmws.v3.woksearchlite.WokSearchLite;
import com.thomsonreuters.wokmws.v3.woksearchlite.WokSearchLiteService;

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
public class SearchClientLiteTest {
    /*
     * comment start: add constant WSDL_URL.
     */
    public static String SERVICE_ENDPOINT_URL = "http://woksearchlite.v3.wokmws.thomsonreuters.com";
    public static String REMOTE_WSDL_URL = SERVICE_ENDPOINT_URL + "?wsdl";
    public static URL LOCAL_WSDL_URL = WokSearchLiteService.WSDL_LOCATION;
    /* comment end */
    private static final QName SERVICE_NAME = WokSearchLiteService.SERVICE;

    private SearchClientLiteTest() {
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
    public static WokSearchLite createPort(URL wsdlURL) throws Exception {
	try {

	    WokSearchLiteService ss = new WokSearchLiteService(wsdlURL,
		    SERVICE_NAME);
	    WokSearchLite port = ss.getWokSearchLitePort();
	    return port;
	} catch (Exception e) {
	    // do error handling. Here we just print stack trace and rethrow
	    // exception
	    e.printStackTrace();
	    throw e;
	}
    }

    public static void setSessionCookie(WokSearchLite port, String sid)
	    throws Exception {
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

    public static void main(String args[]) throws Exception {
	SearchClientLiteTest client = new SearchClientLiteTest();
	SearchResults results = client.runSearch(args);
	System.out.println("search.result.getRecordsFound()="
		+ results.getRecordsFound());
	System.out.println("number of records: " + results.getRecords().size());
	LiteRecord firstRecord = results.getRecords().get(99);
	System.out.println("Title of 10th record '"
		+ firstRecord.getTitle().get(0).getValue().get(0) + "'");
	List<String> authors = firstRecord.getAuthors().get(0).getValue();
	System.out.println("Authors of 1st record:");
	for (int i = 0; i < authors.size(); i++) {
	    System.out.println("\t" + i + ": " + authors.get(i));
	}
    }

    public SearchResults runSearch(String args[]) {
	SearchResults results = null;
	// use the local WSDL used by WSDL2Java to generate the client code
	try {
	    URL wsdlURL = LOCAL_WSDL_URL;
	    WokSearchLite port = createPort(wsdlURL);

	    // Get the session identifier using the Authentication client
	    AuthenticationClient auth = new AuthenticationClient();
	    // use local WSDL
	    auth.createPort(AuthenticationClient.LOCAL_WSDL_URL);
	    String sid = auth.getSessionIdentifier();
	    System.out.println("Session id: " + sid);
	    auth.setSessionCookie();
	    /*
	     * Set up the request context properly, including setting the SID
	     * cookie This setup is required for all operations to work.
	     */
	    setSessionCookie(port, sid);
	    System.out.println("Invoking search...");
	    QueryParameters queryParameters = new QueryParameters();
	    RetrieveParameters retrieveParameters = new RetrieveParameters();

	    // initialize QueryParameters & RetrieveParameters
	    queryParameters.setDatabaseId("WOS");
	    EditionDesc desc_sci = new EditionDesc();
	    desc_sci.setCollection("WOS");
	    desc_sci.setEdition("SCI");
	    queryParameters.getEditions().add(desc_sci);
	    queryParameters.setQueryLanguage("en");
	    queryParameters.setUserQuery("AD=(suicide)");
	    retrieveParameters.setFirstRecord(1);
	    retrieveParameters.setCount(100);
	    // submit the query
	    try {
		results = port.search(queryParameters, retrieveParameters);
		return results;
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    auth.closeSession();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return results;
    }
}