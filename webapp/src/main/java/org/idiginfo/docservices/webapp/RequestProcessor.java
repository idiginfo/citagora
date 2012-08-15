package org.idiginfo.docservices.webapp;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.idiginfo.docservices.ServiceFactory;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.model.DocService;
import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.model.Documents;
import org.idiginfo.docservices.model.Users;
import org.idiginfo.docservices.utilities.HtmlDocumentWriter;

public class RequestProcessor {

	public RequestProcessor() {
	}

	public class Result {
		Result() {
		}

		Result(Status statusCode, String body) {
			this(statusCode, body, MediaType.TEXT_HTML);
		}

		Result(Status statusCode, String body, String mimeType) {
			this.statusCode = statusCode;
			this.body = body;
			this.mimeType = mimeType;
		}

		public Status statusCode;
		public String body;
		public String mimeType;
	}

	public Object getObjects(ApiParams params) {
		String collection = params.getCollection();
		if (collection == null) {
			return new Result(Status.BAD_REQUEST,
					"collection must be specified");
		}
		DocService service = ServiceFactory.getSharedService(collection);
		if (service == null) {
			return new Result(Status.BAD_REQUEST, "collection "
					+ collection + " is unknown");
		}
		return getObjects(service, params);
	}

	public Object getObjects(DocService service, ApiParams params) {
		if (params == null)
			return null;
		Result result = new Result();
		if (params.getMethod() == null) {
			result.statusCode = Status.NOT_FOUND;
			result.body = "'method' parameter must be supplied";
			return result;
		}
		// ApiParams apiParams = params.getApiServiceParams();
		if (params.getMethod().equals(DocServicesParams.METHOD_GET_USERS)) {
			Users users = service.getUsers(params);
			return users;
		} else if (params.getMethod().equals(
				DocServicesParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = service.getDocuments(params);
			return documents;
		} else if (params.getMethod().equals(
				DocServicesParams.METHOD_GET_DOCUMENT)) {
			Document document = service.getDocument(params);
			return document;
		} else if (params.getMethod().equals(
				DocServicesParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = service.getAnnotations(params);
			return documentNotes;
		} else {
			result.statusCode = Status.NOT_FOUND;
			result.body = "'method' parameter value '" + params.getMethod()
					+ "' not allowed";
			return result;
		}
	}

	public Result processRequest(ApiParams params) {
		Object objects = getObjects(params);
		String body = null;
		String format = params.getFormat();
		if (DocServicesParams.FORMAT_JSON.equals(format)) {

		} else if (DocServicesParams.FORMAT_XLS.equals(format)) {

		} else if (DocServicesParams.FORMAT_RDF.equals(format)) {

		} else {
			body = HtmlDocumentWriter.toHtml(params, objects);
			if (body == null)
				return new Result(Status.INTERNAL_SERVER_ERROR,
						"no body returned");
			return new Result(Status.OK, body);
		}
		return null;
	}

}
