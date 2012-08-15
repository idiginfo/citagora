package org.idiginfo.docservices.webapptest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.idiginfo.docservices.webapp.RequestProcessor;
import org.idiginfo.docservices.webapp.DocServicesParams;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestProcessor requestProcessor = new RequestProcessor();

	public TestServlet() {
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DocServicesParams params = new DocServicesParams(request);
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		if (result.statusCode != Response.Status.OK
				&& result.statusCode.getStatusCode() != 0) {
			response.sendError(result.statusCode.getStatusCode(), result.body);
		}
	}
}
