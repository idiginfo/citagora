package org.idiginfo.docsvc.model.altmetric;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

public class AltmetricService implements DocService {

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
		return getDocument(params.getId(), params.getDate());
	}

	@Override
	public Document getDocument(String id, String date) {
		return getDocument(id, date, false, false);
	}

	@Override
	public Document getDocument(String id, String date, boolean withMeta,
			boolean withNotes) {
		AltmetricApiParams params = new AltmetricApiParams();
		params.setId(id);
		Documents documents = getAltmetricDocuments("getdocument", params);
		if (documents == null || documents.size() < 1)
			return null;
		return documents.get(0);
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		Documents documents = getAltmetricDocuments("getdocuments", params);
		return documents;
	}

	@Override
	public Documents getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		AltmetricApiParams params = new AltmetricApiParams();
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

	private Documents getAltmetricDocuments(String function, ApiParams params) {
		String content = queryService(function, params);
		AltmetricResult result = gson.fromJson(content, AltmetricResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private String queryService(String function, ApiParams params) {
		try {
			String content;
			// TODO add other functions
			AltmetricUrl url = new AltmetricUrl(function, params);
			if ("getdocument".equals(function)) {
				//TODO add getdocument
			} else if ("getdocuments".equals(function)) {
				//url.addParameter("keyword", params.getSearchTerms());
			}
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent(), "UTF-8");
			if (AltmetricUrl.isError(content)) {
				System.err.println(content);
				return null;
			}
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			content = gson.toJson(tree);
			result.disconnect();
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Add all of the Altmetric parameters to the URL
	 * 
	 * @param url
	 * @param params
	 */
	protected AltmetricUrl getAltmetricUrl(String function, ApiParams params) {
		AltmetricUrl url = new AltmetricUrl("citations", "1d");
		return url;
	}
}
