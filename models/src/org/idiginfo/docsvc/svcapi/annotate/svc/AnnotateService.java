package org.idiginfo.docsvc.svcapi.annotate.svc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocument;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocumentNotes;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocuments;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateUsers;

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

public class AnnotateService implements DocService {

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static JsonParser parser = new JsonParser();
    static final int CONNECT_TIMEOUT = 200000;

    private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
	    .createRequestFactory(new HttpRequestInitializer() {
		public void initialize(HttpRequest request) throws IOException {
		}
	    });
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public String format(String content) {
	String formattedContent;
	if (content == null)
	    return null;
	try {
	    JsonElement tree = parser.parse(content);
	    formattedContent = gson.toJson(tree);
	    return formattedContent;
	} catch (JsonParseException e) {
	}
	return null;
    }

    public AnnotateUsers getUsers(ApiParams params) {
	if (params != null && !(params instanceof AnnotateApiParams))
	    return null;
	AnnotateApiParams annotateParams = (AnnotateApiParams) params;
	JsonElement content = queryService("listUsers.php", annotateParams);
	// map to AnnotateUsers
	AnnotateUsers users = gson.fromJson(content, AnnotateUsers.class);
	return users;
    }

    @Override
    public Document getDocument(ApiParams params) {
	return getDocument(params.getId(), params.getDate(),
		params.getWithMeta(), params.getWithNotes());
    }

    public AnnotateDocumentNotes getDocument(String code, String date) {
	return getDocument(code, date, false, false);
    }

    public AnnotateDocumentNotes getDocument(String code, String date,
	    String withMeta, String withNotes) {
	return getDocument(code, date, "1".equals(withMeta),
		"1".equals(withNotes));
    }

    public AnnotateDocumentNotes getDocument(String code, String date,
	    boolean withMeta, boolean withNotes) {
	JsonElement content;
	AnnotateApiParams params = new AnnotateApiParams();
	params.setId(code);
	params.setDate(date);
	// params.setApiAnnotateUser(user);
	params.setWithMeta(withMeta);
	params.setWithNotes(withNotes);
	content = queryService("listNotes.php", params);
	// System.out.println("result of annotate query: " + content);
	// content = format(content);
	// System.out.println(content);
	// map to AnnotateDocuments
	AnnotateDocumentNotes document = gson.fromJson(content,
		AnnotateDocumentNotes.class);
	return document;
    }

    @Override
    public Documents getDocuments(ApiParams params) {
	if (!(params instanceof AnnotateApiParams))
	    return null;
	AnnotateApiParams annotateParams = (AnnotateApiParams) params;

	return getDocuments(annotateParams.getApiUser(),
		annotateParams.withMeta, annotateParams.withNotes);
    }

    public AnnotateDocuments getDocuments(String user) {
	return getDocuments(user, false, false);
    }

    public AnnotateDocuments getDocuments(String user, String withMeta,
	    String withNotes) {
	return getDocuments(user, "1".equals(withMeta), "1".equals(withNotes));

    }

    public AnnotateDocuments getDocuments(String user, boolean withMeta,
	    boolean withNotes) {
	JsonElement content;
	AnnotateApiParams params = new AnnotateApiParams();
	params.setOwner(user);
	params.setWithMeta(withMeta);
	params.setWithNotes(withNotes);
	content = queryService("listDocuments.php", params);
	// map to AnnotateDocuments
	AnnotateDocuments documents = new AnnotateDocuments(gson.fromJson(
		content, AnnotateDocument[][].class));
	return documents;
    }

    /**
     * This routine will get the document without providing a date. Currently no
     * way to do this on a.nnotate services
     * 
     * @param documentCode
     * @return
     */
    public AnnotateDocumentNotes getNotes(String documentCode) {
	AnnotateDocument document = null;
	// TODO get the document!
	return getNotes(document);
    }

    public AnnotateDocumentNotes getNotes(Document document) {
	if (!(document instanceof AnnotateDocument))
	    return null;
	return getNotes(document.getId());
    }

    @Override
    public Document getAnnotations(ApiParams params) {
	if (!(params instanceof AnnotateApiParams))
	    return null;
	return getAnnotations(params.getId(), params.getDate());
    }

    @Override
    public AnnotateDocumentNotes getAnnotations(Document document) {
	if (document == null || !(document instanceof AnnotateDocument))
	    return null;
	return getAnnotations(document.getId(), document.getDate());
    }

    public AnnotateDocumentNotes getAnnotations(String code, String date) {
	if (code == null || date == null)
	    return null;
	JsonElement content;
	AnnotateApiParams params = new AnnotateApiParams();
	params.setId(code);
	params.setDate(date);// doesn't work without date
	content = queryService("listNotes.php", params);
	// map to AnnotateDocumentNotes
	AnnotateDocumentNotes documentNotes = gson.fromJson(content,
		AnnotateDocumentNotes.class);
	return documentNotes;

    }

    /**
     * Invoke the a.nnotate service
     * 
     * @param function
     * @return
     */
    private JsonElement queryService(String function,
	    AnnotateApiParams annotateParams) {
	if (function == null)
	    return null;
	try {
	    AnnotateUrl url = new AnnotateUrl(function, annotateParams);
	    url.prepare();
	    System.out.println(url.build());
	    HttpRequest request = requestFactory.buildGetRequest(url);
	    request.setConnectTimeout(CONNECT_TIMEOUT);
	    HttpResponse result = request.execute();
	    Reader reader = new InputStreamReader(result.getContent(), "UTF-8");
	    JsonParser parser = new JsonParser();
	    JsonElement json = parser.parse(reader);
	    result.disconnect();
	    if (AnnotateUrl.isError(json)) {
		System.err.println(json);
		return null;
	    }
	    return json;
	} catch (JsonParseException e) {
	    return null;
	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
    }

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
