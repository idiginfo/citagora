package org.idiginfo.docsvc.controller.harvest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.google.api.client.http.HttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class to separate SciVerse content into individual files 
 * 
 * @author griccardi
 * 
 */

public class SciVerseSplit {
	final static String IN_FILE_PREFIX = SciVerseHarvest.FILE_PREFIX;

	final static String SPLIT_FILE_PREFIX = "c:/dev/split/sciverse/";

	private static final int FIRST_FILE = 0;

	private static final int MAX_FILES = 300;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static JsonParser parser = new JsonParser();

	private void run(String[] args) {
		int numFiles;
		numFiles = splitFiles(IN_FILE_PREFIX, SPLIT_FILE_PREFIX);
		System.out.println("Number of files processed: " + numFiles);
	}

	public String format(String content) {
		String formattedContent;
		try {
			JsonElement tree = parser.parse(content);
			formattedContent = gson.toJson(tree);
			return formattedContent;
		} catch (JsonParseException e) {
		}
		return null;
	}

	public int splitFiles(String inFilePrefix, String splitFilePrefix) {
		int numFiles = 0;
		for (int fileNum = FIRST_FILE; fileNum < MAX_FILES; fileNum++) {
			String inFileName = inFilePrefix + String.format("%05d", fileNum)
					+ ".json";
			System.out.println("getting file " + inFileName);
			try {
				FileReader in = new FileReader(inFileName);
				JsonObject tree = (JsonObject) parser.parse(in);
				// get list of records
				JsonObject results = (JsonObject) tree.get("search-results");
				JsonElement entry = results.get("entry");
				JsonArray entries = entry.getAsJsonArray();
				for (int i = 0; i < entries.size(); i++) {
					String splitFileName = splitFilePrefix;
					JsonObject record = (JsonObject) entries.get(i);
					if (record == null)
						break;
					// find an id to use as the file name
					// try doi first
					// in some cases, SciVerse returns invalid doi strings
					// Examples include "org/{valid doi}", "http://...", "{valid
					// doi}\"
					// "jkns.2011..."
					JsonElement id = record.get("prism:doi");
					boolean isDoi = false;
					String doi = null;
					if (id != null) {
						// look at doi string and fix it if necessary
						doi = id.getAsString();
						if (doi.startsWith("10.")) {
							isDoi = true;
						} else if (doi.startsWith("org")) {
							doi = doi.substring(4);
							isDoi = true;
						}
						// fix double slashes and trailing backslashes
						doi = doi.replaceAll("//", "/");
						if (doi.contains("\\")) {
							System.out.println(doi);
						}
						doi = doi.replace("\\", "");
					}
					if (isDoi) {
						int endIndex = doi.lastIndexOf('/');
						if (endIndex > -1) {
							String fileDirName = splitFilePrefix
									+ doi.substring(0, endIndex);
							(new File(fileDirName)).mkdirs();
						}

						splitFileName += doi + ".json";
					} else { // use SCOPUS id
						id = record.get("dc:identifier");
						String idString = id.getAsString();
						int beginIndex = idString.indexOf(':') + 1;
						String fileDirName = splitFilePrefix + "scopus/";
						(new File(fileDirName)).mkdirs();
						splitFileName = fileDirName + "scopus_"
								+ idString.substring(beginIndex) + ".json";
					}
					File outFile = new File(splitFileName);
					if (outFile.exists()) {
						// already stored this item
						System.out.println("File exists: " + splitFileName);
					} else {
						FileWriter out = new FileWriter(splitFileName);
						gson.toJson(record, out);
						out.close();
					}
				}
				in.close();
				numFiles++;
			} catch (FileNotFoundException e) {
				System.out.println("no file: " + inFileName);
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return numFiles;
	}

	public static void main(String[] args) {
		SciVerseSplit splitter = new SciVerseSplit();
		splitter.run(args);
		return;
	}

	public static void enableLogging() {
		Logger logger = Logger.getLogger(HttpTransport.class.getName());
		logger.setLevel(Level.CONFIG);
		logger.addHandler(new Handler() {

			@Override
			public void close() throws SecurityException {
			}

			@Override
			public void flush() {
			}

			@Override
			public void publish(LogRecord record) {
				// default ConsoleHandler will print >= INFO to System.err
				if (record.getLevel().intValue() < Level.INFO.intValue()) {
					System.out.println(record.getMessage());
				}
			}
		});
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
