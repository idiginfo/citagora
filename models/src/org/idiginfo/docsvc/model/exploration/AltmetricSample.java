package org.idiginfo.docsvc.model.exploration;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.altmetric.AltmetricApiParams;
import org.idiginfo.docsvc.model.altmetric.AltmetricService;
import org.idiginfo.docsvc.model.altmetric.AltmetricUrl;
import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;

public class AltmetricSample {

	static AltmetricService service = new AltmetricService();

	// http://api.altmetric.com/v1/citations/1d

	private static void run() {
		// testAltmetricDocument();
		testAltmetricQuery();
	}

	public static String testAltmetricDocument() {
		ApiParams params = new AltmetricApiParams();
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId("doi:10.1007/s11276-008-0131-4");
		Document document = service.getDocument(params);
		// System.out.print(content);
		return null;
	}

	public static String testAltmetricQuery() {
		String content;
		AltmetricUrl url = new AltmetricUrl();
		//List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");

		//http://api.altmetric.com/v1/details/241939
		List<String> pathParts = Arrays.asList("", "v1", "details", "241939");

		url.setPathParts(pathParts);
		ApiParams params = new AltmetricApiParams();
		System.out.println(url.build());
		Documents altmetricResult = service.getDocuments(params);
		if (altmetricResult==null) {
			System.err.println("Service request failed");
			return null;
		}
		Document record = altmetricResult.get(0);
		System.out.println("Id is: " + record.getId());
		System.out.println("Title is: " + record.getTitle());
		System.out.println("Pub name is: " + record.getName());
		// System.out.println("num results "+AltmetricResult.result.get(0).total);
		System.out.println("num results " + altmetricResult.size());
		return null;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
