package org.idiginfo.annotate.webapp;

import java.util.Iterator;

import org.idiginfo.annotationmodel.Annotation;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;
import org.idiginfo.annotationmodel.Users;

public class ResponseFormatter {

	public static String toHtml(Users users) {
		StringBuffer out = new StringBuffer();
		Iterator<String> members = users.getMembers().iterator();
		while (members.hasNext()) {
			out.append("<p>").append(members.next()).append("</p>\n");
		}
		// out.append("");
		return out.toString();
	}

	public static String toHtml(Document document) {
		StringBuffer out = new StringBuffer();
		out.append("<html><body>");
		out.append("<p><b>Code: ").append(document.getId()).append("</b></p>");
		out.append("<p><b>Title: ").append(document.getTitle())
				.append("</b></p>");
		out.append("<p><b>Authors: ").append(document.getAuthors())
				.append("</b></p>");
		out.append(
				"<p><b>Number of Annotations: " + document.getNumAnnotations())
				.append("</b></p>");
		out.append("<p><b>Name of document: ").append(document.getName())
				.append("</b></p>");
		out.append(getNotesTable(document));
		out.append("</body></html>");
		return out.toString();
	}

	private static String getNotesTable(Document document) {
		Annotation[] annotations = document.getAnnotations();
		if (annotations == null)
			return "";

		StringBuffer out = new StringBuffer(
				"<table border=\"1\" valign=\"top\"><tr>");
		out.append("<th>Date </br>Click to see page</th>");
		out.append("<th>Annotator</th>");
		out.append("<th>Context</th>");
		out.append("<th>Type</th>");
		out.append("<th>Comment</th>");
		out.append("<th>Match</th>");
		for (int i = 0; i < annotations.length; i++) {
			Annotation note = annotations[i];
			out.append("<tr valign=\"top\">\n");
			out.append("<td><a href=\"").append(note.getFullPageUrl())
					.append("\" target=\"pagedetail\">");
			out.append(note.getDate()).append("</a></td>");
			out.append("<td>").append(note.getSigned()).append("</td>");
			out.append("<td>").append(note.getContext()).append("</td>");
			out.append("<td>").append(note.getType()).append("</td>");
			out.append("<td>").append(note.getNotetext()).append("</td>");
			out.append("<td>").append(note.getMatch()).append("</td>");
			out.append("</tr>\n");
		}
		out.append("</table>\n");
		return out.toString();
	}

	public static String toHtml(Documents document) {
		return null;
	}

	public static String toHtmlAnnotations(Document document) {
		return null;
	}

	static String toHtml(ServiceParams params, Object objects) {
		StringBuffer title = new StringBuffer();
		StringBuffer body = new StringBuffer();
		title.append("MSRC ").append(params.getCollection());
		if (params.method.equals(ServiceParams.METHOD_GET_USERS)) {
			Users users = (Users) objects;
			title.append("users");
			body.append(toHtml(users));
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENTS)) {
			Documents documents = (Documents) objects;
			title.append("documents for user").append(params.apiAnnotateUser);
			body.append(toHtml(documents));
		} else if (params.method.equals(ServiceParams.METHOD_GET_DOCUMENT)) {
			Document document = (Document) objects;
			title.append("document").append(params.code);
			body.append(toHtml(document));
		} else if (params.method.equals(ServiceParams.METHOD_GET_ANNOTATIONS)) {
			Document documentNotes = (Document) objects;
			title.append("notes for document").append(params.code);
			body.append(toHtmlAnnotations(documentNotes));
		} else {
			return ("unkown error");
		}
		StringBuffer out = new StringBuffer("<html><body><title>");
		out.append(title.toString());
		out.append("</title><body>");
		out.append("<h2>").append(title.toString()).append("</h2>");
		out.append(body.toString());
		out.append("</body></html>");
		return out.toString();
	}

}
