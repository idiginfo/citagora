package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.entrez.EntrezApiParams;
import org.idiginfo.docsvc.svcapi.entrez.EntrezDocuments;
import org.idiginfo.docsvc.svcapi.entrez.EntrezSearchResponse;
import org.idiginfo.docsvc.svcapi.entrez.EntrezService;
import org.idiginfo.docsvc.svcapi.entrez.EntrezUrl;

/**
 * Class to acquire Entrez (NCBI) content
 * 
 * @author griccardi
 * 
 */

public class EntrezSample {

	static EntrezService service = new EntrezService();

	private static void run() throws FileNotFoundException {
		testEntrezFile(EntrezUrl.FETCH, "c:/dev/api samples/medsamp2010.xml");
		testEntrezFile(EntrezUrl.SEARCH,
				"c:/dev/api samples/medline_esearch.xml");
		// testEntrezDocument();
		testEntrezQuery("suicide");
	}

	public static String testEntrezFile(String function, String fileName)
			throws FileNotFoundException {
		Reader reader = new FileReader(fileName);
		if (function.equals(EntrezUrl.FETCH)) {
			EntrezDocuments documents = service.getDocuments(reader);
			System.out.println("Number of documents " + documents.size());
			Document document = documents.get(0);
			System.out.println("title " + document.getTitle());
			System.out.println("authors " + document.getAuthors());
		} else {
			EntrezSearchResponse searchResponse = service
					.getSearchReponse(reader);
			System.out.println("Number of Ids: " + searchResponse.getCount());
			System.out.println("first id: "
					+ searchResponse.getIdList().get(0).getvalue());
		}
		return null;
	}

	public static String testEntrezDocument() {
		String[] idVals = { "" };
		List<String> ids = Arrays.asList(idVals);
		// params.setDoi("http://dx.doi.org/10.1136/bmj.c6801");
		List<Document> documents = service.getDocuments(ids);
		Document document = documents.get(0);
		System.out.println(document.getId());
		return null;
	}

	public static String testEntrezQuery(String searchTerms) {
		EntrezApiParams params = new EntrezApiParams();
		params.setKeyword(searchTerms);

		EntrezSearchResponse searchResponse = service.getSearchReponse(params);
		System.out.println("Number of Ids: " + searchResponse.getCount());
		System.out.println("first id: "
				+ searchResponse.getIdList().get(0).getvalue());
		List<Document> documents = service.getDocumentsIdList(searchResponse);
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
