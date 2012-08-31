package org.idiginfo.docsvc.controller.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docsvc.model.model.Annotation;
import org.idiginfo.docsvc.model.model.DocService;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateService;
import org.idiginfo.docsvc.view.XlsAnnotationWriter;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Generates files containing extracts of the annotate.msrc.fsu.edu notes
 * database
 * 
 * @author griccardi
 * 
 */
public class GetAllAnnotations {

	public static void main(String[] args) {
		new GetAllAnnotations().run();
		return;
	}

	XlsAnnotationWriter annotationWriter = new XlsAnnotationWriter();

	DocService service = new AnnotateService();
	// output parameters
	static final String OUTPUT_PREFIX = "c:/dev/annotateSampleFiles/proposal_notes";
	// CSV output parameters
	static final String CSV_FILE_NAME = OUTPUT_PREFIX + ".csv";
	static final char SEPARATOR = ',';
	CSVWriter writer;
	// XLS output parameters
	static final String XLS_FILE_NAME = OUTPUT_PREFIX + ".xls";
	// Context text file output parameters
	static final String CONTEXT_FILE_NAME = OUTPUT_PREFIX + ".txt";
	PrintWriter contextPrinter = null;

	String[] headers = { "title", "subject", "context", "url" };

	public GetAllAnnotations() {
	}

	private void run() {
		String documentUser = "diane.leiva@cci.fsu.edu";
		// String documentUser = "drupal@msrc.fsu.edu";
		System.out.println("Document user: " + documentUser);

		String selectedUser = documentUser;
		Documents documents = service.getDocuments(selectedUser);
		System.out.println("number of documents " + documents.size());
		try {
			// create output objects
			// CSV
			writer = new CSVWriter(new FileWriter(CSV_FILE_NAME), SEPARATOR);
			// XLS
			// Context text
			contextPrinter = new PrintWriter(new File(CONTEXT_FILE_NAME));

			// initialize output objects
			writeHeader(headers);

			getAllNotes(documents);

			// finalize output objects
			writer.close();
			FileOutputStream outFile = new FileOutputStream(XLS_FILE_NAME);
			annotationWriter.getWorkbook().write(outFile);
			outFile.close();
			contextPrinter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets all of the notes for each document in documents Writes the notes
	 * information to various files
	 * 
	 * @param documents
	 */
	private void getAllNotes(Documents documents) {
		int numDocsWithNotes = 0;
		int numNotesProcessed = 0;
		for (int i = 0; i < documents.size(); i++) {
			Document document = documents.getDocument(i);
			Document annotations = service.getAnnotations(document);
			if (annotations == null)
				continue;
			// print all annotations
			int numAnnotations = annotations.getNumAnnotations();
			if (numAnnotations > 0)
				numDocsWithNotes++;
			for (int j = 0; j < numAnnotations; j++) {
				Annotation note = annotations.getAnnotation(j);
				String context = note.getContext();
				numNotesProcessed++;
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
		System.out.println("Num docs: " + numDocsWithNotes + " num notes: "
				+ numNotesProcessed);
	}

	private void writeHeader(String[] headers) {
		writer.writeNext(headers);
		annotationWriter.addHeaders(headers);
	}

	private void writeLine(Document document, Annotation note) {
		// String[] headers = { "title", "subject", "context", "url"};
		if (note.getTags() != null && note.getTags().length() > 0)
			return;
		String[] fields = new String[9];
		fields[0] = document.getTitle();
		fields[1] = note.getSubject();
		fields[2] = note.getContext();
		String url = note.getFullPageUrl();
		fields[3] = url;
		writer.writeNext(fields);
		annotationWriter.addRow(fields, url);
		contextPrinter.println(note.getContext());
	}
}
