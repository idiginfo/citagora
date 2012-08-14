package org.idiginfo.docservices.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.webapp.DocServicesParams;
import org.idiginfo.docservices.webapp.RequestProcessor;

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
	@Path("citagora/")
	@Produces(MediaType.TEXT_HTML)
	public Response get(@QueryParam("collection") String collection) {
		MultivaluedMap<String, String> queryParams = uriInfo
				.getQueryParameters();
		ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
		params.setCollection(collection);
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		// return result.body as a Response object;
		// creating and returning a Response object allows the charset to be specified
		return Response
				.status(result.statusCode)
				.entity(result.body)
				.header(HttpHeaders.CONTENT_TYPE,
						MediaType.TEXT_HTML + "; charset=UTF-8").build();

	}

}
