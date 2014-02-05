package org.idiginfo.docsvc.svcapi.crossref;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Class to implement DocService object for Crossref
 * 
 * @author griccardi
 * 
 */

public class CrossrefRdfService implements DocService {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});
	static final int CONNECT_TIMEOUT = 200000;

	@Override
	public String format(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		String doi = params.getDoi();
		return getDocument(doi);
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
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

	private Model queryService(String doi) {
		try {
			// TODO add other functions
			CrossrefRdfUrl url = new CrossrefRdfUrl(doi);
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept("application/rdf+xml");
			request.setHeaders(headers);
			request.setConnectTimeout(CONNECT_TIMEOUT);
			HttpResponse result = request.execute();
			Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
			Model model = ModelFactory.createDefaultModel();
			model.read(reader, null);
			result.disconnect();
			return model;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Document getDocument(String doi) {
		Model model = queryService(doi);
		if (model == null)
			return null;
		Resource resource = model.getResource("http://dx.doi.org/" + doi);
		RdfDocument rdfDocument = new RdfDocument(model, resource);
		return rdfDocument;
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
