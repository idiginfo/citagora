package org.idiginfo.annotate.exploration;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

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
	static final String XLS_FILE_NAME = "c:/dev/allnotes.xls";
	static final char SEPARATOR = ',';
	CSVWriter writer;
	Workbook wb = new HSSFWorkbook();
	Sheet sheet = wb.createSheet("annotations");
	short xlsRowNumber = 0;
	CreationHelper createHelper = wb.getCreationHelper();
	CellStyle hlink_style = wb.createCellStyle();
	Font hlink_font = wb.createFont();

	String[] headers = { "document", "title", "date", "annotator", "subject",
			"tags", "comment", "context", "pageurl" };

	public GetAllAnnotations() {
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setFont(hlink_font);
	}

	private void run() {
		String documentUser = "drupal@msrc.fsu.edu";
		System.out.println("Document user: " + documentUser);

		String selectedUser = "drupal@msrc.fsu.edu";
		Documents documents = service.getDocuments(selectedUser);
		System.out.println("number of documents " + documents.size());
		try {
			writer = new CSVWriter(new FileWriter(CSV_FILE_NAME), SEPARATOR);
			writeHeader(headers);
			getAllNotes(documents);
			writer.close();
			FileOutputStream outFile = new FileOutputStream(XLS_FILE_NAME);
			wb.write(outFile);
			outFile.close();
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
		xlsAddRow(headers, null);
	}

	private void xlsAddRow(String[] values, String url) {
		Row row = sheet.createRow(xlsRowNumber);
		for (int i = 0; i < values.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(values[i]);
			if (i == values.length - 1) {// last cell
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
		// String[] headers = { "document", "title", "date", "annotator",
		// "subject", "type", "comment", "context", "pageurl" };

		String[] fields = new String[9];
		fields[0] = document.getId();
		fields[1] = document.getTitle();
		fields[2] = note.getDate();
		fields[3] = note.getSigned();
		fields[4] = note.getSubject();
		fields[5] = note.getTags();
		fields[6] = note.getNotetext();
		fields[7] = note.getContext();
		String url = note.getFullPageUrl();
		fields[8] = url;
		// line[7] = "<a href=\"" + url + "\">" + url + "</a>";
		writer.writeNext(fields);
		xlsAddRow(fields, url);
	}

	public static void main(String[] args) {
		new GetAllAnnotations().run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
