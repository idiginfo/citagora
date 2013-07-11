package org.idiginfo.docsvc.controller.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.idiginfo.docsvc.svcapi.sciverse.SciVerseApiParams;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseResult;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseService;

import com.google.api.client.http.HttpTransport;

/**
 * Class to Harvest content via SciVerse source
 * 
 * @author griccardi
 * 
 */

public class SciVerseHarvest {

	final static String FILE_DIR = "c:/dev/harvest/sciverse/";
	final static String FILE_PREFIX = FILE_DIR + "abs_suicide_";
	private final static int MAX_PER_PAGE = 25;
	private static final int FIRST_PAGE = 200;

	@SuppressWarnings("unused")
	private static void run(String[] args) {
		String test;
		String searchText = "abs(suicide)";
		String filePrefix = FILE_PREFIX;
		int numPerFile = MAX_PER_PAGE;
		if (args != null && args.length > 0) {
			searchText = args[0];
		}
		if (args != null && args.length > 1) {
			filePrefix = args[1];
		}
		if (args != null && args.length > 2) {
			numPerFile = Integer.parseInt(args[2]);
		}
		test = harvestFiles(searchText, filePrefix, numPerFile);
	}

	public static String harvestFiles(String keywords, String filePrefix,
			int numPerFile) {
		SciVerseService service = new SciVerseService();
		SciVerseApiParams params = new SciVerseApiParams();
		params.setKeyword(keywords);
		params.setNumResults(1);
		SciVerseResult result = service.getSciVerseResult("search", params);
		int totalResults = result.getTotalResults();
		int totalPages = (int) Math.ceil(totalResults / numPerFile) + 1;
		System.out.println("Total results: " + totalResults + " pages: "
				+ totalPages + " per file: " + numPerFile);
		for (int pageNum = FIRST_PAGE; pageNum < totalPages; pageNum++) {
			params.setFirstResult(pageNum * numPerFile);
			params.setNumResults(numPerFile);
			String fileName = filePrefix + String.format("%05d", pageNum)
					+ ".json";
			System.out.println("getting file " + fileName);
			String contents = service.getSciVerseContents("search", params);
			try {
				FileWriter out = new FileWriter(fileName);
				out.write(contents);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		run(args);
		return;
	}

	public static void enableLogging() {
		Logger logger = Logger.getLogger(HttpTransport.class.getName());
		logger.setLevel(Level.ALL);
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
