package org.idiginfo.jerseyapp.app1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Hello world!
 * 
 */
@Path("/hello")
@Produces(MediaType.APPLICATION_XML)
public class Hello {
	@GET
	public String list() {
		return "Hello World, I am very simple";
	}

}
