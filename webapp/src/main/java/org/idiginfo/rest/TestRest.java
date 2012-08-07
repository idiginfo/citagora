package org.idiginfo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.idiginfo.annotate.webapp.RequestProcessor;

/**
 * Hello world!
 * 
 */
@Path("/rest")
@Produces(MediaType.APPLICATION_XML)
public class TestRest {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	RequestProcessor requestProcessor;

	@GET
	@Path("search/{}")
	@Produces(MediaType.TEXT_HTML)
	String search() {
		MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		RestParams params = new RestParams(queryParams);
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.out;
	}

}
