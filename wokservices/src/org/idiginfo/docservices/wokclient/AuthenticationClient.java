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
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import com.thomsonreuters.wokmws.cxf.auth.WOKMWSAuthenticate;
import com.thomsonreuters.wokmws.cxf.auth.WOKMWSAuthenticateService;

public class AuthenticationClient {
	public static String REMOTE_WSDL_URL = "http://search.isiknowledge.com/esti/wokmws/ws/WOKMWSAuthenticate?wsdl";
	public static URL LOCAL_WSDL_URL = WOKMWSAuthenticateService.WSDL_LOCATION;
	private static QName SERVICE_NAME = new QName(
			"http://auth.cxf.wokmws.thomsonreuters.com",
			"WOKMWSAuthenticateService");
	WOKMWSAuthenticateService service;
	WOKMWSAuthenticate port;
	String session_identifier;

	/**
	 * Make a call to the authenticate operation to get a session identifier.
	 * Return the session identifier and also store it in the member variable
	 * (session_identifier).
	 * 
	 * <p/>
	 * The SESSION_MAINTAIN_PROPERTY of the requestContext of the binding
	 * provider/port must set to Boolean.TRUE in order to send, receive and
	 * store cookies. This only needs to be done once for the lifetime of the
	 * binding provider/port.
	 * 
	 * @return the session identifier
	 * @throws Exception
	 */
	public String getSessionIdentifier() throws Exception {
		try {
			/*
			 * comment start: now we have not an authentication account, so
			 * comment out the following to use IP authentication
			 */
			// BindingProvider bp = (BindingProvider)port;
			// Map<String, Object> context = bp.getRequestContext();
			/*
			 * comment start: the next line of code has already been done for
			 * this port in createPort() otherwise the SESSION_MAINTAIN_PROPERTY
			 * needs to be set to Boolean.TRUE
			 */
			// context.put(
			// javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
			// Boolean.valueOf(true)
			// );
			/* comment end */
			//
			// String username = "training";
			// String password = "lapis";
			// context.put(javax.xml.ws.BindingProvider.USERNAME_PROPERTY,
			// username);
			// context.put(javax.xml.ws.BindingProvider.PASSWORD_PROPERTY,
			// password);
			/*
			 * comment start: make the call to the authenticate operation
			 */
			session_identifier = null;
			session_identifier = port.authenticate();
		} catch (Exception e) {
			// Unexpected error while calling the authenticate operation.
			// ¡- finish your exception handling,
			// we are simply printing the stack trace and rethrowing the
			// exception
			e.printStackTrace();
			throw e;
		}
		return session_identifier;
	}

	/**
	 * Close the session: make a call to the closeSession operation.
	 * 
	 * <p/>
	 * The session identifier is sent as an HTTP cookie with name "SID". The
	 * value of the cookie is the session identifier. This cookie is already set
	 * in the requestContext for this binding provider/port within the
	 * {@link #setSessionCookie()} method.
	 * 
	 * @throws Exception
	 * 
	 * @see #setSessionCookie()
	 */
	public void closeSession() throws Exception {
		try {
			/*
			 * comment start: the next three lines of code have already been
			 * done for this port in createPort() otherwise the
			 * SESSION_MAINTAIN_PROPERTY needs to be set to Boolean.TRUE
			 */
			// BindingProvider bp = (BindingProvider)port;
			// Map<String, Object> context = bp.getRequestContext();
			// context.put(
			// javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
			// Boolean.valueOf(true)
			// );
			/* comment end */
			/*
			 * comment start: Create the SID cookie
			 */
			Cookie cookie = new Cookie("SID", session_identifier);
			Client client = ClientProxy.getClient(port);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			httpClientPolicy.setCookie(cookie.getName() + "="
					+ cookie.getValue());
			/* comment end */

			http.setClient(httpClientPolicy);
			port.closeSession();
		} catch (Exception e) {
			// Unexpected error
			// ¡- finish your exception handling,
			// we are simply printing the stack trace and rethrowing the
			// exception
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Put the session cookie into the request context for the binding
	 * provider/port
	 * 
	 * <p/>
	 * Once this is done the session cookie will be set for all outgoing
	 * requests from this port.
	 * 
	 * @throws Exception
	 */
	public void setSessionCookie() throws Exception {
		try {
			/*
			 * comment start: the next three lines of code have already been
			 * done for this port in createPort() otherwise the
			 * SESSION_MAINTAIN_PROPERTY needs to be set to Boolean.TRUE
			 */
			// BindingProvider bp = (BindingProvider)port;
			// Map<String, Object> context = bp.getRequestContext();
			// context.put(
			// javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
			// Boolean.valueOf(true)
			// );
			/* comment end */
			/*
			 * comment start: create the cookie
			 */
			Client client = ClientProxy.getClient(port);
			HTTPConduit http = (HTTPConduit) client.getConduit();
			HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
			/* comment end */
			/*
			 * comment start:
			 * 
			 * pattern of cookie is incorrect.
			 */
			Cookie cookie = new Cookie("SID", session_identifier);
			httpClientPolicy.setCookie(cookie.getName() + "="
					+ cookie.getValue());
			http.setClient(httpClientPolicy);
		} catch (Exception e) {
			// Unexpected error while calling the authenticate service.
			// ¡- finish your exception handling,
			// we are simply printing the stack trace and rethrowing the
			// exception
			e.printStackTrace();
			throw e;
		}
	}

	public void createPort(URL wsdlURL) throws Exception {
		// default to the WSDL used by WSDL2Java
		if (wsdlURL == null) {
			wsdlURL = WOKMWSAuthenticateService.WSDL_LOCATION;
		}
		try {
			service = new WOKMWSAuthenticateService(wsdlURL, SERVICE_NAME);
			port = service.getWOKMWSAuthenticatePort();
			/*
			 * comment start: output HTTP message.
			 */
			Client client = ClientProxy.getClient(port);
			client.getInInterceptors().add(new LoggingInInterceptor());
			client.getOutInterceptors().add(new LoggingOutInterceptor());
			/* comment end */
			/*
			 * Must set the SESSION_MAINTAIN_PROPERTY to Boolean.TRUE
			 */
			BindingProvider bp = (BindingProvider) port;
			Map<String, Object> context = bp.getRequestContext();
			context.put(javax.xml.ws.BindingProvider.SESSION_MAINTAIN_PROPERTY,
					Boolean.valueOf(true));
		} catch (Exception e) {
			// Unexpected error while calling the authenticate service.

			// ¡- finish your exception handling,
			// we are simply printing the stack trace and rethrowing the
			// exception
			e.printStackTrace();
			throw e;
		}
	}

	public static void main(String[] args) throws Exception {
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
				// Unexpected error
				// ¡- finish your exception handling,
				// we are simply printing the stack trace and rethrowing the
				// exception
				e.printStackTrace();
				throw e;
			}
		}
		AuthenticationClient auth = new AuthenticationClient();
		// create the service and port
		auth.createPort(wsdlURL);
		/*
		 * call the authenticate operation: get the session identifier that all
		 * other operations need
		 */
		String identifier = auth.getSessionIdentifier();
		System.out.println("Identifier: " + identifier);
		/*
		 * create the session cookie: make the session identifier available to
		 * all other Web service operations within this service port
		 */
		auth.setSessionCookie();
		/*
		 * call the other operation in this Web service: close the session
		 */
		auth.closeSession();
		System.exit(0);
	}
}
