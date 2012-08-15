package org.idiginfo.jerseyapp.app1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.api.Responses;

/**
 * Hello world!
 * 
 */
@Path("/hello")
@Produces(MediaType.TEXT_HTML)
public class Hello {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	public String hi() {
		return "Hello World, I am very simple";
	}

	@GET
	@Path("yo/{name}")
	public String yo(@PathParam("name") String name) {
		return "Hello " + name;
	}

	@GET
	@Path("xxx")
	public String nonono() {
		Response response = Response.status(Responses.NOT_FOUND)
				.entity("Sorry!").type("text/plain").build();
		WebApplicationException e = new WebApplicationException(response);
		throw e;
	}
}
