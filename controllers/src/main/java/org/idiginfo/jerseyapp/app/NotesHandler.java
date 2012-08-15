package org.idiginfo.jerseyapp.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/notes")
@Produces(MediaType.APPLICATION_XML)
public class NotesHandler {

    @GET
    public String list() {
        return "Hello World, I still need some work to be useful!";
    }

}