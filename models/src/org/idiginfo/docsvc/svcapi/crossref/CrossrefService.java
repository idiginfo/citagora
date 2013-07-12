package org.idiginfo.docsvc.svcapi.crossref;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.apisvc.Users;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseResult;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Class to implement DocService for Crossref Service
 * 
 * @author griccardi
 * 
 */

public class CrossrefService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Gson gson = getGson();

	public CrossrefService() {
		SvcApiLogger.enableLogging();
	}

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

	public CrossrefResult getSpringerResult(String function, ApiParams params) {
		JsonElement content = queryService(function, params);
		CrossrefResult result = gson.fromJson(content, CrossrefResult.class);
		return result;
	}

	private JsonElement queryService(String function, ApiParams params) {
		try {
			// TODO add other functions
			CrossrefUrl url = new CrossrefUrl("metadata", "json", function,
					params);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			if (CrossrefUrl.isError(json)) {
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

	public static Gson getGson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDocument(String doi) {
		// TODO Auto-generated method stub
		return null;
	}

	public CrossrefResult getResponse(CrossrefApiParams params) {
		JsonElement content = queryService("getDocuments", params);
		CrossrefResult result = gson.fromJson(content, CrossrefResult.class);
		if (result == null)
			return null;
		return result;
	}

	public JsonElement matchService(List<String> refs) {
		JsonArray jsonContent = new JsonArray();
		for (String ref : refs) {
			jsonContent.add(new JsonPrimitive(ref));
		}
		return matchService(jsonContent);
	}

	public JsonElement matchService(JsonArray jsonContent) {
		// create json array from references
		// post to /links
		// TODO Auto-generated method stub
		String strContent = gson.toJson(jsonContent);
		ByteArrayContent content = new ByteArrayContent("application/json",
				strContent.getBytes());
		CrossrefUrl url = new CrossrefUrl("links", "json");
		url.prepare();
		System.out.println(url.build());

		try {
			HttpRequest request = requestFactory.buildPostRequest(url, content);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public CrossrefMatch getMatch(JsonArray jsonContent) {
		JsonElement result = matchService(jsonContent);
		System.out.println("Match result: " + gson.toJson(result));
		CrossrefMatch match = gson.fromJson(result, CrossrefMatch.class);
		return match;
	}

	public CrossrefMatch getMatch(String[] references) {
		JsonArray jsonContent = new JsonArray();
		for (String ref : references) {
			jsonContent.add(new JsonPrimitive(ref));
		}
		return getMatch(jsonContent);
	}
}
