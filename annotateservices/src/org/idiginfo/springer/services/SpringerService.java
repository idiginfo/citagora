package org.idiginfo.springer.services;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateDocumentNotes;
import org.idiginfo.annotate.services.AnnotateUrl;
import org.idiginfo.annotationmodel.AnnotationService;
import org.idiginfo.annotationmodel.ApiParams;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;
import org.idiginfo.springer.services.SpringerResult.Result;

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
		String content;
		ApiParams params = new AnnotateApiParams();
		params.setCode(code);
		params.setDate(date);
		// params.setApiAnnotateUser(user);
		// params.setWithMeta(withMeta);
		// params.setWithNotes(withNotes);
		SpringerResult results = getSpringerResult("getdocument", params);
		// System.out.println(content);
		// map to AnnotateDocuments
		List<SpringerRecord> records = results.records;
		SpringerRecord record = records.get(0);

		return record;
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		// TODO Auto-generated method stub
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

	@Override
	public Document getAnnotations(String code, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	private SpringerResult getSpringerResult(String function, ApiParams params) {
		String content = queryService(function, params);
		SpringerResult result = gson.fromJson(content, SpringerResult.class);
		return result;
	}

	private String queryService(String function, ApiParams params) {
		if (function == null || !function.equals("getdocument"))
			return null;
		String content;
		try {
			// TODO add other functions
			SpringerUrl url = new SpringerUrl("metadata", "json");
			url.addParameter("doi", params.getCode());
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
