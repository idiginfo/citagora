package org.idiginfo.docsvc.svcapi.mendeley;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.apisvc.Users;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeader;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeaders;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyRecord;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyDocuments;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class that implements the Mendeley API service function
 * 
 * @author sflager
 * 
 */

public class MendeleyService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Gson gson = getGson();

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});

	// @Override
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

	// @Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	private MendeleyHeader getMendeleyHeader(ApiParams params) {
		// TODO must make a MendeleyResult and then extract a MendeleyRecord
		String search = "test";
		JsonElement content = queryService("search", search, params);
		printJson(content, "c:/dev/api samples/mendeley1.json");
		MendeleyResult result = gson.fromJson(content, MendeleyResult.class);
		// Documents documents = result.getDocuments();
		// if (documents == null || documents.size() < 1)
		// return null;
		// return documents.get(0);
		// }
		List<MendeleyHeader> headers = result.getHeaders();
		if (headers == null || headers.size() < 1)
			return null;
		return headers.get(0);
	}

	// TODO pass the uuid into URL creator to build "details" action
	public MendeleyRecord getMendeleyRecord(String function, String uuid,
			ApiParams params) {
		JsonElement content = queryService(function, uuid, params);
		MendeleyRecord result = gson.fromJson(content, MendeleyRecord.class);
		return result;
	}

	// TODO pass the search string into URL creator to build "search" action
	public List<MendeleyHeader> getMendeleyHeaders(String function,
			String search, ApiParams params) {
		MendeleyResult result = getMendeleyResult(function, search, params);
		if (result == null)
			return null;
		return result.getHeaders();
	}

	public MendeleyResult getMendeleyResult(String function, String search,
			ApiParams params) {
		JsonElement content = queryService(function, search, params);
		MendeleyResult result = gson.fromJson(content, MendeleyResult.class);
		return result;
	}

	public String getMendeleyUUID(String function, String search,
			ApiParams params) {
		JsonElement content = queryService(function, search, params);
		String contentString = gson.toJson(content);
		return contentString;
	}

	public MendeleyRecord getMendeleyUUIDRecord(String function, String search,
			ApiParams params) {
		JsonElement content = queryService(function, search, params);
		String contentString = gson.toJson(content);
		MendeleyRecord record = gson.fromJson(contentString,
				MendeleyRecord.class);
		return record;
	}

	private JsonElement queryService(String function, String search,
			ApiParams params) {
		try {
			// TODO add other functions
			MendeleyUrl url = new MendeleyUrl(function, params);
			url.prepare(function, search);
			// System.out.println(url.build());
			url.build();
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			if (MendeleyUrl.isError(json)) {
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
	public String getMendeleyContents(MendeleyApiParams params) {
		String search = "test";
		JsonElement results = queryService("search", search, params);
		return gson.toJson(results);
	}

	public static Gson getGson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson;
	}

	// @Override
	// public Document getDocument(ApiParams params) {
	// return getMendeleyDocument(params);
	// }

	// @Override
	// public Document getDocument(String id, String date) {
	// return getDocument(id, date, false, false);
	// }

	// @Override
	// public Document getDocument(String id, String date, boolean withMeta,
	// boolean withNotes) {
	// MendeleyApiParams params = new MendeleyApiParams();
	// params.setId(id);
	// return getMendeleyDocument(params);
	// }

	// @Override
	// public Documents getDocuments(ApiParams params) {
	// Documents documents = getMendeleyDocuments("search", params);
	// return documents;
	// }

	// @Override
	// public Documents getDocuments(String user) {
	// return getDocuments(user, false, false);
	// }

	// @Override
	// public Documents getDocuments(String user, boolean withMeta,
	// boolean withNotes) {
	// MendeleyApiParams params = new MendeleyApiParams();
	// map to AnnotateDocuments
	// return getDocuments(params);
	// }

}
