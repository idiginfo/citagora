package org.idiginfo.springer.services;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.sciverse.services.SciVerseDocument;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class SpringerSample {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;

	private static void run() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});
		String test = null;
		// test = testSpringerDocument();
		test = testSpringerQuery();
	}

	public static String testSpringerDocument() {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			SpringerUrl url = new SpringerUrl("metadata", "json");
			// url.view="META_ABS";
			url.addParameter("doi", "10.1007/s11276-008-0131-4");
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
			// System.out.print(content);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			System.out.println(gson.toJson(tree));

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (SpringerUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		return null;
	}

	public static String testSpringerQuery() {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			SpringerUrl url = new SpringerUrl("metadata", "json");
			// url.view="META_ABS";
			url.addParameter("title", "suicide");
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
			// System.out.print(content);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			String json = gson.toJson(tree);
			//System.out.println(json);

			SpringerResult springerResult = gson.fromJson(json,
					SpringerResult.class);
			SpringerRecord record = springerResult.records.get(1);
			System.out.println("Id is: " + record.identifier);
			System.out.println("Title is: " + record.title);
			System.out.println("Pub name is: " + record.publicationName);
			// System.out.println("num results "+springerResult.result.get(0).total);
			System.out.println("num results "
					+ springerResult.getResult().total);
			for (int i = 0; i < springerResult.facets.size(); i++) {
				SpringerResult.Facet facet = springerResult.getFacet(i);
				System.out.println("Facet " + i + " '" + facet.name + "': "
						+ facet.values.get(0).value + ": "
						+ facet.values.get(0).count);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (SpringerUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		return null;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
