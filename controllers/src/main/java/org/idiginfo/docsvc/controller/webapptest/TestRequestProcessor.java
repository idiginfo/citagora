package org.idiginfo.docsvc.controller.webapptest;

import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docsvc.controller.request.DocServicesParams;
import org.idiginfo.docsvc.controller.request.RequestProcessor;
import org.idiginfo.docsvc.model.ServiceFactory;

/**
 * Test request processing from various content sources
 * 
 * @author griccardi
 */

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
			params.setCollection(ServiceFactory.COLLECTION_CITAGORA);
			// params.setMethod(DocServicesParams.METHOD_GET_USERS);
			// tester.run(params);
			params.setMethod(DocServicesParams.METHOD_GET_DOCUMENT);
			// params.setId("TZpwu9je");
			// params.setId("http://citagora.org/container/1");
			// params.setDate("2012-06-29");
			// params.setFormat(DocServicesParams.FORMAT_RDF);
			params.setId("doi:10.1177_000312240807300603");
			params.setDate("2013-04-05");
			params.setFormat(DocServicesParams.FORMAT_JSON);
			tester.run(params);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
