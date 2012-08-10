package org.idiginfo.docservices.webapp;

import javax.servlet.http.HttpServletResponse;

import org.idiginfo.docservices.AnnotationFactory;
import org.idiginfo.docservices.model.AnnotationService;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.model.Documents;
import org.idiginfo.docservices.model.Users;

public class RequestProcessor {

	public RequestProcessor() {
	}

	public class Result {
		Result() {
		}

		Result(int statusCode, String body) {
			this.statusCode = statusCode;
			this.body = body;
		}

		public int statusCode;
		public String body;
	}

	public Object getObjects(ServiceParams params) {
		String collection = params.getCollection();
		if (collection == null) {
			return new Result(HttpServletResponse.SC_BAD_REQUEST,
					"collection must be specified");
		}
		AnnotationService service = AnnotationFactory
				.getSharedService(collection);
		if (service == null) {
			return new Result(HttpServletResponse.SC_BAD_REQUEST, "collection "
					+ collection + " is unknown");
		}
		return getObjects(service, params);
	}

	public Object getObjects(AnnotationService service, ServiceParams params) {
		Result result = new Result();
		if (params.method == null) {
			result.statusCode = HttpServletResponse.SC_NOT_FOUND;
			result.body = "'method' parameter must be supplied";
			return result;
		}
		ApiParams apiParams = params.getApiServiceParams();
		if (params.method.equals(ServiceParams.METHOD_GET_USERS)) {
			Users users = service.getUsers(apiParams);
			return users;
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = service.getDocuments(apiParams);
			return documents;
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENT)) {
			Document document = service.getDocument(apiParams);
			return document;
		} else if (params.method.equals(ServiceParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = service.getAnnotations(apiParams);
			return documentNotes;
		} else {
			result.statusCode = HttpServletResponse.SC_NOT_FOUND;
			result.body = "'method' parameter value '" + params.method
					+ "' not allowed";
			return result;
		}
	}

	public Result processRequest(ServiceParams params) {
		Object objects = getObjects(params);
		String body = null;
		body = ResponseFormatter.toHtml(params, objects);
		if (body == null)
			return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"no body returned");
		return new Result(HttpServletResponse.SC_OK, body);
	}

}
