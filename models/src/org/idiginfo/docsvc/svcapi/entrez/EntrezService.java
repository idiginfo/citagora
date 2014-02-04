package org.idiginfo.docsvc.svcapi.entrez;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.esearch.ESearchResult;
import org.idiginfo.medline.MedlineCitationSet;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Class to implement DocService object for Entrez (NCBI)
 * 
 * @author griccardi
 * 
 */

public class EntrezService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final int CONNECT_TIMEOUT = 200000;
	// contexts for XML parsers
	static JAXBContext searchContext = null;
	static JAXBContext fetchContext = null;

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});

	static {
		try {
			searchContext = JAXBContext.newInstance(ESearchResult.class);
			fetchContext = JAXBContext.newInstance(MedlineCitationSet.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Reader queryService(String function, EntrezApiParams params) {
		try {
			// TODO add other functions
			EntrezUrl url = new EntrezUrl(function, params);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			return reader;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// todo add capability to page through stored search response

	public EntrezDocuments getDocuments(List<String> ids) {
		EntrezApiParams params = new EntrezApiParams();
		params.setIds(ids);
		// fetch XML document from service
		Reader reader = queryService(EntrezUrl.FETCH, params);
		return getDocuments(reader);
	}

	public EntrezDocuments getDocuments(Reader reader) {
		// parse XML document using medline schema package
		try {
			MedlineCitationSet citationSet = (MedlineCitationSet) fetchContext
					.createUnmarshaller().unmarshal(reader);
			EntrezFetchResponse response = new EntrezFetchResponse(citationSet);
			return response.getDocuments();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public EntrezSearchResponse getSearchReponse(String searchTerms) {
		EntrezApiParams params = new EntrezApiParams();
		return getSearchReponse(params);
	}

	public EntrezSearchResponse getSearchReponse(EntrezApiParams params) {
		// fetch XML document from service
		Reader reader = queryService(EntrezUrl.SEARCH, params);
		return getSearchReponse(reader);
	}

	public EntrezSearchResponse getSearchReponse(Reader reader) {
		// parse XML document using medline schema package
		try {
			ESearchResult result = (ESearchResult) searchContext
					.createUnmarshaller().unmarshal(reader);
			EntrezSearchResponse response = new EntrezSearchResponse(result);
			return response;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Get documents from the result returned by a search query
	 * 
	 * @param idList
	 */
	public EntrezDocuments getDocumentsIdList(EntrezSearchResponse response) {
		// TODO handle full capability of
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public String format(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getAnnotations(ApiParams params) {
		return null;
	}

	@Override
	public Document getAnnotations(Document document) {
		return null;
	}
}
