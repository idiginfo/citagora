package org.idiginfo.docservices.wokclient;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.thomsonreuters.wokmws.cxf.woksearchlite.EditionDesc;
import com.thomsonreuters.wokmws.cxf.woksearchlite.QueryParameters;
import com.thomsonreuters.wokmws.cxf.woksearchlite.RetrieveParameters;
import com.thomsonreuters.wokmws.cxf.woksearchlite.TimeSpan;
import com.thomsonreuters.wokmws.cxf.woksearchlite.WokSearchLite;
import com.thomsonreuters.wokmws.cxf.woksearchlite.WokSearchLiteService;

/**
 * A very simple high-level client program to access the WokSearchLite Web
 * service. The search operation is called.
 * 
 * <p/>
 * The very simple high-level AuthenticationClient is also used to create an ISI
 * WOK WS session identifier and to close the session when we are finished.
 * 
 * <p/>
 * This client program also illustrates two different ways to create the
 * WokSearchLite port/client. The first {@link #createPort(URL)} uses standard
 * JAX-WS APIs. The second {@link #createPortUsingFactory()} uses a CXF Factory
 * that provides more control.
 * 
 * <p/>
 * There are even more ways to create the port available. Refer to the JAX-WS
 * API documentation.
 */
public class SearchClient {
	/*
	 * comment start: add constant WSDL_URL.
	 */
	public static String SERVICE_ENDPOINT_URL = "http://search.isiknowledge.com/esti/wokmws/ws/WokSearchLite";
	public static String REMOTE_WSDL_URL = SERVICE_ENDPOINT_URL + "?wsdl";
	public static URL LOCAL_WSDL_URL = WokSearchLiteService.WSDL_LOCATION;
	/* comment end */
	private static final QName SERVICE_NAME = new QName(
			"http://woksearchlite.cxf.wokmws.thomsonreuters.com",
			"WokSearchLiteService");

