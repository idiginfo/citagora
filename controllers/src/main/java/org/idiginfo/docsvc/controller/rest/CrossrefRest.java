package org.idiginfo.docsvc.controller.rest;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

//import javax.servlet.*;

/**
 * Class to support CrossRef Rest service
 * 
 * @author griccardi
 * 
 */

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class CrossrefRest {

	CrossrefService service = new CrossrefService();
	Gson gson = CrossrefService.getGson();
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Path(value = "/match")
	public Response getCrossref(@QueryParam("ref") List<String> refs) {
		JsonElement matches = service.matchService(refs);
		return createResponse(matches);
	}

	@POST
	@Path(value = "/match")
	public Response getCrossrefPost(@FormParam("refs") String refs) {
		JsonElement matches = null;
		JsonParser parser = new JsonParser();
		JsonElement json = parser.parse(refs);
		if (json instanceof JsonArray) {
			JsonArray refsArray = (JsonArray) json;
			matches = service.matchService(refsArray);
		} else {
			// not json array, try strings
			List<String> refStrings = Arrays.asList(StringUtils.split(refs,
					'\n'));
			matches = service.matchService(refStrings);
		}
		return createResponse(matches);
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
