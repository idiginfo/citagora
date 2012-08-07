package org.idiginfo.annotate.webapp;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

public class RequestProcessor {

	private AnnotationService service = new AnnotateService();

	public class Result {
		public int statusCode;
		public String message;
		public String out;
	}

	public List<Object> getObjects(ServiceParams params){
		return null;
	}
	public Result processRequest(ServiceParams params) {
		Result result = new Result();
		StringBuffer title = new StringBuffer();
		StringBuffer body = new StringBuffer();
		title.append("MSRC A.nnotate ");
		if (params.method == null) {
			result.statusCode = HttpServletResponse.SC_NOT_FOUND;
			result.message = "'method' parameter must be supplied";
			return result;
		}
		AnnotateApiParams apiParams = params.getApiServiceParams();
		if (params.method.equals(ServiceParams.METHOD_GET_USERS)) {
			Users users = service.getUsers(apiParams);
			title.append("users");
			body.append(ResponseFormatter.format(users));
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = service.getDocuments(params.apiAnnotateUser);
			title.append("documents for user").append(params.apiAnnotateUser);
			body.append(ResponseFormatter.format(documents));
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENT)) {
			Document document = service.getDocument(apiParams);
			title.append("document").append(params.code);
			body.append(ResponseFormatter.format(document));
		} else if (params.method.equals(ServiceParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = service.getAnnotations(params.code,
					params.date);
			title.append("notes for document").append(params.code);
			body.append(ResponseFormatter.formatAnnotations(documentNotes));
		} else {
			result.statusCode = HttpServletResponse.SC_NOT_FOUND;
			result.message = "'method' parameter value '" + params.method
					+ "' not allowed";
			return result;
		}
		StringBuffer out = new StringBuffer("<html><body><title>");
		out.append(title.toString());
		out.append("</title><body>");
		out.append("<h2>").append(title.toString()).append("</h2>");
		out.append(body.toString());
		out.append("</body></html>");
		result.out = out.toString();
		return result;
	}

}
