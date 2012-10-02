package org.idiginfo.docsvc.svcapi.annotatetest;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocument;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocumentNotes;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocuments;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateUsers;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;

/**
 * Test of a few features of the a.nnotate service and the java package that we
 * use to access it.
 * 
 * @author griccardi
 * 
 */
public class TestAnnotateServices {

	static AnnotateService service = new AnnotateService();

	@SuppressWarnings("unused")
	private static void run() {

		AnnotateUsers users = testUsers();
		String selectedUser = "drupal@msrc.fsu.edu"; // users.getMembers().get(0);
		String code = "TZpwu9je";
		String date = "2012-06-29";
		// AnnotateDocumentNotes document = testDocument(code, date);
		AnnotateDocuments documents = testDocuments(selectedUser);
		// AnnotateDocumentNotes notes = testNotes(documents.getDocument(0));
	}

	public static AnnotateDocuments testDocuments(String documentUser) {

		// get all documents for a user
		AnnotateDocuments documents = service.getDocuments(documentUser);
		System.out.println("name of first document: "
				+ documents.getDocument(0).getName());
		System.out.println("code of first document: "
				+ documents.getDocument(0).getId());
		System.out.println("number of documents " + documents.size());
		Document selectedDocument = documents.getDocument(1);
		System.out.println("Selected document: " + selectedDocument.getId());
		return documents;
	}

	public static AnnotateDocumentNotes testDocument(String code, String date) {
		// get single document
		AnnotateDocumentNotes document = service.getDocument(code, date);
		System.out.println("Owner of document: " + document.getOwner());
		System.out.println("Name of document: " + document.getName());
		return document;

	}

	public static AnnotateDocumentNotes testNotes(AnnotateDocument document) {
		// get notes on a document
		AnnotateDocumentNotes documentNotes = service.getNotes(document);// selectedDocument.getCode());
		System.out.println("Number of notes: " + documentNotes.notes.length);
		System.out.println("Name of document: " + documentNotes.meta.getName());
		return documentNotes;
	}

	public static AnnotateUsers testUsers() {
		// get all members and annotators
		AnnotateUsers users = service.getUsers(null);
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
