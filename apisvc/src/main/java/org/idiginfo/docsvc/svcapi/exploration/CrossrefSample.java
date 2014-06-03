package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.MatchResult;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefDocument;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefMatch;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefRdfService;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefResult;
import org.idiginfo.docsvc.svcapi.crossref.RdfDocument;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Class to acquire Crossref content
 * 
 * @author griccardi
 * 
 */

public class CrossrefSample {

	static final String collection = ServiceFactory.COLLECTION_CROSSREF;
	static ServiceFactory serviceFactory = ServiceFactory.getFactory();

	static DocService service = serviceFactory.createService(collection);

//	static CrossrefService service = new CrossrefService();
	static CrossrefRdfService rdfService = new CrossrefRdfService();
	static JsonParser parser = new JsonParser();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	// http://api.Crossref.com/v1/citations/1d

	private static void run() {
		// testCrossrefDocument("241939");
		// testCrossrefFile("c:/dev/api samples/crossref.json");
		// testCrossrefMatchFile("c:/dev/api samples/crossrefMatch.json");

//		testDoiDocument("10.1126/science.1157784");
		// testPmidDocument("21148220");
		 testCrossrefQuery();
//		 testCrossrefMatch();
	}

	public static Document testDoiDocument(String doi) {
		RdfDocument result = (RdfDocument) rdfService.getDocument(doi);
//		Model model = result.getRdfModel();
		System.out.println("Printing model");
		// model.write(System.out);
		System.out.println("type: " + result.getType());
		System.out.println("title: " + result.getTitle());
		System.out.println("authors: " + result.getAuthors());
		System.out.println("start page: " + result.getPageStart());
		System.out.println("pages: " + result.getPages());
		System.out.println("journal title: " + result.getPublicationName());
		System.out.println("issn: " + result.getIssn());
		System.out.println("eIssn: " + result.geteIssn());
		System.out.println("date object: " + result.getDateObject());
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
			
//			MatchResult result = response.getResults().get(0);
			printResponse(response.getResults());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return "";
	}

	private static void printResponse(List<? extends MatchResult> response) {
		System.out.println("query returned: " + response.size() + " results");
		for (MatchResult result : response) {
			System.out.println("<match>");
			System.out.println("\tmatch: " + result.getMatch());
			System.out.println("\tdoi: " + result.getDoi());
			System.out.println("\tscore: " + result.getScore());
			System.out.println("\ttext: " + result.getText());
			System.out.println("\treason: " + result.getReason());
			System.out.println("</match>");

		}
	}

	private static void printResponse(CrossrefResult response) {
		// CrossrefResponseObject object = response.getResultObject();
		System.out.println("numresults is: " + response.getTotalResults());
	}
	
	private static void printItems(List<? extends Document> response) {
		// CrossrefResponseObject object = response.getResultObject();
		System.out.println("numresults is: " + response.size());
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
		ApiParams params = serviceFactory.createApiParams(collection);
		params.setKeyword("suicide");
		List<? extends Document> documents  = service.getDocuments(params);
		if (documents == null) {
			System.err.println("Service request failed");
			return null;
		}
		printItems(documents);
		return null;
	}

	public static String testCrossrefMatch() {
		// String content;
		String[] refs = {
				"M. Henrion, D. J. Mortlock, D. J. Hand, and A. Gandy, \"A Bayesian approach to star-galaxy classification,\" Monthly Notices of the Royal Astronomical Society, vol. 412, no. 4, pp. 2286-2302, Apr. 2011.",
				"Renear 2012"};
		List<? extends MatchResult> record = (List<? extends MatchResult>) service.getMatch(refs);
//		CrossrefMatch record = (CrossrefMatch) service.getCrossrefMatch(refs);
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
