package org.idiginfo.annotate.webapp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

public class RequestProcessor {

	private AnnotationService service = new AnnotateService();
	private int statusCode = 200;
	private String statusMessage = null;

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServiceParams params = new ServiceParams(request);
		PrintWriter out = response.getWriter();
		int statusCode = processRequest(params, out);
		if (statusCode != HttpServletResponse.SC_OK || statusCode != 0) {
			response.sendError(statusCode, statusMessage);
		}

	}

	public int processRequest(ServiceParams params, PrintWriter out)
			throws IOException {
		StringBuffer title = new StringBuffer();
		StringBuffer body = new StringBuffer();
		title.append("MSRC A.nnotate ");
		if (params.method == null) {
			statusCode = HttpServletResponse.SC_NOT_FOUND;
			statusMessage = "'method' parameter must be supplied";
			return statusCode;
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
			statusCode = HttpServletResponse.SC_NOT_FOUND;
			statusMessage = "'method' parameter value '" + params.method
					+ "' not allowed";
			return statusCode;
		}
		out.println("<html><body><title>");
		out.println(title.toString());
		out.println("</title><body>");
		out.println("<h2>" + title.toString() + "</h2>");
		out.print(body.toString());
		out.println("</body></html>");
		out.close();
		return HttpServletResponse.SC_OK;
	}

}
