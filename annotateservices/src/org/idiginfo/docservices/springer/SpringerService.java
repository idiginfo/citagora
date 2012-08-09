package org.idiginfo.docservices.springer;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docservices.model.AnnotationService;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.model.Documents;
import org.idiginfo.docservices.model.Users;

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

public class SpringerService implements AnnotationService {

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
		return getDocument(params.getCode(), params.getDate());
	}

	@Override
	public Document getDocument(String code, String date) {
		return getDocument(code, date, false, false);
	}

	@Override
	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes) {
		SpringerApiParams params = new SpringerApiParams();
		params.setCode(code);
		Documents documents = getSpringerDocuments("getdocument", params);
		if (documents == null)
			return null;
		return documents.get(0);
	}

	@Override
	public Documents getDocuments(ApiParams apiParams) {
		// TODO Auto-generated method stub
		SpringerApiParams params;

		if (apiParams instanceof SpringerApiParams) {
			params = (SpringerApiParams) apiParams;
		} else {
			params = new SpringerApiParams(apiParams);
		}
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

	private Documents getSpringerDocuments(String function,
			SpringerApiParams params) {
		String content = queryService(function, params);
		SpringerResult result = gson.fromJson(content, SpringerResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private String queryService(String function, SpringerApiParams params) {
		try {
			String content;
			// TODO add other functions
			SpringerUrl url = new SpringerUrl("metadata", "json");
			if ("getdocument".equals(function)) {
				url.addParameter("doi", params.getCode());
			} else if ("getdocuments".equals(function)) {
				url.addParameter("keyword", params.getSearchTerms());
			}
			url.prepare();
			// System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
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
}
