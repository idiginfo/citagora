package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.naming.spi.DirStateFactory.Result;

import org.idiginfo.docsvc.model.crossref.CrossrefApiParams;
import org.idiginfo.docsvc.model.crossref.CrossrefDocument;
import org.idiginfo.docsvc.model.crossref.CrossrefMatch;
import org.idiginfo.docsvc.model.crossref.CrossrefResult;
import org.idiginfo.docsvc.model.crossref.CrossrefService;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class CrossrefSample {

    static CrossrefService service = new CrossrefService();
    static JsonParser parser = new JsonParser();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // http://api.Crossref.com/v1/citations/1d

    private static void run() {
	// testCrossrefDocument("241939");
	testCrossrefFile("c:/dev/api samples/crossref.json");
	testCrossrefMatchFile("c:/dev/api samples/crossrefMatch.json");

	// testDoiDocument("10.1038/news.2011.490");
	// testPmidDocument("21148220");
	testCrossrefQuery();
    }

    public static String testDoiDocument(String doi) {
	String result = service.getDocument(doi);
	System.out.println(result);
	return result;
    }

    @SuppressWarnings("unused")
    public static String testCrossrefFile(String filename) {
	FileReader in;
	try {
	    in = new FileReader(filename);
	    JsonElement json = parser.parse(in);
	    int resultCode = CrossrefResult.getResultCode(json);
	    System.out.println("result message from json: "
		    + CrossrefResult.getMessage(json));
	    CrossrefResult response = gson.fromJson(json, CrossrefResult.class);
	    printResponse(response);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return "";
    }

    @SuppressWarnings("unused")
    public static String testCrossrefMatchFile(String filename) {
	FileReader in;
	try {
	    in = new FileReader(filename);
	    JsonElement json = parser.parse(in);
	    int resultCode = CrossrefResult.getResultCode(json);
	    System.out.println("result message from json: "
		    + CrossrefResult.getMessage(json));
	    CrossrefMatch response = gson.fromJson(json, CrossrefMatch.class);
	    CrossrefMatch.Result result = response.getResults()[0];
	    printResponse(response);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	return "";
    }

    private static void printResponse(CrossrefMatch response) {
	System.out.println(response.getResults()[0].getReason());
	
	// TODO Auto-generated method stub
	
    }

    private static void printResponse(CrossrefResult response) {
	// CrossrefResponseObject object = response.getResultObject();
	System.out.println("numresults is: " + response.getTotalResults());
    }

    public static String testPmidDocument(String pmid) {
	return null;
    }

    static void printDocumentInfo(CrossrefDocument document) {
	// System.out.println("Id is: " + document.getId());
	// System.out.println("Title is: " + document.getTitle());
	// System.out.println("Pub name is: " + document.getName());
	// System.out.println("DOI is: " + document.getDoi());
	// System.out.println("PMID is: " + document.getNlmid());
	// System.out.println("Crossref is: " + document.getCrossrefId());
    }

    public static String testCrossrefQuery() {
	// String content;
	CrossrefApiParams params = new org.idiginfo.docsvc.model.crossref.CrossrefApiParams();
	params.setKeyword("suicide");
	CrossrefResult record = service.getResponse(params);
	if (record == null) {
	    System.err.println("Service request failed");
	    return null;
	}
	printResponse(record);
	return null;
    }

    public static void main(String[] args) {
	run();
	return;
    }
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu