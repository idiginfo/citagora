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
import org.idiginfo.annotate.webapp.ServiceParams;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestRequestProcessor {
	private static final long serialVersionUID = 1L;

	RequestProcessor requestProcessor = new RequestProcessor();

	public TestRequestProcessor() {
	}

	public String run(ServiceParams params) throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		requestProcessor.processRequest(params, out);
		return null;
	}

	public static void main(String[] args) {
		TestRequestProcessor tester = new TestRequestProcessor();
		try {
			ServiceParams params = new ServiceParams();
			params.setMethod(ServiceParams.METHOD_GET_USERS);
			//tester.run(params);
			params.setMethod(ServiceParams.METHOD_GET_DOCUMENT);
			params.setCode("TZpwu9je");
			params.setDate("2012-06-29");
			tester.run(params);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
