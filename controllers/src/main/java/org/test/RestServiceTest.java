package org.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import org.idiginfo.docsvc.controller.request.RequestProcessor;

/**
 * Hello world!
 * 
 */
@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class RestServiceTest {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	RequestProcessor requestProcessor = new RequestProcessor();

	@GET
	public String hello() {
		return "you are in the test service";
	}

	@GET
	@Path("/search")
	@Produces(MediaType.TEXT_HTML)
	public String get(@QueryParam("collection") String collection) {
			return "get with param: "+collection;
	}
}
