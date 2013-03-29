package org.idiginfo.docsvc.wokv3;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class WoKRestService {

    WokServices service = new WokServices();
    Gson gson = null;// CrossrefService.getGson();
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    @GET
    @Path(value = "/match")
    public Response getCrossref(@QueryParam("ref") List<String> refs) {
	return createResponse(null);
    }

    private Response createResponse(JsonElement matches) {
	String result = gson.toJson(matches);
	return Response
		.status(Status.OK)
		.entity(result)
		.header(HttpHeaders.CONTENT_TYPE,
			"application/json" + "; charset=UTF-8").build();
    }

}
