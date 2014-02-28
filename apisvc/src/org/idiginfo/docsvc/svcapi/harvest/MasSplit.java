package org.idiginfo.docsvc.svcapi.harvest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiSplit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class to separate MAS content into individual files
 * 
 * @author griccardi
 * 
 */

public class MasSplit implements ApiSplit{

//	private static final int FIRST_FILE = 1;
//
//	private static final int MAX_FILES = 300;

	DocService service = ServiceFactory.getFactory().createService(
			ServiceFactory.COLLECTION_MAS);
	Gson gson = service.getGson();
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

	@Override
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
				JsonObject results = (JsonObject) tree.get("d");
				JsonObject publication = (JsonObject) results
						.get("Publication");
				JsonElement result = publication.get("Result");
				JsonArray entries = result.getAsJsonArray();
//				int numEntries = entries.size();
				for (JsonElement entry : entries) {
					String splitFileName = splitFilePrefix;
					JsonObject record = (JsonObject) entry;
					if (record == null)
						break;
					// find an id to use as the file name
					// try doi first
					// in some cases, SciVerse returns invalid doi strings
					// Examples include "org/{valid doi}", "http://...", "{valid
					// doi}\"
					// "jkns.2011..."
					JsonElement id = record.get("DOI");
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
						if (doi.contains("<")) {
							System.out.println("Problem character in: " + doi);
						}
						doi = doi.replace("<", "-");
						doi = doi.replace(">", "-");
						doi = doi.replace(":", "-");
						doi = doi.replace(";", "-");
					}
					if (isDoi) {
						int endIndex = doi.lastIndexOf('/');
						if (endIndex > -1) {
							String fileDirName = splitFilePrefix
									+ doi.substring(0, endIndex);
							(new File(fileDirName)).mkdirs();
						}

						splitFileName += doi + ".json";
					} else { // use MAS id
						id = record.get("ID");
						String idString = id.getAsString();
						int beginIndex = idString.indexOf(':') + 1;
						String fileDirName = splitFilePrefix + "mas/";
						(new File(fileDirName)).mkdirs();
						splitFileName = fileDirName + "mas_"
								+ idString.substring(beginIndex) + ".json";
					}
					try {
						File outFile = new File(splitFileName);
						if (outFile.exists()) {
							// already stored this item
							System.out.println("File exists: " + splitFileName);
						} else {
							FileWriter out = new FileWriter(splitFileName);
							gson.toJson(record, out);
							out.close();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				in.close();
				numFiles++;
			} catch (FileNotFoundException e) {
				System.out.println("no file: " + f.getName());
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return numFiles;
	}

}