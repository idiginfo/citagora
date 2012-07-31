package org.idiginfo.annotate.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AnnotationService service = null;

	public TestServlet() {
	}

	@Override
	public void init() throws ServletException {
		super.init();
		service = new AnnotateService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ServiceParams params = new ServiceParams(request);
		if (params.method == null) {
			response.sendError(404, "'method' parameter must be supplied");
			return;
		}
		AnnotateApiParams apiParams = params.getApiServiceParams();
		if (params.method.equals(ServiceParams.METHOD_GET_USERS)) {
			Users users = service.getUsers(apiParams);
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = service.getDocuments(params.apiAnnotateUser);
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENT)) {
			Document document = service.getDocument(apiParams);
		} else if (params.method.equals(ServiceParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = service.getAnnotations(params.code,
					params.date);
		} else {
			response.sendError(404, "'method' parameter value '"
					+ params.method + "' not allowed");

		}
	}

}
