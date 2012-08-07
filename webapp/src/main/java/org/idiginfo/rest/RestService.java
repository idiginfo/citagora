package org.idiginfo.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.idiginfo.annotate.webapp.RequestProcessor;

/**
 * Hello world!
 * 
 */
@Path("/rest")
@Produces(MediaType.APPLICATION_XML)
public class RestService {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	RequestProcessor requestProcessor = new RequestProcessor();

	@GET
	public String hello() {
		return "you are in the rest service";
	}

	@GET
	@Path("citagora/{collection}")
	@Produces(MediaType.TEXT_HTML)
	public String get(@PathParam("collection") String collection) {
		MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		RestParams params = new RestParams(queryParams);
		params.setCollection(collection);
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.body;
	}

}