	private SearchClient() {
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
			// execption
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Create the port for WokSearchLite Web service using a JAX-WS factory
	 * class and the service endpoint url.
	 * 
	 * <p/>
	 * Also, set the logging interceptors
	 * 
	 * @return the port (e.g. instance of the WokSearchLite interface) for the
	 *         WokSearchLite Web service
	 * 
	 * @throws Exception
	 */
	public static WokSearchLite createPortUsingFactory() throws Exception {
		try {
			/*
			 * comment start: CXF Factory for creating JAX-WS Proxies. This
			 * class provides access to internal properties used to setup
			 * proxies. Using it provides more control than the standard JAX-WS
			 * APIs.
			 */
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.getInInterceptors().add(new LoggingInInterceptor());
			factory.getOutInterceptors().add(new LoggingOutInterceptor());
			factory.setServiceClass(WokSearchLite.class);
			factory.setAddress(SERVICE_ENDPOINT_URL);
			WokSearchLite port = (WokSearchLite) factory.create();
			return port;
		} catch (Exception e) {
			// do error handling. Here we just print stack trace and rethrow
			// execption
			e.printStackTrace();
			throw e;
		}
	}

	public static void setSessionCookie(WokSearchLite port, String sid)
			throws Exception {
		Client client = ClientProxy.getClient(port);
		/*
		 * comment start:
		 * 
		 * output HTTP message.
		 * 
		 * if you are using createPort() method then uncomment the following two
		 * lines. if you are using createPortUsingFactory() method then leave
		 * these two lines commented out.
		 */
		// client.getInInterceptors().add(new LoggingInInterceptor());
		// client.getOutInterceptors().add(new LoggingOutInterceptor());
		/* comment end */
		BindingProvider bp = (BindingProvider) port;
		Map<String, Object> context = bp.getRequestContext();
		context.put(javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
				true);
		Cookie cookie = new Cookie("SID", sid);
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		/*
		 * comment start: pattern of cookie is incorrect.
		 */
		// httpClientPolicy.setCookie(cookie.toString());
		httpClientPolicy.setCookie(cookie.getName() + "=" + cookie.getValue());
		/* comment end */
		http.setClient(httpClientPolicy);
	}

	public static void main(String args[]) throws Exception {
		/*
		 * comment start: Select a WSDL: Each WSDL may define the save
		 * service(s) but have a different service endpoint address
		 * 
		 * We will default to the local one used by WSDL2Java
		 */
		// use the local WSDL used by WSDL2Java to generate the client code
		URL wsdlURL = LOCAL_WSDL_URL;
		// use the remote WSDL
		// wsdlURL = new URL(REMOTE_WSDL_URL);
		// or pass in your own local WSDL:
		if (args.length > 0) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				// do error handling. Here we just print stack trace and rethrow

				// execption
				e.printStackTrace();
				throw e;
			}
		}
		/* comment end */
		/*
		 * comment start: use one of the following two methods to create the Web
		 * service port
		 */
		// WokSearchLite port = createPort( wsdlURL );
		WokSearchLite port = createPortUsingFactory();
		/*
		 * comment start: Get the session identifier using the Authentication
		 * client
		 */
		AuthenticationClient auth = new AuthenticationClient();
		// use remote WSDL
		// auth.createPort( new URL( AuthenticationClient.REMOTE_WSDL_URL ) );
		// use local WSDL
		auth.createPort(AuthenticationClient.LOCAL_WSDL_URL);
		String sid = auth.getSessionIdentifier();
		System.out.println("Session id: " + sid);
		auth.setSessionCookie();
		/* end comment */
		/*
		 * Set up the request context properly, including setting the SID cookie
		 * This setup is required for all operations to work.
		 */
		setSessionCookie(port, sid);
		System.out.println("Invoking search...");
		com.thomsonreuters.wokmws.cxf.woksearchlite.QueryParameters _search_queryParameters = new QueryParameters();
		com.thomsonreuters.wokmws.cxf.woksearchlite.RetrieveParameters _search_retrieveParameters = new RetrieveParameters();
		/*
		 * comment start: initialize QueryParameters & RetrieveParameters
		 */
		_search_queryParameters.setDatabaseID("WOS");
		EditionDesc desc_sci = new EditionDesc();
		desc_sci.setCollection("WOS");
		desc_sci.setEdition("SCI");
		EditionDesc desc_ssci = new EditionDesc();
		desc_ssci.setCollection("WOS");
		desc_ssci.setEdition("SSCI");
		EditionDesc desc_ahci = new EditionDesc();
		desc_ahci.setCollection("WOS");
		desc_ahci.setEdition("AHCI");
		EditionDesc desc_ic = new EditionDesc();
		desc_ic.setCollection("WOS");

		desc_ic.setEdition("IC");
		EditionDesc desc_ccr = new EditionDesc();
		desc_ccr.setCollection("WOS");
		desc_ccr.setEdition("CCR");
		EditionDesc desc_istp = new EditionDesc();
		desc_istp.setCollection("WOS");
		desc_istp.setEdition("ISTP");
		EditionDesc desc_isshp = new EditionDesc();
		desc_isshp.setCollection("WOS");
		desc_isshp.setEdition("ISSHP");
		_search_queryParameters.getEditions().add(desc_sci);
		// _search_queryParameters.getEditions().add(desc_ssci);
		// _search_queryParameters.getEditions().add(desc_ahci);
		// _search_queryParameters.getEditions().add(desc_ic);
		// _search_queryParameters.getEditions().add(desc_ccr);
		// //_search_queryParameters.getEditions().add(desc_istp);
		// _search_queryParameters.getEditions().add(desc_ssci);
		// _search_queryParameters.setQueryLanguage("en");
		_search_queryParameters.setUserQuery("AD=(suicide)");
		TimeSpan timeSpan = new TimeSpan();
		timeSpan.setBegin("1900-01-01");
		timeSpan.setEnd("2009-12-31");
		_search_queryParameters.setTimeSpan(timeSpan);
		_search_retrieveParameters.setFirstRecord(1);
		_search_retrieveParameters.setCount(100);
		/* comment end */
		try {
			com.thomsonreuters.wokmws.cxf.woksearchlite.SearchResults _search__return = port
					.search(_search_queryParameters, _search_retrieveParameters);
			System.out.println("search.result.getRecordsFound()="
					+ _search__return.getRecordsFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
		auth.closeSession();
		System.exit(0);
	}
}