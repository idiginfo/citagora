package org.idiginfo.docsvc.svcapi.mas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

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
import com.google.common.util.concurrent.Service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class MasService implements DocService {

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

	public String getResult(String doi) {
		MasApiParams params = new MasApiParams();
		params.setId(doi);
		JsonElement json = queryService("getdocument", params);
		return gson.toJson(json);
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

			content = gson.toJson(json);
			result.disconnect();
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
}
