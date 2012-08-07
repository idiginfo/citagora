package org.idiginfo.resttest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.idiginfo.annotate.webapp.RequestProcessor;
import org.idiginfo.annotate.webapp.ServiceParams;
import org.idiginfo.rest.RestParams;

/**
 * Hello world!
 * 
 */

public class TestRest {

	RequestProcessor requestProcessor = new RequestProcessor();

	String get(String collection) {
		Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
		List<String> strings;
		strings = new Vector<String>();
		strings.add(ServiceParams.METHOD_GET_DOCUMENT);
		queryParams.put(RestParams.METHOD_PARAM, strings);

		strings = new Vector<String>();
		strings.add("TZpwu9je");
		queryParams.put(RestParams.CODE_PARAM, strings);

		strings = new Vector<String>();
		strings.add("2012-06-29");
		queryParams.put(RestParams.DATA_PARAM, strings);

		RestParams params = new RestParams(queryParams);
		params.setCollection(collection);

		RequestProcessor.Result result = requestProcessor
				.processRequest(params);
		return result.body;
	}

	static public void main(String[] args) {
		String body = new TestRest().get(ServiceParams.COLLECTION_ANNOTATE);
		System.out.println(body);
	}
}
