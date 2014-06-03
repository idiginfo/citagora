package org.idiginfo.docsvc.svcapi.harvest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Generates files containing extracts of the annotate.msrc.fsu.edu notes
 * database
 * 
 * @author griccardi
 * 
 */
public class GetAllAnnotations {
	
	//TODO split this class into controller and service. Right now, it gets and prints annotations.

//	public static void main(String[] args) {
//		new GetAllAnnotations().run();
//		return;
//	}
//
//	XlsAnnotationWriter annotationWriter = new XlsAnnotationWriter();
//	ServiceFactory serviceFactory = ServiceFactory.getFactory();
//	DocService service = serviceFactory
//			.createService(ServiceFactory.COLLECTION_ANNOTATE);
//	ApiParams params = serviceFactory
//			.createApiParams(ServiceFactory.COLLECTION_ANNOTATE);
//
//	// output parameters
//	// static final String OUTPUT_PREFIX =
//	// "c:/dev/annotateSampleFiles/some_notes";
//	static final String OUTPUT_PREFIX = "c:/dev/annotateSampleFiles/proposal_notes";
//	// CSV output parameters
//	static final String CSV_FILE_NAME = OUTPUT_PREFIX + ".csv";
//	static final char SEPARATOR = ',';
//	CSVWriter writer;
//	// XLS output parameters
//	static final String XLS_FILE_NAME = OUTPUT_PREFIX + ".xls";
//	// Context text file output parameters
//	static final String CONTEXT_FILE_NAME = OUTPUT_PREFIX + ".txt";
//	PrintWriter contextPrinter = null;
//
//	String[] headers = { "title", "subject", "tags", "note text", "context",
//			"url" };
//
//	public GetAllAnnotations() {
//	}
//
//	private void run() {
//		String documentUser = "diane.leiva@cci.fsu.edu";
//		// String documentUser = "drupal@msrc.fsu.edu";
//		System.out.println("Document user: " + documentUser);
//
//		String selectedUser = documentUser;
//		ApiParams params = ServiceFactory.getFactory().createApiParams(
//				ServiceFactory.COLLECTION_ANNOTATE);
//		params.setApiUser(selectedUser);
//		List<? extends Document> documents = service.getDocuments(params);
//		System.out.println("number of documents " + documents.size());
//		try {
//			// create output objects
//			// CSV
//			writer = new CSVWriter(new FileWriter(CSV_FILE_NAME), SEPARATOR);
//			// XLS
//			// Context text
//			contextPrinter = new PrintWriter(new File(CONTEXT_FILE_NAME));
//
//			// initialize output objects
//			writeHeader(headers);
//
//			getAllNotes(documents);
//
//			// finalize output objects
//			writer.close();
//			FileOutputStream outFile = new FileOutputStream(XLS_FILE_NAME);
//			annotationWriter.getWorkbook().write(outFile);
//			outFile.close();
//			contextPrinter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Gets all of the notes for each document in documents Writes the notes
//	 * information to various files
//	 * 
//	 * @param documents
//	 */
//	private void getAllNotes(List<? extends Document> documents) {
//		int numDocsWithNotes = 0;
//		int numNotesProcessed = 0;
//		for (int i = 0; i < documents.size(); i++) {
//			Document document = documents.get(i);
//			Document annotations = service.getAnnotations(document);
//			if (annotations == null)
//				continue;
//			// print all annotations
//			int numAnnotations = annotations.getNumAnnotations();
//			if (numAnnotations > 0)
//				numDocsWithNotes++;
//			for (int j = 0; j < numAnnotations; j++) {
//				Annotation note = annotations.getAnnotation(j);
//				String context = note.getContext();
//				numNotesProcessed++;
//				if (context.length() > 1500) {
//					// printLine(note);
//					System.err
//							.println("Note " + note.getGid() + " document " + i
//									+ " note " + j + " is long "
//									+ context.length());
//				} else {
//					writeLine(document, note);
//				}
//			}
//		}
//		System.out.println("Num docs: " + numDocsWithNotes + " num notes: "
//				+ numNotesProcessed);
//	}
//
//	private void writeHeader(String[] headers) {
//		writer.writeNext(headers);
//		annotationWriter.addHeaders(headers);
//	}
//
//	private void writeLine(Document document, Annotation note) {
//		// String[] headers = { "title", "subject", "context", "url"};
//		// if (note.getTags() != null && note.getTags().length() > 0) return;
//		String[] fields = new String[9];
//		fields[0] = document.getTitle();
//		fields[1] = note.getSubject();
//		fields[2] = note.getTags();
//		fields[3] = note.getNotetext();
//		fields[4] = note.getContext();
//		String url = note.getFullPageUrl();
//		fields[5] = url;
//		writer.writeNext(fields);
//		annotationWriter.addRow(fields, url);
//		contextPrinter.println(note.getContext());
//	}
}
