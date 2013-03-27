package org.idiginfo.docsvc.controller.resttest;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.idiginfo.docsvc.controller.request.DocServicesParams;
import org.idiginfo.docsvc.controller.request.RequestProcessor;
import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.ApiParams;

/**
 * Hello world!
 * 
 */

public class TestRest {

    //static final String FILE_NAME = "c:/dev/api samples/citagoratest.json";
    // static final String FILE_NAME = "c:/dev/api samples/altmetrictest.json";
    // static final String FILE_NAME = "c:/dev/api samples/springertest.json";
    // static final String FILE_NAME = "c:/dev/api samples/annotate.rdf";
     static final String FILE_NAME = "c:/dev/api samples/springer2.rdf";
    // static final String FILE_NAME = "c:/dev/api samples/springer.html";
    RequestProcessor requestProcessor = new RequestProcessor();

    String get(String collection) {
	if (ServiceFactory.COLLECTION_SPRINGER.equals(collection)) {
	    return getSpringer();
	}
	if (ServiceFactory.COLLECTION_ANNOTATE.equals(collection)) {
	    return getAnnotate();
	}
	if (ServiceFactory.COLLECTION_ALTMETRIC.equals(collection)) {
	    return getAltmetric();
	}
	if (ServiceFactory.COLLECTION_ELSEVIER.equals(collection)) {
	    return getSciVerse();
	}
	if (ServiceFactory.COLLECTION_CITAGORA.equals(collection)) {
	    return getCitagora();
	}
	return null;
    }

    private String getSciVerse() {
	// TODO Auto-generated method stub
	return null;
    }

    private String getCitagora() {
	Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
	List<String> strings;
	strings = new Vector<String>();
	strings.add(DocServicesParams.METHOD_GET_CITAGORA);
	queryParams.put(DocServicesParams.METHOD_PARAM, strings);

	strings = new Vector<String>();
	 strings.add("10.1007/s13238-011-1102-6");
	 queryParams.put("doi", strings);
//	strings.add("http://citagora.org/container/1");
//	queryParams.put("id", strings);

	ApiParams params = DocServicesParams.getApiServiceParams(
		ServiceFactory.COLLECTION_CITAGORA, queryParams);
	params.setCollection(ServiceFactory.COLLECTION_CITAGORA);
	params.setFormat(DocServicesParams.FORMAT_RDF);

	RequestProcessor.Result result = requestProcessor
		.processRequest(params);
	return result.body;
    }

    String getAnnotate() {
	Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
	List<String> strings;
	strings = new Vector<String>();
	strings.add(DocServicesParams.METHOD_GET_DOCUMENT);
	queryParams.put(DocServicesParams.METHOD_PARAM, strings);

	strings = new Vector<String>();
	strings.add("sJZefHkg6");
	queryParams.put(DocServicesParams.ID_PARAM, strings);

	strings = new Vector<String>();
	strings.add("2012-03-27");
	queryParams.put(DocServicesParams.DATE_PARAM, strings);

	ApiParams params = DocServicesParams.getApiServiceParams(
		ServiceFactory.COLLECTION_ANNOTATE, queryParams);
	params.setCollection(ServiceFactory.COLLECTION_ANNOTATE);
	params.setFormat(DocServicesParams.FORMAT_HTML);

	RequestProcessor.Result result = requestProcessor
		.processRequest(params);
	return result.body;
    }

    String getSpringerDoc() {
	Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
	List<String> strings;
	strings = new Vector<String>();
	strings.add(DocServicesParams.METHOD_GET_DOCUMENT);
	queryParams.put(DocServicesParams.METHOD_PARAM, strings);

	strings = new Vector<String>();
	strings.add("10.1007/s11276-008-0131-4");
	queryParams.put(DocServicesParams.ID_PARAM, strings);

	ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
	params.setCollection(ServiceFactory.COLLECTION_SPRINGER);

	RequestProcessor.Result result = requestProcessor
		.processRequest(params);
	return result.body;
    }

    String getSpringer() {
	Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
	List<String> strings;
	strings = new Vector<String>();
	strings.add(DocServicesParams.METHOD_GET_DOCUMENTS);
	queryParams.put(DocServicesParams.METHOD_PARAM, strings);
	strings = new Vector<String>();
	strings.add("suicide");
	queryParams.put("keyword", strings);

	ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
	params.setCollection(ServiceFactory.COLLECTION_SPRINGER);
	params.setFormat(DocServicesParams.FORMAT_JSON);

	RequestProcessor.Result result = requestProcessor
		.processRequest(params);
	return result.body;
    }

    String getAltmetric() {
	Map<String, List<String>> queryParams = new HashMap<String, List<String>>();
	List<String> strings;
	strings = new Vector<String>();
	strings.add(DocServicesParams.METHOD_GET_DOCUMENT);
	queryParams.put(DocServicesParams.METHOD_PARAM, strings);
	strings = new Vector<String>();
	strings.add("10.1038/news.2011.490");
	queryParams.put("doi", strings);
	// testDoiDocument("10.1038/news.2011.490");
	// testPmidDocument("21148220");

	ApiParams params = DocServicesParams.getApiServiceParams(queryParams);
	params.setCollection(ServiceFactory.COLLECTION_ALTMETRIC);
	params.setFormat(DocServicesParams.FORMAT_JSON);

	RequestProcessor.Result result = requestProcessor
		.processRequest(params);
	return result.body;
    }

    static public void main(String[] args) throws Exception {
	 String body = new TestRest().get(ServiceFactory.COLLECTION_SPRINGER);
	//String body = new TestRest().get(ServiceFactory.COLLECTION_CITAGORA);
	// String body = new
	// TestRest().get(ServiceFactory.COLLECTION_ALTMETRIC);
	// String body = new TestRest().get(ServiceFactory.COLLECTION_ANNOTATE);
	FileWriter out = new FileWriter(FILE_NAME);
	out.write(body);
	out.close();
	System.out.println(body);
    }
}
