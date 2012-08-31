package org.idiginfo.docsvc.svcapi.mas.svc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.DocService;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;
import org.idiginfo.docsvc.model.model.Users;
import org.idiginfo.docsvc.svcapi.mas.model.MasKeyword;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponseObject;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class MasService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 200000;
	static GsonBuilder gsonBuilder = createGsonBuilder();
	Gson gson = gsonBuilder.create();

	private static GsonBuilder createGsonBuilder() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE);
		return gsonBuilder;
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

	public String getResult(String doi) {
		MasApiParams params = new MasApiParams();
		params.setId(doi);
		MasResponse response = getResponse(params);
		// printResponse(response);
		return gson.toJson(response);
	}

	public MasResponse getResponse(MasApiParams params) {
		JsonElement json = queryService("getdocument", params);
		MasResponse response = gson.fromJson(json, MasResponse.class);
		return response;
	}

	@SuppressWarnings("unused")
	private void printResponse(MasResponse response) {
		MasResponseObject object = response.getResultObject();
		System.out.println(object.getResultCode());

	}

	@Override
	public Document getDocument(String id, String date) {
		return getDocument(id, date, false, false);
	}

	@Override
	public Documents getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		MasApiParams params = new MasApiParams();
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

	private JsonElement queryService(String function, MasApiParams params) {
		try {
			String content;
			// TODO add other functions
			MasUrl url = new MasUrl(params);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			if (MasResponse.isError(json)) {
				System.err.println(MasResponse.getMessage(json));
				return null;
			}
			result.disconnect();

			// print the response into a file
			content = gson.toJson(json);
			reader.close();
			FileWriter out = new FileWriter(
					"c:/dev/api samples/Mas_details.json");
			out.write(content);
			out.close();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Add all of the Mas parameters to the URL
	 * 
	 * @param url
	 * @param params
	 */
	protected MasUrl getMasUrl(String function, ApiParams params) {
		MasUrl url = new MasUrl();
		return url;
	}

	@Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}


	/** 
	 * Get a block of keywords for a publication
	 * @param publicationId
	 * @param start
	 * @param end
	 * @return
	 *TODO make this a generic method
	 */
	public List<MasKeyword> getKeywords(Integer publicationId, int start,
			int end) {
		MasApiParams params = new MasApiParams();
		params.setPublicationId(publicationId);
		params.setPublicationContent("Keyword");
		params.setStartIdx(start);
		params.setEndIdx(end);
		MasResponse response = getResponse(params);
		return response.getD().getKeyword().getResult();
	}
	
	public <T> List<T> getResult(Integer publicationId, int start,
			int end) {
		//TODO this doesn't work
		MasApiParams params = new MasApiParams();
		params.setPublicationId(publicationId);
		params.setPublicationContent("Keyword");
		params.setStartIdx(start);
		params.setEndIdx(end);
		MasResponse response = getResponse(params);
		return null; // response.getD().getKeyword().getResult();
	}

}
