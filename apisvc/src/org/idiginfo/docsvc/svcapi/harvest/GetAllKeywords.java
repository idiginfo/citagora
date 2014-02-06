package org.idiginfo.docsvc.svcapi.harvest;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;
import org.idiginfo.docsvc.svcapi.mas.model.MasKeyword;
import org.idiginfo.docsvc.svcapi.mas.utility.KeywordUtilities;
//import org.idiginfo.docsvc.view.XlsAnnotationWriter;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Generates files containing extracts of the annotate.msrc.fsu.edu notes
 * database
 * 
 * @author griccardi
 * 
 */
public class GetAllKeywords {

//	public static void main(String[] args) {
//		new GetAllKeywords().run(args);
//		return;
//	}
//
//	XlsAnnotationWriter annotationWriter = new XlsAnnotationWriter();
//
//	ServiceFactory serviceFactory = ServiceFactory.getFactory();
//	DocService service = serviceFactory
//			.createService(ServiceFactory.COLLECTION_ANNOTATE);
//	ApiParams params = serviceFactory
//			.createApiParams(ServiceFactory.COLLECTION_ANNOTATE);
//	// output parameters
//
//	String outputPrefix = "c:/dev/api samples/mas_keywords";
//	// CSV output parameters
//	String csvFilename = outputPrefix + ".csv";
//	static final char SEPARATOR = ',';
//	CSVWriter writer;
//	// XLS output parameters
//	String xlsFilename = outputPrefix + ".xls";
//
//	String[] headers = { "id", "name", "citationCount", "publicationCount" };
//
//	public GetAllKeywords() {
//	}
//
//	private void run(String[] args) {
//		// Integer publicationId = 3601711;
//		String keyword = "suicide";
//		if (args != null && args.length > 0) {
//			// publicationId = Integer.valueOf(args[0]);
//			keyword = args[0];
//			outputPrefix += " " + keyword;
//			xlsFilename = outputPrefix + ".xls";
//			csvFilename = outputPrefix + ".csv";
//		}
//		KeywordUtilities keywordUtilities = new KeywordUtilities();
//		// System.out.println("Get keywords for publication: " +publicationId);
//		// keywordUtilities.fetchAllKeywords(publicationId);
//		System.out.println("Get keywords for keyword: " + keyword);
//		keywordUtilities.fetchAllKeywords(keyword);
//		try {
//			// create output objects
//			// CSV
//			writer = new CSVWriter(new FileWriter(csvFilename), SEPARATOR);
//
//			// initialize output objects
//			writeHeader(headers);
//
//			writeKeywords(keywordUtilities);
//
//			// finalize output objects
//			writer.close();
//			FileOutputStream outFile = new FileOutputStream(xlsFilename);
//			annotationWriter.getWorkbook().write(outFile);
//			outFile.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Gets all of the notes for each document in documents Writes the notes
//	 * information to various files
//	 * 
//	 * @param keywordUtilities
//	 * 
//	 * @param documents
//	 */
//	private void writeKeywords(KeywordUtilities keywordUtilities) {
//		// int numDocsWithNotes = 0;
//		// int numNotesProcessed = 0;
//		Map<Long, MasKeyword> keywordMap = keywordUtilities.getKeywordMap();
//		Collection<MasKeyword> keywordObjects = keywordMap.values();
//		Iterator<MasKeyword> keyIterator = keywordObjects.iterator();
//		while (keyIterator.hasNext()) {
//			MasKeyword keywordObject = keyIterator.next();
//			writeLine(keywordObject);
//		}
//		System.out.println("Num keywords: " + keywordObjects.size());
//	}
//
//	private void writeHeader(String[] headers) {
//		writer.writeNext(headers);
//		annotationWriter.addHeaders(headers);
//	}
//
//	private void writeLine(MasKeyword keywordObject) {
//		// String[] headers = { "id", "name", "citationCount",
//		// "publicationCount"};
//		String[] fields = new String[9];
//		fields[0] = Long.toString(keywordObject.getId());
//		fields[1] = keywordObject.getName();
//		fields[2] = Long.toString(keywordObject.getCitationCount());
//		fields[3] = Long.toString(keywordObject.getPublicationCount());
//		writer.writeNext(fields);
//		annotationWriter.addRow(fields, null);
//		// contextPrinter.println(note.getContext());
//	}

}
