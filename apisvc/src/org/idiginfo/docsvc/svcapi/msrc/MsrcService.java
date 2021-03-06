package org.idiginfo.docsvc.svcapi.msrc;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiResult;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ListTypeAdapter;
import org.idiginfo.docsvc.model.apisvc.MatchResult;
import org.idiginfo.docsvc.svcapi.SvcApiLogger;

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
import com.google.gson.reflect.TypeToken;

/**
 * Class to implement the MilitarySuicideResearchConsortium (MSRC) DocService
 * object
 * 
 * @author griccardi
 * 
 */

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
	public MsrcService() {
		SvcApiLogger.enableLogging();
	}
	/**
	 * Get the gson object tailored to processing the SciVerse results
	 * 
	 * We have to make the deserializer able to deal with the way that sciverse
	 * deals with lists of 1 element (no list ("{") if only 1 element, list
	 * ("[{") otherwise
	 * 
	 * @return
	 */
	public Gson getGson() {
		Type annotationListType = new TypeToken<List<MsrcAnnotationRef>>() {
		}.getType();
		MsrcAnnotationRef sampleAnnotation = new MsrcAnnotationRef();
		Type documentListType = new TypeToken<List<MsrcDocument>>() {
		}.getType();
		MsrcDocument sampleDocument = new MsrcDocument();
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(
						annotationListType,
						new ListTypeAdapter<MsrcAnnotationRef>(sampleAnnotation))
				.registerTypeAdapter(documentListType,
						new ListTypeAdapter<MsrcDocument>(sampleDocument))
				.setPrettyPrinting().create();
		return gson;
	}

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
		if (biblioId == null)
			return null;
		return getDetails(biblioId);
	}

	public MsrcRecord getDetailsByPmid(String pmid) {
		String biblioId = getBiblioIdByPmid(pmid);
		if (biblioId == null)
			return null;
		return getDetails(biblioId);
	}

	public MsrcRecord getDetails(String biblioId) {
		MsrcApiParams params = new MsrcApiParams();
		params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		params.setId(biblioId);
		return (MsrcRecord) getDocument(params);
	}

	public String getBiblioIdByDoi(String doi) {
		// TODO
		return null;
		// MsrcApiParams params = new MsrcApiParams();
		// params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		// params.setId(doi);
		// MsrcRecord document = (MsrcRecord) getDocument(params);
		// return document.getBiblioId();
	}

	public String getBiblioIdByPmid(String pmid) {
		// TODO
		return null;
		// MsrcApiParams params = new MsrcApiParams();
		// params.setCollection(MsrcApiParams.BIBLIO_COLLECTION);
		// params.setId(pmid);
		// MsrcRecord document = (MsrcRecord) getDocument(params);
		// System.out.println("For Pmid: " + pmid + " Biblio id: "
		// + document.getBiblioId());
		// return document.getBiblioId();
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
	public List<? extends Document> getDocuments(ApiParams params) {
		List<Document> documents = getBiblioDocuments("getdocuments", params);
		return documents;
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

	private List<Document> getBiblioDocuments(String function, ApiParams params) {
		if (!(params instanceof MsrcApiParams)) {
			return null; // TODO exception here
		}
		JsonElement content = queryService(function, (MsrcApiParams) params);
		MsrcCollectionResult result = gson.fromJson(content,
				MsrcCollectionResult.class);
		if (result == null)
			return null;
		return result.getDocuments();
	}

	private JsonElement queryService(String function, MsrcApiParams params) {
		try {
			String content;
			// TODO add other functions
			MsrcUrl url = new MsrcUrl(params);
			url.prepare();
			// System.out.println(url.build());
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
					"c:/dev/api samples/Biblio_details_.json");
			out.write(content);
			out.close();
			return json;
		} catch (IOException e) {
			// e.printStackTrace();
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

	MsrcApiParams params = new MsrcApiParams();

	public String getJsonDocument(int documentId) {
		params.setId(Integer.toString(documentId));
		JsonElement result = queryService("document", params);
		return gson.toJson(result);
	}

	public List<Integer> getMsrcDocumentIds() {
		// params?
		JsonElement result = queryService("documentIds", params);
		MsrcResult msrcResult = gson.fromJson(result, MsrcResult.class);
		if (msrcResult.documentIds != null)
			return msrcResult.documentIds;
		return new Vector<Integer>();
	}

	@Override
	public JsonElement matchService(List<String> refs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(JsonArray refsArray) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends MatchResult> getMatch(String[] refStrings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApiResult getApiResult(String function, String keywords,
			ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(Reader in) {
		// TODO Auto-generated method stub
		return null;
	}
}
