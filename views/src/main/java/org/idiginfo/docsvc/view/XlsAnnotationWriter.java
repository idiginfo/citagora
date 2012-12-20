package org.idiginfo.docsvc.view;

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
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

//TODO make this into a service with parameters
// getAnnotations(
/**
 * Generates files containing extracts of the annotate.msrc.fsu.edu notes
 * database
 * 
 * @author griccardi
 * 
 */
public class XlsAnnotationWriter {

	public XlsAnnotationWriter() {
		createHelper = wb.getCreationHelper();
		hlink_style = wb.createCellStyle();
		hlink_font = wb.createFont();
		hlink_font.setUnderline(Font.U_SINGLE);
		hlink_font.setColor(IndexedColors.BLUE.getIndex());
		hlink_style.setFont(hlink_font);
	}

	Workbook wb = new HSSFWorkbook();
	Sheet sheet = wb.createSheet("annotations");
	short xlsRowNumber = 0;
	CreationHelper createHelper = null;
	CellStyle hlink_style = null;
	Font hlink_font = null;
	// Context text file output parameters

	String[] headers = { "title", "tags", "context", "subject", "url" };

	public void addHeaders(String[] headers) {
		// initialize output objects
		addRow(headers, null);
	}

	public Workbook getWorkbook() {
		return wb;
	}

	public void addAnnotations(Document annotations) {
		int numAnnotations = annotations.getNumAnnotations();
		for (int j = 0; j < numAnnotations; j++) {
			Annotation note = annotations.getAnnotation(j);
			String context = note.getContext();
			if (context.length() > 1500) {
				// printLine(note);
				System.err.println("Note " + note.getGid() + " note " + j
						+ " is long " + context.length());
			} else {
				addRow(annotations, note);
			}
		}
	}

	public void addRow(String[] values, String url) {
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

	private void addRow(Document document, Annotation note) {
		// String[] headers = { "title", "tags", "context", "url"};

		String[] fields = new String[9];
		fields[0] = document.getTitle();
		fields[1] = note.getTags();
		fields[2] = note.getContext();
		fields[3] = note.getSubject();
		String url = note.getFullPageUrl();
		fields[4] = url;
		addRow(fields, url);
	}

}
