package org.idiginfo.annotate.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.webapp.RequestProcessor;
import org.idiginfo.annotate.webapp.ServiceParams;
import org.idiginfo.annotate.webapp.RequestProcessor.Result;

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
		ServiceParams params = new ServiceParams(request);
		PrintWriter out = response.getWriter();
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		if (result.statusCode != HttpServletResponse.SC_OK
				|| result.statusCode != 0) {
			response.sendError(result.statusCode, result.message);
		}
	}
}
