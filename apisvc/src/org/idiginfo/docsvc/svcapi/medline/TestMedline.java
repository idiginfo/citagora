package org.idiginfo.docsvc.svcapi.medline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class to support accessing PubMed MEDLINE content
 * 
 * @author griccardi
 * 
 */

public class TestMedline {

	private static final String MEDLINE_FILE = "c:/dev/nlm_nih_gov/pmc_result(suicide_ideation_tbi).txt";

	public static void main(String[] args) {
		run();
	}

	public static void run() {
		try {
			FileReader inFile = new FileReader(MEDLINE_FILE);
			BufferedReader in = new BufferedReader(inFile);
			String line = in.readLine();
			MedlineDocumentSet documents = new MedlineDocumentSet(in);
			System.out.println("num docs: " + documents.size());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
