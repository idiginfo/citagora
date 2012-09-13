package org.idiginfo.docsvc.svcapi.springer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.apisvc.Users;

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

public class SpringerService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		return getSpringerDocument(params);
	}

	@Override
	public Document getDocument(String id, String date) {
		return getDocument(id, date, false, false);
	}

	@Override
	public Document getDocument(String id, String date, boolean withMeta,
			boolean withNotes) {
		SpringerApiParams params = new SpringerApiParams();
		params.setId(id);
		return getSpringerDocument(params);
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		Documents documents = getSpringerDocuments("getdocuments", params);
		return documents;
	}

	@Override
	public Documents getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		SpringerApiParams params = new SpringerApiParams();
		// map to AnnotateDocuments
		return getDocuments(params);
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
	public Document getAnnotations(String code, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	private Document getSpringerDocument(ApiParams params) {
		// TODO must make a SpringerResult and then extract a SpringerRecord
		JsonElement content = queryService("getdocument", params);
		SpringerResult result = gson.fromJson(content, SpringerResult.class);
		Documents documents = result.getDocuments();
		if (documents==null||documents.size()<1) return null;
		return documents.get(0);
	}

	private Documents getSpringerDocuments(String function, ApiParams params) {
		JsonElement content = queryService(function, params);
		SpringerResult result = gson.fromJson(content, SpringerResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private JsonElement queryService(String function, ApiParams params) {
		try {
			// TODO add other functions
			SpringerUrl url = getSpringerUrl(function, params);
			if ("getdocument".equals(function)) {
				url.addParameter("doi", params.getId());
			} else if ("getdocuments".equals(function)) {
				url.addParameter("keyword", params.getSearchTerms());
			}
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			result.disconnect();
			if (SpringerUrl.isError(json)) {
				System.err.println(json);
				return null;
			}
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Add all of the Springer parameters to the URL
	 * 
	 * @param url
	 * @param params
	 */
	protected SpringerUrl getSpringerUrl(String function, ApiParams params) {
		SpringerUrl url = new SpringerUrl("metadata", "json");
		return url;
	}
}
