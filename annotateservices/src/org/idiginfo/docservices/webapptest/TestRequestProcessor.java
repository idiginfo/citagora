package org.idiginfo.docservices.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docservices.ServiceFactory;
import org.idiginfo.docservices.webapp.RequestProcessor;
import org.idiginfo.docservices.webapp.DocServicesParams;

public class TestRequestProcessor {
	static final long serialVersionUID = 1L;

	RequestProcessor requestProcessor = new RequestProcessor();

	public TestRequestProcessor() {
	}

	public String run(DocServicesParams params) throws IOException {
		PrintWriter out = new PrintWriter(System.out);
		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		out.println(result.body);
		out.close();
		return null;
	}

	public static void main(String[] args) {
		TestRequestProcessor tester = new TestRequestProcessor();
		try {
			DocServicesParams params = new DocServicesParams();
			params.setCollection(ServiceFactory.COLLECTION_ANNOTATE);
			params.setMethod(DocServicesParams.METHOD_GET_USERS);
			// tester.run(params);
			params.setMethod(DocServicesParams.METHOD_GET_DOCUMENT);
			params.setId("TZpwu9je");
			params.setDate("2012-06-29");
			tester.run(params);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
