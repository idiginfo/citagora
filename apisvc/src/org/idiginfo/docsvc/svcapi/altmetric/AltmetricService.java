package org.idiginfo.docsvc.svcapi.altmetric;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;

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
 * Class to implement DocService for Altmetric 
 * 
 * @author griccardi
 * 
 */

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

	public AltmetricResult getCitations(String timespan) {
		return null;
	}

	public AltmetricRecord getDetailsByDoi(String doi) {
		String altmetricId = getAltmetricIdByDoi(doi);
		return getDetails(altmetricId);
	}

	public AltmetricRecord getDetailsByPmid(String pmid) {
		String altmetricId = getAltmetricIdByPmid(pmid);
		return getDetails(altmetricId);
	}

	public AltmetricRecord getDetails(String altmetricId) {
		if (altmetricId == null)
			return null;
		AltmetricApiParams params = new AltmetricApiParams();
		params.setCollection(AltmetricApiParams.DETAILS_COLLECTION);
		params.setId(altmetricId);
		return (AltmetricRecord) getDocument(params);
	}

	public String getAltmetricIdByDoi(String doi) {
		AltmetricApiParams params = new AltmetricApiParams();
		params.setCollection(AltmetricApiParams.DOI_COLLECTION);
		params.setId(doi);
		AltmetricRecord document = (AltmetricRecord) getDocument(params);
		if (document == null)
			return null;
		return document.getAltmetricId();
	}

	public String getAltmetricIdByPmid(String pmid) {
		AltmetricApiParams params = new AltmetricApiParams();
		params.setCollection(AltmetricApiParams.PMID_COLLECTION);
		params.setId(pmid);
		AltmetricRecord document = (AltmetricRecord) getDocument(params);
		System.out.println("For Pmid: " + pmid + " Altmetric id: "
				+ document.getAltmetricId());
		return document.getAltmetricId();
	}

	/**
	 * Get document from Altmetric service Check first for presence of doi, then
	 * pmcid in params If not present, send parameters to service
	 */
	@Override
	public Document getDocument(ApiParams params) {
		if (params.getDoi() != null) {
			return getDetailsByDoi(params.getDoi());
		}
		if (params instanceof AltmetricApiParams
				&& ((AltmetricApiParams) params).getPmcid() != null) {
			return getDetailsByPmid(((AltmetricApiParams) params).getPmcid());
		}
		JsonElement content = queryService("getdocument",
				(AltmetricApiParams) params);
		AltmetricRecord result = gson.fromJson(content, AltmetricRecord.class);
		return result;
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
		return getAltmetricDocuments("getdocuments", params);
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

	private List<? extends Document> getAltmetricDocuments(String function, ApiParams params) {
		if (!(params instanceof AltmetricApiParams)) {
			return null; // TODO exception here
		}
		JsonElement content = queryService(function,
				(AltmetricApiParams) params);
		AltmetricResult result = gson.fromJson(content, AltmetricResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private JsonElement queryService(String function, AltmetricApiParams params) {
		try {
			String content;
			// TODO add other functions
			AltmetricUrl url = new AltmetricUrl(params);
			if ("getdocument".equals(function)) {
				// TODO add getdocument
			} else if ("getdocuments".equals(function)) {
				// url.addParameter("keyword", params.getSearchTerms());
			}
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			JsonParser parser = new JsonParser();
			JsonElement json = parser.parse(reader);
			if (AltmetricUrl.isError(json)) {
				System.err.println(AltmetricUrl.getMessage(json));
				return null;
			}

			content = gson.toJson(json);
			result.disconnect();
			reader.close();
			// FileWriter out = new FileWriter(
			// "c:/dev/api samples/altmetric_details_201135.json");
			// out.write(content);
			// out.close();
			return json;
		} catch (Exception e) {
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

	@Override
	public JsonElement matchService(List<String> refs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gson getGson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(JsonArray refsArray) {
		// TODO Auto-generated method stub
		return null;
	}
}
