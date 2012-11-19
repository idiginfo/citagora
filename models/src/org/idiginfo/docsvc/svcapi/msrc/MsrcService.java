package org.idiginfo.docsvc.svcapi.msrc;

import java.io.FileWriter;
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

public class MsrcService implements DocService {

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

	public MsrcCollectionResult getCitations(String timespan) {
		return null;
	}

	public MsrcRecord getDetailsByDoi(String doi) {
		String biblioId = getBiblioIdByDoi(doi);
		return getDetails(biblioId);
	}

	public MsrcRecord getDetailsByPmid(String pmid) {
		String biblioId = getBiblioIdByPmid(pmid);
		return getDetails(biblioId);
	}

	public MsrcRecord getDetails(String biblioId) {
		MsrcApiParams params = new MsrcApiParams();
		params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		params.setId(biblioId);
		return (MsrcRecord) getDocument(params);
	}

	public String getBiblioIdByDoi(String doi) {
		MsrcApiParams params = new MsrcApiParams();
		params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		params.setId(doi);
		MsrcRecord document = (MsrcRecord) getDocument(params);
		return document.getBiblioId();
	}

	public String getBiblioIdByPmid(String pmid) {
		MsrcApiParams params = new MsrcApiParams();
		params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		params.setId(pmid);
		MsrcRecord document = (MsrcRecord) getDocument(params);
		System.out.println("For Pmid: " + pmid + " Biblio id: "
				+ document.getBiblioId());
		return document.getBiblioId();
	}

	@Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Get document from Biblio service Check first for presence of doi, then
	 * pmcid in params If not present, send parameters to service
	 */
	@Override
	public Document getDocument(ApiParams params) {
		if (params.getDoi() != null) {
			return getDetailsByDoi(params.getDoi());
		}
		if (params instanceof MsrcApiParams
				&& ((MsrcApiParams) params).getPmcid() != null) {
			return getDetailsByPmid(((MsrcApiParams) params).getPmcid());
		}
		JsonElement content = queryService("getdocument",
				(MsrcApiParams) params);
		MsrcRecord result = gson.fromJson(content, MsrcRecord.class);
		return result;
	}

	@Override
	public Document getDocument(String id, String date) {
		return getDocument(id, date, false, false);
	}

	@Override
	public Document getDocument(String id, String date, boolean withMeta,
			boolean withNotes) {
		MsrcApiParams params = new MsrcApiParams();
		params.setId(id);
		Documents documents = getBiblioDocuments("getdocument", params);
		if (documents == null || documents.size() < 1)
			return null;
		return documents.get(0);
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		Documents documents = getBiblioDocuments("getdocuments", params);
		return documents;
	}

	@Override
	public Documents getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		MsrcApiParams params = new MsrcApiParams();
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

	private Documents getBiblioDocuments(String function, ApiParams params) {
		if (!(params instanceof MsrcApiParams)) {
			return null; // TODO exception here
		}
		JsonElement content = queryService(function,
				(MsrcApiParams) params);
		MsrcCollectionResult result = gson.fromJson(content, MsrcCollectionResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private JsonElement queryService(String function, MsrcApiParams params) {
		try {
			String content;
			// TODO add other functions
			MsrcUrl url = new MsrcUrl(params);
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
			if (MsrcUrl.isError(json)) {
				System.err.println(MsrcUrl.getMessage(json));
				return null;
			}

			content = gson.toJson(json);
			result.disconnect();
			reader.close();
			FileWriter out = new FileWriter(
					"c:/dev/api samples/Biblio_details_201135.json");
			out.write(content);
			out.close();
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Add all of the Biblio parameters to the URL
	 * 
	 * @param url
	 * @param params
	 */
	protected MsrcUrl getBiblioUrl(String function, ApiParams params) {
		MsrcUrl url = new MsrcUrl("citations", "1d");
		return url;
	}
}
