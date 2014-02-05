package org.idiginfo.docsvc.svcapi.nature;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class to implement DocService object for Nature
 * 
 * @author griccardi
 * 
 */

public class NatureService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Gson gson = getGson();

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});

	@Override
	public String format(String content) {
		String formattedContent;
		try {
			JsonElement tree = parser.parse(content);
			formattedContent = gson.toJson(tree);
			return formattedContent;
		} catch (JsonParseException e) {
		}
		return null;
	}

	@Override
	public Document getAnnotations(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getAnnotations(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	// private Document getSpringerDocument(ApiParams params) {
	// // TODO must make a SpringerResult and then extract a SpringerRecord
	// JsonElement content = queryService("getdocument", params);
	// printJson(content, "c:/dev/api samples/springer1.json");
	// NatureResult result = gson.fromJson(content, NatureResult.class);
	// Documents documents = result.getDocuments();
	// if (documents == null || documents.size() < 1)
	// return null;
	// return documents.get(0);
	// }
	//
	// public Documents getSpringerDocuments(String function, ApiParams params)
	// {
	// NatureResult result = getSpringerResult(function, params);
	// if (result == null)
	// return null;
	// return result.getDocuments();
	// }

	public NatureResult getSpringerResult(String function, ApiParams params) {
		JsonElement content = queryService(function, params);
		NatureResult result = gson.fromJson(content, NatureResult.class);
		return result;
	}

	private JsonElement queryService(String function, ApiParams params) {
		try {
			// TODO add other functions
			NatureUrl url = new NatureUrl("metadata", "json", function, params);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			if (NatureUrl.isError(json)) {
				System.err.println(json);
				return null;
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void printJson(JsonElement json, String fileName) {
		try {
			FileWriter out = new FileWriter(fileName);
			out.write(gson.toJson(json));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// return service result as formatted json string
	public String getSpringerContents(NatureApiParams params) {
		JsonElement results = queryService("getdocuments", params);
		return gson.toJson(results);
	}

	@Override
	public Gson getGson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(List<String> refs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(JsonArray refsArray) {
		// TODO Auto-generated method stub
		return null;
	}

}
