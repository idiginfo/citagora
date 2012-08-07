package org.idiginfo.annotate.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;

import org.idiginfo.annotate.webapp.RequestProcessor;
import org.idiginfo.annotate.webapp.ServiceParams;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestRequestProcessor {
	static final long serialVersionUID = 1L;

	RequestProcessor requestProcessor = new RequestProcessor();

	public TestRequestProcessor() {
	}

	public String run(ServiceParams params) throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		RequestProcessor.Result result = requestProcessor.processRequest(params);
		out.println(result.out);
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
