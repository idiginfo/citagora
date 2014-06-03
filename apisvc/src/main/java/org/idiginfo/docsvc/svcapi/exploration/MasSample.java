package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.idiginfo.docsvc.svcapi.mas.model.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponseObject;
import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Class to acquire Microsoft Academic Search (MAS) content
 * 
 * @author griccardi
 * 
 */

public class MasSample {

	static MasService service = new MasService();
	static JsonParser parser = new JsonParser();
	static Gson gson = new GsonBuilder().setPrettyPrinting()
			.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

	// http://api.Mas.com/v1/citations/1d

	private static void run() {
		// testMasDocument("241939");
		// testMasFile("c:/dev/api samples/Mas_details.json");

		// testDoiDocument("10.1001/archinte.160.10.1522");
		testDoiDocument("10.1093/nar/25.17.338");
		// testDoiDocument(null,
		// "Suicidal Ideation and Suicide Attempts in General Medical Illnesses");
		// testPmidDocument("21148220");
		// testMasQuery();
	}

	public static String testDoiDocument(String doi) {
		String result = service.getResult(doi, null);
		System.out.println(result);
		return result;
	}

	@SuppressWarnings("unused")
	public static String testMasFile(String filename) {
		FileReader in;
		try {
			in = new FileReader(filename);
			JsonElement json = parser.parse(in);
			int resultCode = MasResponse.getResultCode(json);
			System.out.println("result message from json: "
					+ MasResponse.getMessage(json));
			MasResponse response = gson.fromJson(json, MasResponse.class);
			printResponse(response);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	private static void printResponse(MasResponse response) {
		MasResponseObject object = response.getResultObject();
		System.out.println("result is: "
				+ MasApiParams.RESPONSE_CODE_MESSAGES[object.getResultCode()]);
		System.out.println("number of pubs: "
				+ object.getPublication().result.size());
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
		MasApiParams params = new MasApiParams();
		params.setKeyword("suicide");
		MasResponse record = service.getResponse(params);
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
