package org.idiginfo.docsvc.svcapi.mas.svc;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.apisvc.Users;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;
import org.idiginfo.docsvc.svcapi.mas.model.MasKeyword;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponseObject;
import org.idiginfo.docsvc.svcapi.mas.model.MasResultObject;

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

/**
 * Class to implement the DocService object for MicrosoftAcademicSearch
 * 
 * @author griccardi
 * 
 */

public class MasService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();
	static final int CONNECT_TIMEOUT = 0;
	static GsonBuilder gsonBuilder = createGsonBuilder();
	static Gson gson = gsonBuilder.create();

	public MasService() {
		SvcApiLogger.enableLogging();
	}

	public static Gson getGson() {
		return gson;
	}

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
		try {
			StringBuffer urlString = new StringBuffer("http://");
			urlString.append(MasApiParams.API_HOST);
			urlString.append('/').append(MasApiParams.API_SERVICE);
			urlString.append('/').append(MasApiParams.API_SEARCH);
			urlString.append("?appId=").append(MasApiParams.API_KEY);
			urlString.append("&EndIdx=10&FulltextQuery=doi:(").append(
					URLEncoder.encode(doi, "UTF-8"));
			urlString.append(")&ResultObjects=Publication");

			String test = "http://academic.research.microsoft.com/json.svc/search?AppId=349bdedd-f3c6-4939-97a2-032141aeb565"
					+ "&EndIdx=10&FulltextQuery=doi:(10.1093%2fnar%2f25.17.3389)&ResultObjects=Publication";
			System.out.println(urlString.toString());
			System.out.println(test);
			// URL url = new URL(test);
			URL url = new URL(urlString.toString());

			InputStream in = url.openStream();
			String content = IOUtils.toString(in);
			JsonElement json = parser.parse(content);
			MasResponse response = gson.fromJson(json, MasResponse.class);
			// printResponse(response);
			return gson.toJson(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getResult(String doi, String title) {
		MasApiParams params = new MasApiParams();
		params.setId(doi);
		params.setTitle(title);
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
	public Document getAnnotations(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getAnnotations(Document document) {
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

			// // print the response into a file
			// content = gson.toJson(json);
			// reader.close();
			// FileWriter out = new FileWriter(
			// "c:/dev/api samples/Mas_details.json");
			// out.write(content);
			// out.close();
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
	public Document getDocument(ApiParams params) {
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
	 * 
	 * @param publicationId
	 * @param start
	 * @param end
	 * @return TODO make this a generic method
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

	public <T> MasResultObject<T> getResult(Integer publicationId, int start,
			int end) {
		// TODO this doesn't work
		MasApiParams params = new MasApiParams();
		params.setPublicationId(publicationId);
		// params.setPublicationContent("Keyword");
		params.setStartIdx(start);
		params.setEndIdx(end);
		MasResponse response = getResponse(params);
		MasResponseObject responseObj = response.getD();
		return null; // response.getD().getKeyword().getResult();
	}

	public String getMasContents(String string, MasApiParams params) {
		JsonElement content = queryService("getdocuments", params);
		String contentString = gson.toJson(content);
		return contentString;
	}

}
