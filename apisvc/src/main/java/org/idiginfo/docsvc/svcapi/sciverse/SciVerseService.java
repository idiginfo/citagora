package org.idiginfo.docsvc.svcapi.sciverse;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiResult;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ListTypeAdapter;
import org.idiginfo.docsvc.model.apisvc.MatchResult;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;

import com.google.api.client.http.HttpHeaders;
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
import com.google.gson.reflect.TypeToken;

/**
 * Class to implement DocService object for Sciverse
 * 
 * @author griccardi
 * 
 */

public class SciVerseService implements DocService {
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Type linkListType = new TypeToken<List<SciVerseLink>>() {
	}.getType();
	Type documentListType = new TypeToken<List<SciVerseDocument>>() {
	}.getType();
	Type affiliationListType = new TypeToken<List<SciVerseDocument.Affiliation>>() {
	}.getType();

	Gson gson = getGson();
	String authKey;

	public SciVerseService() {
		SvcApiLogger.enableLogging();
		authKey = getAuthKey();
		System.out.println("authKey: " + authKey);
	}

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});

	/**
	 * Get the gson object tailored to processing the SciVerse results
	 * 
	 * We have to make the deserializer able to deal with the way that sciverse
	 * deals with lists of 1 element (no list ("{") if only 1 element, list
	 * ("[{") otherwise
	 * 
	 * @return
	 */
	@Override
	public Gson getGson() {
		Type linkListType = new TypeToken<List<SciVerseLink>>() {
		}.getType();
		Type documentListType = new TypeToken<List<SciVerseDocument>>() {
		}.getType();
		Type affiliationListType = new TypeToken<List<SciVerseDocument.Affiliation>>() {
		}.getType();
		SciVerseDocument sampleDocument = new SciVerseDocument();
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(linkListType,
						new ListTypeAdapter<SciVerseLink>(new SciVerseLink()))
				.registerTypeAdapter(documentListType,
						new ListTypeAdapter<SciVerseDocument>(sampleDocument))
				.registerTypeAdapter(
						affiliationListType,
						new ListTypeAdapter<SciVerseDocument.Affiliation>(
								sampleDocument.new Affiliation()))

				.setPrettyPrinting().create();
		return gson;
	}

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
	public Document getDocument(ApiParams params) {
		return getSciVerseDocument(params);
	}

	@Override
	public SciVerseDocuments getDocuments(ApiParams params) {
		SciVerseDocuments documents = getSciVerseDocuments("getdocuments",
				params);
		return documents;
	}

	@Override
	public SciVerseDocument getAnnotations(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SciVerseDocument getAnnotations(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	private SciVerseDocument getSciVerseDocument(ApiParams params) {
		// TODO must make a SciVerseResult and then extract a SciVerseRecord
		JsonElement content = queryService("getdocument", params);
		printJson(content, "c:/dev/api samples/springer1.json");
		SciVerseDocument document = gson.fromJson(content,
				SciVerseDocument.class);
		return document;
	}

	private SciVerseDocuments getSciVerseDocuments(String function,
			ApiParams params) {
		// TODO this is not a working method: needs revision
		JsonElement content = queryService(function, params);
		SciVerseDocuments result = gson.fromJson(content,
				SciVerseDocuments.class);
		if (result == null)
			return null;
		return result;
	}

	public SciVerseResult getSciVerseResult(String function, ApiParams params) {
		JsonElement content = queryService(function, params);
		SciVerseResult result = gson.fromJson(content, SciVerseResult.class);
		if (result == null)
			return null;
		return result;
	}

	public String getSciVerseContents(String function, ApiParams params) {
		JsonElement content = queryService(function, params);
		if (content == null)
			return null;
		SciVerseResult result = gson.fromJson(content, SciVerseResult.class);
		String contentString = gson.toJson(result);
		return contentString;
	}

	public static String getAuthKey() {
		try {
			ScopusAuthUrl url = new ScopusAuthUrl();
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpHeaders headers = new HttpHeaders();

			headers.set("X-ELS-APIKey", SciVerseApiParams.API_KEY);// griccardi
			request.setHeaders(headers);
			HttpResponse result = request.execute();
			String content = IOUtils.toString(result.getContent());
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public JsonElement queryService(String function, ApiParams params) {
		try {
			// TODO add other functions
			SciVerseUrl url = new SciVerseUrl(function, "SCOPUS", params);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept("application/json");
			headers.set("X-ELS-APIKey", SciVerseApiParams.API_KEY);
			headers.set("X-ELS-Authtoken", authKey);
			headers.set("X-ELS-ResourceVersion", "XOCS");
			request.setHeaders(headers);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			if (SciVerseUrl.isError(json)) {
				System.err.println(json);
				return null;
			}
			return json;
		} catch (Exception e) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(Reader in) {
		// TODO Auto-generated method stub
		return null;
	}

}
