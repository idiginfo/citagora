package org.idiginfo.docsvc.view;

import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Users;

/**
 * Demonstration class to create an html page from a collection of documents
 * and/or annotations
 * 
 * @author griccardi
 * 
 */
public class HtmlDocumentWriter {
	static final String CHARSET = "UTF-8";

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
		if (document == null)
			return "";
		StringBuffer out = new StringBuffer();
		out.append("<p><b>Identifier:</b> ").append(document.getId())
				.append("</p>");
		if (document.getDate() != null) {
			out.append("<p><b>Date:</b> ").append(document.getDate())
					.append("</p>");
		}
		out.append("<p><b>Title:</b> ").append(document.getTitle())
				.append("</p>");
		out.append("<p><b>Authors:</b> ").append(document.getAuthors())
				.append("</p>");
		out.append(
				"<p><b>Number of Annotations:</b> "
						+ document.getNumAnnotations()).append("</p>");
		out.append("<p><b>Name of document:</b> ").append(document.getName())
				.append("</p>");
		out.append(getNotesTable(document));
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

	public static String toHtml(List<Document> documents) {
		if (documents == null)
			return null;
		StringBuffer out = new StringBuffer();
		Iterator<Document> documentIterator = documents.iterator();
		while (documentIterator.hasNext()) {
			out.append("<div>");
			out.append(toHtml(documentIterator.next()));
			out.append("</div>");
		}
		return out.toString();
	}

	public static String toHtmlAnnotations(Document document) {
		return null;
	}

	public static String toHtml(ApiParams params, Object objects) {
		StringBuffer title = new StringBuffer();
		StringBuffer body = new StringBuffer();
		title.append("MSRC ").append(params.getCollection());
		if (objects == null)
			return "No objects returned from service";
		if (objects instanceof Users) {
			Users users = (Users) objects;
			title.append(" users ");
			body.append(toHtml(users));
		} else if (objects instanceof List<?>) {
			List<Document> documents = (List<Document>) objects;
			title.append(" documents ");
			if (params.getKeyword() != null) {
				title.append(" with keyword \"").append(params.getKeyword())
						.append("\"");
			} else {
				title.append("for user ").append(params.getOwner());
			}
			body.append(toHtml(documents));
		} else if (objects instanceof Document) {
			Document document = (Document) objects;
			title.append(" document ").append(params.getId());
			body.append(toHtml(document));
		} else if (objects instanceof Document) {
			Document documentNotes = (Document) objects;
			title.append(" notes for document ").append(params.getId());
			body.append(toHtmlAnnotations(documentNotes));
		} else {
			return ("unkown object type");
		}
		StringBuffer out = new StringBuffer("<html><head><meta charset=\"");
		out.append(CHARSET).append("\"><body><title>");
		out.append(title.toString());
		out.append("</title><body>");
		out.append("<h2>").append(title.toString()).append("</h2>");
		out.append(body.toString());
		out.append("</body></html>");
		return out.toString();
	}
}
