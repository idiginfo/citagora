package org.idiginfo.docsvc.svcapi.springer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.DocService;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;
import org.idiginfo.docsvc.model.model.Users;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
		String content = queryService("getdocument", params);
		SpringerRecord result = gson.fromJson(content, SpringerRecord.class);
		return result;
	}

	private Documents getSpringerDocuments(String function, ApiParams params) {
		String content = queryService(function, params);
		SpringerResult result = gson.fromJson(content, SpringerResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private String queryService(String function, ApiParams params) {
		try {
			String content;
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
			content = IOUtils.toString(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			content = gson.toJson(tree);
			result.disconnect();
			if (SpringerUrl.isError(content)) {
				System.err.println(content);
				return null;
			}
			return content;
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
