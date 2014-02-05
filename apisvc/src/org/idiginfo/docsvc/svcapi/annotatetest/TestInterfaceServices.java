package org.idiginfo.docsvc.svcapi.annotatetest;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Users;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;

/**
 * Test of a few features of the a.nnotate service and the java package that we
 * use to access it.
 * 
 * @author griccardi
 * 
 */
public class TestInterfaceServices {

    static AnnotateService service = new AnnotateService();

    @SuppressWarnings("unused")
    private static void run() {

	Users users = testUsers();
	String selectedUser = users.getMembers().get(0);
	String code = "TZpwu9je";
	String date = "2012-06-29";
	Document document = testDocument(code, date);
	List<Document> documents = testDocuments(selectedUser);
	Document notes = testNotes(documents.get(0));
    }

    public static List<Document> testDocuments(String documentUser) {
	System.out.println("\n** test docs for user " + documentUser + " **");
	// get all documents for a user
	List<Document> documents = service.getDocuments(documentUser);
	System.out.println("\nname of first document: "
		+ documents.get(0).getName());

	System.out.println("code of first document: "
		+ documents.get(0).getId());
	System.out.println("number of documents " + documents.size());
	Document selectedDocument = documents.get(1);
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
	Document documentNotes = service.getAnnotations(code, date);
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
