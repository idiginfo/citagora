package org.idiginfo.docsvc.model.exploration;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.scopus.ScopusDocument;
import org.idiginfo.docsvc.model.scopus.ScopusUrl;

import com.google.api.client.http.HttpHeaders;
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

public class ScopusSample {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;

	@SuppressWarnings("unused")
	private static void run() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});
		String test;
		test = testScopus();
		// test = testScopusSearch();
	}

	public static String testScopus() {
		String content;
		try {
			// AnnotateApiParams params = new AnnotateApiParams();
			ScopusUrl url = new ScopusUrl("");
			url.setSearch("suicide");
			// "article/DOI:10.1016/j.jpsychires.2008.05.001");
			// url.view = "META_ABS";
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			// HttpHeaders httpHeaders = result.getHeaders();
			content = IOUtils.toString(result.getContent());
			System.out.print(content);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			System.out.println(gson.toJson(tree));

			ScopusDocument data = gson.fromJson(content, ScopusDocument.class);
			System.out.println("Id is: " + data.getId());
			System.out.println("Title is: " + data.getTitle());
			System.out.println("Pub name is: " + data.getPubName());

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (ScopusUrl.isError(content)) {
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
