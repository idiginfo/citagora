package org.idiginfo.annotate.exploration;

/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.FileWriter;
import java.io.IOException;

import org.idiginfo.annotate.services.AnnotateService;
import org.idiginfo.annotationmodel.Annotation;
import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;

import au.com.bytecode.opencsv.CSVWriter;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

/**
 * Test of a few features of the a.nnotate service and the java package that we
 * use to access it.
 * 
 * @author griccardi
 * 
 */
public class GetAllAnnotations {

	HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	HttpRequestFactory requestFactory;
	AnnotateService service = new AnnotateService();
	static final String CSV_FILE_NAME = "c:/dev/allnotes.csv";
	static final char SEPARATOR = ',';
	CSVWriter writer;
	String[] headers = { "document", "title", "date", "annotator", "tags",
			"comment", "context", "pageurl" };

	private void run() {
		String documentUser = "drupal@msrc.fsu.edu";
		System.out.println("Document user: " + documentUser);

		String selectedUser = "drupal@msrc.fsu.edu";
		Documents documents = service.getDocuments(selectedUser);
		System.out.println("number of documents" + documents.size());
		try {
			writer = new CSVWriter(new FileWriter(CSV_FILE_NAME), SEPARATOR);
			writeHeader(headers);
			getAllNotes(documents);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getAllNotes(Documents documents) {
		int numDocs = 0;
		int numNotes = 0;
		for (int i = 0; i < documents.size(); i++) {
			Document document = documents.getDocument(i);
			Document annotations = service.getAnnotations(document);
			if (annotations == null) continue;
			// print all annotations
			int numAnnotations = annotations.getNumAnnotations();
			if (numAnnotations > 0) numDocs++;
			for (int j = 0; j < numAnnotations; j++) {
				Annotation note = annotations.getAnnotation(j);
				String context = note.getContext();
				numNotes++;
				if (context.length() > 1500) {
					// printLine(note);
					System.err
							.println("Note " + note.getGid() + " document " + i
									+ " note " + j + " is long "
									+ context.length());
				} else {
					writeLine(document, note);
				}
			}
		}
		System.out.println("Num docs: " + numDocs + " num notes: " + numNotes);
	}

	private void writeHeader(String[] headers) {
		writer.writeNext(headers);
	}

	private void writeLine(Document document, Annotation note) {
		// String[] headers = { "document", "title", "date", "annotator",
		// "type", "comment", "context", "pageurl" };

		String[] line = new String[9];
		line[0] = document.getId();
		line[1] = document.getTitle();
		line[2] = note.getDate();
		line[3] = note.getSigned();
		line[4] = note.getTags();
		line[5] = note.getNotetext();
		line[6] = note.getContext();
		String url = note.getFullPageUrl();
		line[7] = url;
		// line[7] = "<a href=\"" + url + "\">" + url + "</a>";
		writer.writeNext(line);
	}

	public static void main(String[] args) {
		new GetAllAnnotations().run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
