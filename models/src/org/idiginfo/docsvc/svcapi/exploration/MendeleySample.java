package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.idiginfo.docsvc.svcapi.mendeley.MendeleyRecord;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class MendeleySample {

    static MendeleyService service = new MendeleyService();
    final static String FILE_DIR = "c:/dev/harvest/mendeleyTitle/";
    final static String FILE_NAME = FILE_DIR
	    + "suicide_295731b0-d2fb-11e1-bce2-0024e8453de6.json";

    private static void run() throws FileNotFoundException {
	testMendeleyDocument(FILE_NAME);
	// testMendeleyQuery();
    }

    public static String testMendeleyDocument(String fileName)
	    throws FileNotFoundException {
	FileReader in = new FileReader(fileName);
	Gson gson = MendeleyService.getGson();
	JsonElement tree = gson.toJsonTree(in);
	MendeleyRecord document = gson.fromJson(in, MendeleyRecord.class);
	// Document document = service.getDocument(params);
	System.out.println(document.getId());
	String string = gson.toJson(document);
	System.out.println(string);

	return null;
    }

    public static String testMendeleyQuery() {
	// String content;
	// MendeleyUrl url = new MendeleyUrl("metadata", "json");
	// url.view="META_ABS";
	// ApiParams params = new MendeleyApiParams();
	// params.setKeyword("suicide");
	//
	// Documents springerResult = service.getDocuments(params);
	// Document record = springerResult.get(0);
	// System.out.println("Id is: " + record.getId());
	// System.out.println("Title is: " + record.getTitle());
	// System.out.println("Pub name is: " + record.getName());
	// //
	// System.out.println("num results "+springerResult.result.get(0).total);
	// System.out.println("num results " + springerResult.size());
	return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
	run();
	return;
    }
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
