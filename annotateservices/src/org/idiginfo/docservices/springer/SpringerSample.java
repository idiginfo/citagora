package org.idiginfo.docservices.springer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.model.Documents;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SpringerSample {

	static SpringerService service = new SpringerService();

	private static void run() {
		testSpringerDocument();
		testSpringerQuery();
	}

	public static String testSpringerDocument() {
		ApiParams params = new SpringerApiParams();
		//params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId("doi:10.1007/s11276-008-0131-4");
		Document document = service.getDocument(params);
		// System.out.print(content);
		return null;
	}

	public static String testSpringerQuery() {
		String content;
		SpringerUrl url = new SpringerUrl("metadata", "json");
		// url.view="META_ABS";
		ApiParams params = new SpringerApiParams();
		params.setKeyword("suicide");

		Documents springerResult = service.getDocuments(params);
		Document record = springerResult.get(0);
		System.out.println("Id is: " + record.getId());
		System.out.println("Title is: " + record.getTitle());
		System.out.println("Pub name is: " + record.getName());
		// System.out.println("num results "+springerResult.result.get(0).total);
		System.out.println("num results " + springerResult.size());
		return null;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
