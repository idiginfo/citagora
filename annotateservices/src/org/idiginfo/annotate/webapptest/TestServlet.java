package org.idiginfo.annotate.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotate.webapp.RequestProcessor;
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
		requestProcessor.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		requestProcessor.processRequest(request, response);
	}

}
