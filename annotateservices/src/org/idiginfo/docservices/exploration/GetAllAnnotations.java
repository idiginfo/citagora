package org.idiginfo.docservices.exploration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.idiginfo.docservices.annotate.AnnotateService;
import org.idiginfo.docservices.model.Annotation;
import org.idiginfo.docservices.model.DocService;
import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.model.Documents;

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

	DocService service = new AnnotateService();
	// output parameters
	static final String OUTPUT_PREFIX = "c:/dev/annotateSampleFiles/proposal_notes";
	// CSV output parameters
	static final String CSV_FILE_NAME = OUTPUT_PREFIX + ".csv";
	static final char SEPARATOR = ',';
	CSVWriter writer;
	// XLS output parameters
	static final String XLS_FILE_NAME = OUTPUT_PREFIX + "s.xls";
	Workbook wb = null;
	Sheet sheet = null;
	short xlsRowNumber = 0;
	CreationHelper createHelper = null;
	CellStyle hlink_style = null;
	Font hlink_font = null;
	// Context text file output parameters
	static final String CONTEXT_FILE_NAME = OUTPUT_PREFIX + ".txt";
	PrintWriter contextPrinter = null;

	String[] headers = { "title", "tags", "context", "url" };

	public GetAllAnnotations() {
	}

	private void run() {
		String documentUser = "dleiva@fsu.edu";
		// String documentUser = "drupal@msrc.fsu.edu";
		System.out.println("Document user: " + documentUser);

		String selectedUser =documentUser;
		Documents documents = service.getDocuments(selectedUser);
		System.out.println("number of documents " + documents.size());
		try {
			// create output objects
			// CSV
			writer = new CSVWriter(new FileWriter(CSV_FILE_NAME), SEPARATOR);
			// XLS
			wb = new HSSFWorkbook();
			sheet = wb.createSheet("annotations");
			createHelper = wb.getCreationHelper();
			hlink_style = wb.createCellStyle();
			hlink_font = wb.createFont();
			hlink_font.setUnderline(Font.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			// Context text
			contextPrinter = new PrintWriter(new File(CONTEXT_FILE_NAME));

			// initialize output objects
			writeHeader(headers);

			getAllNotes(documents);

			// finalize output objects
			writer.close();
			FileOutputStream outFile = new FileOutputStream(XLS_FILE_NAME);
			wb.write(outFile);
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
			if (numDocsWithNotes > 2)
				break;
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
		xlsAddRow(headers, null);
	}

	private void xlsAddRow(String[] values, String url) {
		Row row = sheet.createRow(xlsRowNumber);
		for (int i = 0; i < values.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(values[i]);
			if (i == 0) {// first cell
				if (url != null) {
					Hyperlink link = createHelper
							.createHyperlink(Hyperlink.LINK_URL);
					link.setAddress(url);
					cell.setHyperlink(link);
					cell.setCellStyle(hlink_style);
				}
			}
		}
		xlsRowNumber++;
	}

	private void writeLine(Document document, Annotation note) {
		// String[] headers = { "title", "tags", "context", "url"};

		String[] fields = new String[9];
		fields[0] = document.getTitle();
		fields[1] = note.getTags();
		fields[2] = note.getContext();
		String url = note.getFullPageUrl();
		fields[3] = url;
		writer.writeNext(fields);
		xlsAddRow(fields, url);
		contextPrinter.println(note.getContext());
	}

}
