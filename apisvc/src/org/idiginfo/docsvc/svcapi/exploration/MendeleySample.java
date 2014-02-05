package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiSvcUtilities;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyApiParams;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyRecord;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyUrl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

/**
 * Class to acquire Mendeley content
 * 
 * @author griccardi
 * 
 */

public class MendeleySample {

	static DocService service = new MendeleyService();
	final static String FILE_DIR = "c:/dev/harvest/mendeleyTitle/";
	final static String FILE_NAME = FILE_DIR
			+ "suicide_295731b0-d2fb-11e1-bce2-0024e8453de6.json";

	private static void run() throws FileNotFoundException {
		SvcApiLogger.enableLogging();
		// testMendeleyDocument(FILE_NAME);
		testMendeleyQuery();
	}

	public static String testMendeleyDocument(String fileName)
			throws FileNotFoundException {
		FileReader in = new FileReader(fileName);
		Gson gson = service.getGson();
		JsonElement tree = gson.toJsonTree(in);
		MendeleyRecord document = gson.fromJson(in, MendeleyRecord.class);
		// Document document = service.getDocument(params);
		System.out.println(document.getId());
		String string = gson.toJson(document);
		System.out.println(string);

		return null;
	}

	public static String testMendeleyQuery() {
		ApiParams params = new MendeleyApiParams();
		params.setKeyword("suicide");

		List<? extends Document> documents = service.getDocuments(params);
		Document record = documents.get(0);
		System.out.println("Id is: " + record.getId());
		System.out.println("Title is: " + record.getTitle());
		System.out.println("Pub name is: " + record.getName());
		//
		// System.out.println("num results " + documents.get(0));
		System.out.println("num results " + documents.size());
		return null;
	}

	public static void main(String[] args) throws FileNotFoundException {
		run();
		return;
	}
}
