package org.idiginfo.annotateTest;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.services.AnnotateDocument;
import org.idiginfo.annotate.services.AnnotateDocumentNotes;
import org.idiginfo.annotate.services.AnnotateDocuments;
import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotate.services.AnnotateUrl;
import org.idiginfo.annotate.services.AnnotateUsers;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;

/**
 * Test of a few features of the a.nnotate service and the java package that we
 * use to access it.
 * 
 * @author griccardi
 * 
 */
public class TestInterfaceServices {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;
	static AnnotateService service = new AnnotateService();

	private static void run() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});

		Users users = testUsers();
		String selectedUser = users.getMembers().get(0);
		String code = "TZpwu9je";
		String date = "2012-06-29";
		Document document = testDocument(code, date);
		Documents documents = testDocuments(selectedUser);
		Document notes = testNotes(documents.getDocument(0));
	}

	public static AnnotateDocuments testDocuments(String documentUser) {
		System.out.println("\n** test docs for user " + documentUser + " **");
		// get all documents for a user
		AnnotateDocuments documents = service.getDocuments(documentUser);
		System.out.println("\nname of first document: "
				+ documents.getDocument(0).getName());
		System.out.println("code of first document: "
				+ documents.getDocument(0).getCode());
		System.out.println("number of documents " + documents.size());
		Document selectedDocument = documents.getDocument(1);
		System.out.println("Selected document: " + selectedDocument.getId());
		return documents;
	}

	public static Document testDocument(String code, String date) {
		System.out.println("\n** test doc (" + code + ", " + date + ") **");
		// get single document(
		Document document = service.getDocument(code, date);
		System.out.println("Owner of document: " + document.getOwner());
		System.out.println("Name of document: " + document.getName());
		return document;

	}

	public static Document testNotes(Document document) {
		String code = document.getId();
		String date = document.getDate();
		System.out.println("\n** test notes (" + code + ", " + date + ") **");
		// get notes on a document
		Document documentNotes = service.getAnnotations(code, date);// selectedDocument.getCode());
		System.out.println("Number of notes: "
				+ documentNotes.getNumAnnotations());
		System.out.println("Name of document: " + documentNotes.getName());
		return documentNotes;
	}

	public static Users testUsers() {
		System.out.println("\n** test users **");
		// get all members and annotators
		Users users = service.getUsers(null);
		System.out.println("first member: " + users.getMembers().get(0));
		System.out.println("first annotator: " + users.getAnnotaters().get(0));
		String documentUser = users.getMembers().get(7);
		System.out.println("Document user: " + documentUser);
		return users;
	}

	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
