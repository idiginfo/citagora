package org.idiginfo.docsvc.svcapi.exploration;

import java.util.Arrays;
import java.util.List;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.svcapi.mas.MasApiParams;
import org.idiginfo.docsvc.svcapi.mas.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.MasService;
import org.idiginfo.docsvc.svcapi.mas.MasUrl;

public class MasSample {

	static MasService service = new MasService();

	// http://api.Mas.com/v1/citations/1d

	private static void run() {
		// testMasDocument("241939");
		testDoiDocument("10.1038/news.2011.490");
		testPmidDocument("21148220");
		// testMasQuery();
	}

	public static String testDoiDocument(String doi) {
		String result = service.getResult(doi);
		System.out.println(result);
		return result;
	}

	public static String testPmidDocument(String pmid) {
		return null;
	}

	static void printDocumentInfo(MasResponse document) {
		// System.out.println("Id is: " + document.getId());
		// System.out.println("Title is: " + document.getTitle());
		// System.out.println("Pub name is: " + document.getName());
		// System.out.println("DOI is: " + document.getDoi());
		// System.out.println("PMID is: " + document.getNlmid());
		// System.out.println("Mas is: " + document.getMasId());
	}

	public static String testMasQuery() {
		// String content;
		MasUrl url = new org.idiginfo.docsvc.svcapi.mas.MasUrl();
		// List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");

		// http://api.Mas.com/v1/details/241939
		List<String> pathParts = Arrays.asList("", "v1", "details", "241939");

		url.setPathParts(pathParts);
		ApiParams params = new MasApiParams();
		System.out.println(url.build());
		MasResponse record = (MasResponse) service.getDocument(params);
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
