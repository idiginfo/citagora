package org.idiginfo.docsvc.svcapi.annotatetest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docsvc.model.model.Annotation;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Test of a few features of the a.nnotate service and the java package that we
 * use to access it.
 * 
 * @author griccardi
 * 
 */
public class TestGetNotes {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;
	static AnnotateService service = new AnnotateService();

	public static void main(String[] args) throws FileNotFoundException {
		run();
		return;
	}

	private static void run() throws FileNotFoundException {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});

		String code = "TZpwu9je";
		String date = "2012-06-29";
		testNotes(code, date);
	}

	public static Document testNotes(String code, String date)
			throws FileNotFoundException {
		// System.out.println("\n** test notes (" + code + ", " + date +
		// ") **");
		// get notes on a document
		Document documentNotes = service.getAnnotations(code, date);
		File outFile = new File("c:/dev/notes.html");
		PrintWriter out = new PrintWriter(outFile);
		out.println("<html><body>");
		out.println("<p><b>Code: " + documentNotes.getId() + "</b></p>");
		out.println("<p><b>Title: " + documentNotes.getTitle() + "</b></p>");
		out.println("<p><b>Authors: " + documentNotes.getAuthors() + "</b></p>");
		out.println("<p><b>Number of Annotations: "
				+ documentNotes.getNumAnnotations() + "</b></p>");
		out.println("<p><b>Name of document: " + documentNotes.getName()
				+ "</b></p>");
		out.println(getNotesTable(documentNotes));
		out.println("</body></html>");
		out.close();
		// outFile.
		return documentNotes;
	}

	public static String getNotesTable(Document document) {
		StringBuffer out = new StringBuffer(
				"<table border=\"1\" valign=\"top\"><tr>");
		out.append("<th>Annotator</th>");
		out.append("<th>Context</th>");
		out.append("<th>Type</th>");
		out.append("<th>Comment</th>");
		out.append("<th>Match</th>");
		Annotation[] annotations = document.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation note = annotations[i];
			out.append("<tr valign=\"top\">\n");
			out.append("<td>" + note.getSigned() + "</td>");
			out.append("<td>" + note.getContext() + "</td>");
			out.append("<td>" + note.getType() + "</td>");
			out.append("<td>" + note.getNotetext() + "</td>");
			out.append("<td>" + note.getMatch() + "</td>");
			out.append("</tr>\n");
		}
		out.append("</table>\n");
		return out.toString();
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
