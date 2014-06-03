package org.idiginfo.docsvc.svcapi.harvest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class to separate Springer content into individual files
 * 
 * @author griccardi
 * 
 */

public class SpringerSplit {
	

//	private static final int FIRST_FILE = 0;
//
//	private static final int MAX_FILES = 300;

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	static JsonParser parser = new JsonParser();

	

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
		File baseDirectory = new File(inFilePrefix);
		File[] files = baseDirectory.listFiles();
		System.out.println("number of files: " + files.length);
		for (File f : files) {
			if (f.isDirectory()) {
				continue;
			}
			System.out.println("getting file " + f.getName());

			try {
				FileReader in = new FileReader(f);
				JsonObject tree = (JsonObject) parser.parse(in);
				// get list of records

				JsonArray results = (JsonArray) tree.get("records");
				for (JsonElement result : results) {
					JsonObject record = (JsonObject) result;
					String splitFileName = splitFilePrefix;
					if (record == null)
						break;
					// find an id to use as the file name
					// try doi first
					// in some cases, SciVerse returns invalid doi strings
					// Examples include "org/{valid doi}", "http://...", "{valid
					// doi}\"
					// "jkns.2011..."
					JsonElement id = record.get("doi");
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
						} else if (doi.startsWith("DOItmp")) {
							isDoi = true;
						}
						// fix double slashes and trailing backslashes
						doi = doi.replaceAll("//", "/");
						if (doi.contains("\\")) {
							System.out.println(doi);
						}
						doi = doi.replace("\\", "");
						doi = doi.replace(":", "-");
						doi = doi.replace("<", "-");
						doi = doi.replace(">", "-");
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
						// TODO revise for Springer
						System.out.println("no doi for item");
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
						try {
							FileWriter out = new FileWriter(splitFileName);
							gson.toJson(record, out);
							out.close();
						} catch (FileNotFoundException e) {
							System.out.println("problem creating file "
									+ splitFileName);
							e.printStackTrace();
							return numFiles;
						}
					}
				}
				in.close();
				numFiles++;
			} catch (FileNotFoundException e) {
				System.out.println("no file: " + f.getName());
				e.printStackTrace();
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return numFiles;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
