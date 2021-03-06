package org.idiginfo.docsvc.svcapi.mendeley;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiResult;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.MatchResult;
import org.idiginfo.docsvc.model.apisvc.Users;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;

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
 * Class that implements the Mendeley API Service function
 * 
 * @author sflager
 * 
 */

public class MendeleyService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Gson gson = getGson();

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});

	public MendeleyService(){
		SvcApiLogger.enableLogging();
	}
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

	public static List<Document> createMendeleyDocuments(
			List<MendeleyRecord> records) {
		List<Document> list = new Vector<Document>();
		if (records == null)
			return list;
		for (Document doc : records) {
			list.add(doc);
		}
		return list;
	}

	// @Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	private MendeleyHeader getMendeleyHeader(ApiParams params) {
		// make a MendeleyResult and then extract a MendeleyRecord
		String search = "test";
		JsonElement content = queryService("search", search, params);
		printJson(content, "c:/dev/api samples/mendeley1.json");
		MendeleyResult result = gson.fromJson(content, MendeleyResult.class);
		List<MendeleyHeader> headers = result.getHeaders();
		if (headers == null || headers.size() < 1)
			return null;
		return headers.get(0);
	}

	// pass the uuid into URL creator to build "details" action
	public MendeleyRecord getMendeleyRecord(String function, String uuid,
			ApiParams params) {
		JsonElement content = queryService(function, uuid, params);
		MendeleyRecord result = gson.fromJson(content, MendeleyRecord.class);
		return result;
	}

	// pass the search string into URL creator to build "search" action
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

	public Gson getGson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson;
	}

	@Override
	public Document getDocument(ApiParams params) {
		return getMendeleyDocument(params);
	}

	private Document getMendeleyDocument(ApiParams params) {
		String keywords = params.getKeyword();
		params.setFirstResult(0);
		MendeleyResult result = getMendeleyResult("search", keywords, params);
		if (result == null || result.getTotalResults() == 0)
			return null;
		List<MendeleyHeader> headers = result.getHeaders();
		String uuid = headers.get(0).getUuid();
		String uText = getMendeleyUUID("details", uuid, params);
		MendeleyRecord document = gson.fromJson(uText, MendeleyRecord.class);
		return document;
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
		List<Document> documents = getMendeleyDocuments(params);
		return documents;
	}

	private List<Document> getMendeleyDocuments(ApiParams params) {
		String keywords = params.getKeyword();
		List<MendeleyRecord> mDocs = new Vector<MendeleyRecord>();
		MendeleyResult result = getMendeleyResult("search", keywords, params);
		if (result == null)
			return null;
		List<MendeleyHeader> headers = result.getHeaders();
		for (int i = 0; i < headers.size(); i++) {
			String uuid = headers.get(i).getUuid();
			String uText = getMendeleyUUID("details", uuid, params);
			MendeleyRecord document = gson
					.fromJson(uText, MendeleyRecord.class);
			mDocs.add(document);
		}
		List<Document> documents = createMendeleyDocuments(mDocs);
		return documents;
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

	@Override
	public List<? extends MatchResult> getMatch(String[] refStrings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult getApiResult(String function, String keywords,
			ApiParams params) {
		return getMendeleyResult(function, keywords, params);
	}

	@Override
	public Document getDocument(Reader in) {
		// TODO Auto-generated method stub
		return null;
	}
}
