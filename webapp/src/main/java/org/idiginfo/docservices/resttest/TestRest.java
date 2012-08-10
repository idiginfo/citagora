package org.idiginfo.docservices.resttest;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.idiginfo.docservices.AnnotationFactory;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.rest.RestParams;
import org.idiginfo.docservices.webapp.DocServicesParams;
import org.idiginfo.docservices.webapp.RequestProcessor;

/**
 * Hello world!
 * 
 */

public class TestRest {

	RequestProcessor requestProcessor = new RequestProcessor();

	String get(String collection) {
		if (AnnotationFactory.COLLECTION_SPRINGER.equals(collection)) {
			return getSpringerQuery();
		}
		if (AnnotationFactory.COLLECTION_ANNOTATE.equals(collection)) {
			return getAnnotate();
		}
		return null;
	}

	String getAnnotate() {
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		List<String> strings;
		strings = new Vector<String>();
		strings.add(DocServicesParams.METHOD_GET_DOCUMENT);
		queryParams.put(RestParams.METHOD_PARAM, strings);

		strings = new Vector<String>();
		strings.add("TZpwu9je");
		queryParams.put(RestParams.ID_PARAM, strings);

		strings = new Vector<String>();
		strings.add("2012-06-29");
		queryParams.put(RestParams.DATE_PARAM, strings);

		ApiParams params = DocServicesParams.getApiServiceParams(AnnotationFactory.COLLECTION_ANNOTATE, queryParams);
		params.setCollection(AnnotationFactory.COLLECTION_ANNOTATE);

		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.body;
	}

	String getSpringerDoc() {
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		List<String> strings;
		strings = new Vector<String>();
		strings.add(DocServicesParams.METHOD_GET_DOCUMENT);
		queryParams.put(RestParams.METHOD_PARAM, strings);

		strings = new Vector<String>();
		strings.add("10.1007/s11276-008-0131-4");
		queryParams.put(RestParams.ID_PARAM, strings);

		ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
		params.setCollection(AnnotationFactory.COLLECTION_SPRINGER);

		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.body;
	}

	String getSpringerQuery() {
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		List<String> strings;
		strings = new Vector<String>();
		strings.add(DocServicesParams.METHOD_GET_DOCUMENTS);
		queryParams.put(RestParams.METHOD_PARAM, strings);
		strings = new Vector<String>();
		strings.add("suicide");
		queryParams.put("keyword", strings);

		ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
		params.setCollection(AnnotationFactory.COLLECTION_SPRINGER);

		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.body;
	}

	static public void main(String[] args) throws Exception {
		String body = new TestRest().get(AnnotationFactory.COLLECTION_SPRINGER);
		FileWriter out = new FileWriter("c:/dev/api samples/springer.html");
		out.write(body);
		out.close();
		System.out.println(body);
	}
}
