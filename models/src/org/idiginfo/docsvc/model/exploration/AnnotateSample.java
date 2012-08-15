package org.idiginfo.docsvc.model.exploration;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.annotate.AnnotateApiParams;
import org.idiginfo.docsvc.model.annotate.AnnotateDocument;
import org.idiginfo.docsvc.model.annotate.AnnotateDocumentNotes;
import org.idiginfo.docsvc.model.annotate.AnnotateDocuments;
import org.idiginfo.docsvc.model.annotate.AnnotateUrl;
import org.idiginfo.docsvc.model.annotate.AnnotateUsers;
import org.idiginfo.docsvc.model.model.Document;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class AnnotateSample {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;

	private static void run() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});
		AnnotateUsers users = testUsers();
		System.out.println("first member: " + users.getMembers().get(0));
		System.out.println("first annotator: " + users.getAnnotaters().get(0));

		String documentUser = users.getMembers().get(7);
		System.out.println("Document user: " + documentUser);
		AnnotateDocument[][] documentArray = testDocuments(documentUser);
		AnnotateDocuments documents = new AnnotateDocuments(documentArray);
		System.out.println("name of first document: "
				+ documents.getDocument(0).getName());
		System.out.println("code of first document: "
				+ documents.getDocument(0).getId());
		System.out.println("number of documents " + documents.size());
		Document selectedDocument = documents.getDocument(1);
		System.out.println("Selected document: " + selectedDocument.getId());
		AnnotateDocumentNotes documentNotes = testNotes(selectedDocument);// selectedDocument.getCode());
		System.out.println("Number of notes: " + documentNotes.notes.length);
		System.out.println("Name of document: " + documentNotes.meta.getName());
	}

	public static AnnotateUsers testUsers() {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			AnnotateUrl url = new AnnotateUrl("listUsers.php", params);
			url.prepare();
			System.out.println(url.getRawPath());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (AnnotateUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		// generic parse and print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// convert the json string back to object
		JsonParser parser = new JsonParser();
		try {
			JsonElement tree = parser.parse(content);
			String json = gson.toJson(tree);
			System.out.println("response from listusers.php\n" + json);
		} catch (JsonIOException e) {
		} catch (JsonParseException e) {

		}
		// map to AnnotateUsers
		AnnotateUsers users = gson.fromJson(content, AnnotateUsers.class);
		return users;
	}

	public static AnnotateDocument[][] testDocuments(String user) {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			params.setOwner(user);
			params.setWithMeta("1");
			// params.setWithNotes("1");
			AnnotateUrl url = new AnnotateUrl("listDocuments.php", params);
			url.prepare();
			// System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			request.setReadTimeout(0);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
			result.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (AnnotateUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		// generic parse and print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// convert the json string back to object
		JsonParser parser = new JsonParser();
		String json = null;
		try {
			JsonElement tree = parser.parse(content);
			json = gson.toJson(tree);
			FileWriter writer = new FileWriter("c:\\dev\\docnotes.json");
			writer.write(json);
			writer.close();
			// System.out.println("response from listusers.php\n" + json);
		} catch (JsonIOException e) {
		} catch (JsonParseException e) {
		} catch (IOException e) {
		}
		// map to AnnotateDocuments
		AnnotateDocument[][] documents = gson.fromJson(json,
				AnnotateDocument[][].class);
		return documents;

	}

	public static AnnotateDocumentNotes testNotes(Document document) {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			params.setId(document.getId());
			params.setDate(document.getDate());// doesn't work without date
			AnnotateUrl url = new AnnotateUrl("listNotes.php", params);
			url.prepare();
			// System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (AnnotateUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		// generic parse and print
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// convert the json string back to object
		JsonParser parser = new JsonParser();
		String json = null;
		try {
			JsonElement tree = parser.parse(content);
			json = gson.toJson(tree);
			FileWriter writer = new FileWriter("c:\\dev\\notes.json");
			writer.write(json);
			writer.close();
			// System.out.println("response from listNotes.php\n" + json);
		} catch (JsonIOException e) {
		} catch (JsonParseException e) {
		} catch (IOException e) {

		}
		// map to AnnotateUsers
		AnnotateDocumentNotes documentNotes = gson.fromJson(json,
				AnnotateDocumentNotes.class);
		return documentNotes;

	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
