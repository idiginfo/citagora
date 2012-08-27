package org.idiginfo.docsvc.svcapi.altmetric;

import java.io.FileWriter;
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

	@Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
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
		String content = queryService("getdocument",
				(AltmetricApiParams) params);
		AltmetricRecord result = gson.fromJson(content, AltmetricRecord.class);
		return result;
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
		if (!(params instanceof AltmetricApiParams)) {
			return null; // TODO exception here
		}
		String content = queryService(function, (AltmetricApiParams) params);
		AltmetricResult result = gson.fromJson(content, AltmetricResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private String queryService(String function, AltmetricApiParams params) {
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
			content = IOUtils.toString(result.getContent(), "UTF-8");
			if (AltmetricUrl.isError(content)) {
				System.err.println(content);
				return null;
			}
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			content = gson.toJson(tree);
			result.disconnect();
			FileWriter out = new FileWriter(
					"c:/dev/api samples/altmetric_details_201135.json");
			out.write(content);
			out.close();
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
