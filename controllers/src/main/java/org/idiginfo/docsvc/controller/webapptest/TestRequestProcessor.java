package org.idiginfo.docsvc.controller.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docsvc.controller.request.DocServicesParams;
import org.idiginfo.docsvc.controller.request.RequestProcessor;
import org.idiginfo.docsvc.model.ServiceFactory;

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
