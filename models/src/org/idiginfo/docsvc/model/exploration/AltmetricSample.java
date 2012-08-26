package org.idiginfo.docsvc.model.exploration;

import java.util.Arrays;
import java.util.List;

import org.idiginfo.docsvc.model.altmetric.AltmetricApiParams;
import org.idiginfo.docsvc.model.altmetric.AltmetricRecord;
import org.idiginfo.docsvc.model.altmetric.AltmetricService;
import org.idiginfo.docsvc.model.altmetric.AltmetricUrl;
import org.idiginfo.docsvc.model.model.ApiParams;

public class AltmetricSample {

	static AltmetricService service = new AltmetricService();

	// http://api.altmetric.com/v1/citations/1d

	private static void run() {
		// testAltmetricDocument("241939");
		testDoiDocument("10.1038/news.2011.490");
		testPmidDocument("21148220");
		// testAltmetricQuery();
	}

	public static String testAltmetricDocument(String altmetricId) {
		AltmetricRecord document = service.getDetails(altmetricId);
		printDocumentInfo(document);
		return null;
	}

	public static String testDoiDocument(String doi) {
		AltmetricRecord document = service.getDetailsByDoi(doi);
		printDocumentInfo(document);
		return null;
	}

	public static String testPmidDocument(String pmid) {
		AltmetricRecord document = service.getDetailsByPmid(pmid);
		printDocumentInfo(document);
		return null;
	}

	static void printDocumentInfo(AltmetricRecord document) {
		System.out.println("Id is: " + document.getId());
		System.out.println("Title is: " + document.getTitle());
		System.out.println("Pub name is: " + document.getName());
		System.out.println("DOI is: " + document.getDoi());
		System.out.println("PMID is: " + document.getNlmid());
		System.out.println("Altmetric is: " + document.getAltmetricId());
	}

	public static String testAltmetricQuery() {
		// String content;
		AltmetricUrl url = new AltmetricUrl();
		// List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");

		// http://api.altmetric.com/v1/details/241939
		List<String> pathParts = Arrays.asList("", "v1", "details", "241939");

		url.setPathParts(pathParts);
		ApiParams params = new AltmetricApiParams();
		System.out.println(url.build());
		AltmetricRecord record = (AltmetricRecord) service.getDocument(params);
		if (record == null) {
			System.err.println("Service request failed");
			return null;
		}
		printDocumentInfo(record);
		return null;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
