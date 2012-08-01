package org.idiginfo.annotate.webapp;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.idiginfo.annotationmodel.*;

public class ResponseFormatter {

	public static String format(Users users) {
		StringBuffer out = new StringBuffer();
		Iterator<String> members = users.getMembers().iterator();
		while (members.hasNext()) {
			out.append("<p>").append(members.next()).append("</p>\n");
		}
		// out.append("");
		return out.toString();
	}

	public static String format(Document document) {
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
		StringBuffer out = new StringBuffer(
				"<table border=\"1\" valign=\"top\"><tr>");
		out.append("<th>Date </br>Click to see page</th>");
		out.append("<th>Annotator</th>");
		out.append("<th>Context</th>");
		out.append("<th>Type</th>");
		out.append("<th>Comment</th>");
		out.append("<th>Match</th>");
		Annotation[] annotations = document.getAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			Annotation note = annotations[i];
			out.append("<tr valign=\"top\">\n");
			out.append("<td><a href=\"").append(note.getFullPageUrl()).append("\" target=\"pagedetail\">");
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

	public static String format(Documents document) {
		return null;
	}

	public static String formatAnnotations(Document document) {
		return null;
	}

}
