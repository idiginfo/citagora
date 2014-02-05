package org.idiginfo.docsvc.svcapi.exploration;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.nature.NatureApiParams;
import org.idiginfo.docsvc.svcapi.nature.NatureService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Class to acquire Nature content
 * 
 * @author griccardi
 * 
 */

public class NatureSample {

	static NatureService service = new NatureService();

	private static void run() {
		testNatureDocument();
		// testNatureQuery();
	}

	public static String testNatureDocument() {
		ApiParams params = new NatureApiParams();
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		// params.setId( "10.1007/s00259-011-1959-x");
		// "10.1023/A:1009661728366");
		params.setSearchTerms("suicide");
		// params.setDoi("doi:10.1136/bmj.c6801");
		List<? extends Document> documents = service.getDocuments(params);
		// Document document = service.getDocument(params);
		Document document = documents.get(0);
		System.out.println(document.getId());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String string = gson.toJson(document);
		System.out.println(string);

		return null;
	}

	public static String testNatureQuery() {
		// String content;
		// NatureUrl url = new NatureUrl("metadata", "json");
		// url.view="META_ABS";
		ApiParams params = new NatureApiParams();
		params.setKeyword("suicide");

		List<? extends Document> natureResult = service.getDocuments(params);
		Document record = natureResult.get(0);
		System.out.println("Id is: " + record.getId());
		System.out.println("Title is: " + record.getTitle());
		System.out.println("Pub name is: " + record.getName());
		// System.out.println("num results "+natureResult.result.get(0).total);
		System.out.println("num results " + natureResult.size());
		return null;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
