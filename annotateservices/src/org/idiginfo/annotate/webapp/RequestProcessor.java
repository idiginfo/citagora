package org.idiginfo.annotate.webapp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;
import org.idiginfo.sciverse.services.SciVerseService;
import org.idiginfo.springer.services.SpringerService;

public class RequestProcessor {

	public RequestProcessor() {
		services = new HashMap<String, AnnotationService>();
		services.put(ServiceParams.COLLECTION_ANNOTATE, new AnnotateService());
		services.put(ServiceParams.COLLECTION_SPRINGER, new SpringerService());
		services.put(ServiceParams.COLLECTION_ELSEVIER, new SciVerseService());
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

	Map<String, AnnotationService> services;

	public Object getObjects(ServiceParams params) {
		String collection = params.getCollection();
		if (collection == null) {
			return new Result(HttpServletResponse.SC_BAD_REQUEST,
					"collection must be specified");
		}
		AnnotationService service = services.get(collection);
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
		AnnotateApiParams apiParams = params.getApiServiceParams();
		if (params.method.equals(ServiceParams.METHOD_GET_USERS)) {
			Users users = service.getUsers(apiParams);
			return users;
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = service.getDocuments(params.apiAnnotateUser);
			return documents;
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENT)) {
			Document document = service.getDocument(apiParams);
			return document;
		} else if (params.method.equals(ServiceParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = service.getAnnotations(params.code,
					params.date);
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
		if (objects instanceof Users) {
			body = ResponseFormatter.toHtml((Users) objects);
		} else if (objects instanceof Documents) {
			body = ResponseFormatter.toHtml((Documents) objects);
		} else if (objects instanceof Document) {
			body = ResponseFormatter.toHtml((Document) objects);
		}
		if (body == null)
			return new Result(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"no body returned");
		return new Result(HttpServletResponse.SC_OK, body);
	}

}
