package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.svcapi.msrc.MsrcResult;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * Class to acquire MRSC content
 * 
 * @author griccardi
 * 
 */

public class MsrcSample {

	static final String collection = ServiceFactory.COLLECTION_MSRC;
	static ServiceFactory serviceFactory = ServiceFactory.getFactory();

	static DocService service = serviceFactory.createService(collection);
	static JsonParser parser = new JsonParser();
	static Gson gson = service.getGson();

	// http://api.Msrc.com/v1/citations/1d

	private void run() {
		// testMsrcDocument("8792");
		testMsrcFile("c:/dev/api samples/msrc8792.json");

		// testDoiDocument("10.1038/news.2011.490");
		// testPmidDocument("21148220");
		// testMsrcQuery();
	}

	@SuppressWarnings("unused")
	public String testMsrcFile(String filename) {
		FileReader in;
		MsrcResult response = null;
		try {
			in = new FileReader(filename);
			// JsonElement json = parser.parse(in);
			// response = gson.fromJson(json, MsrcResult.class);
			response = gson.fromJson(in, MsrcResult.class);

			// printResponse(response);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";
	}

	public String testPmidDocument(String pmid) {
		return null;
	}

	void printDocumentInfo(MsrcResult document) {
		// System.out.println("Id is: " + document.getId());
		// System.out.println("Title is: " + document.getTitle());
		// System.out.println("Pub name is: " + document.getName());
		// System.out.println("DOI is: " + document.getDoi());
		// System.out.println("PMID is: " + document.getNlmid());
		// System.out.println("Msrc is: " + document.getMsrcId());
	}

	public String testMsrcQuery() {
		// String content;
		ApiParams params = serviceFactory.createApiParams(collection);
		params.setKeyword("suicide");
		// MsrcResult record = service.getResponse(params);
		// if (record == null) {
		// System.err.println("Service request failed");
		// return null;
		// }
		// printResponse(record);
		return null;
	}

	public static void main(String[] args) {
		MsrcSample sample = new MsrcSample();
		sample.run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
