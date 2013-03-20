package org.idiginfo.docsvc.controller.harvest;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.idiginfo.docsvc.svcapi.mendeley.MendeleyApiParams;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyResult;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeader;

import com.google.api.client.http.HttpTransport;

/**
 * Class that implements the harvest of Mendeley API content 
 * 
 * @author sflager
 * 
 */
public class MendeleyHarvest {

    final static String FILE_DIR = "c:/dev/split/mendeleyTitle/";
    final static String FILE_PREFIX = FILE_DIR + "suicide_";
    private final static int MAX_PER_PAGE = 20;
    private static final int FIRST_PAGE = 773;

    @SuppressWarnings("unused")
    private static void run(String[] args) {
	String test;
	String searchText = "suicide";
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
	MendeleyService service = new MendeleyService();
	MendeleyApiParams params = new MendeleyApiParams();
	params.setKeyword(keywords);
	params.setFirstResult(0);
	params.setNumResults(1);
	MendeleyResult result0 = service.getMendeleyResult("search", keywords, params);
	int totalResults = result0.getTotalResults();
	int totalPages = (int) Math.ceil(totalResults / numPerFile) + 1;
	System.out.println("Total results: " + totalResults + " pages: "
		+ totalPages + " per file: " + numPerFile);
	params.setNumResults(numPerFile);
	for (int pageNum = FIRST_PAGE; pageNum < totalPages; pageNum++) {
		int resultPage = pageNum - 1;
		System.out.println("Now on page number: " + resultPage);
		params.setFirstResult(resultPage);
		MendeleyResult result = service.getMendeleyResult("search", keywords, params);
		List<MendeleyHeader> headers = result.getHeaders();
		for (int i = 0; i < headers.size(); i++) {
			String uuid = headers.get(i).getUuid();
			String uText = service.getMendeleyUUID("details", uuid, params);
			//System.out.println(uText);
			String fileName = filePrefix + uuid
					+ ".json";
		    System.out.println("getting file " + fileName);
		    try {
				FileWriter out = new FileWriter(fileName);
				out.write(uText);
				out.close();
			    } catch (IOException e) {
				e.printStackTrace();
				break;
				}
		}
		// Mendeley rate limiting: 500 "detail" requests per hour === 25 sets of 20 uuid's per hour
		//  :. sleep for 2.5 minutes between outer loop iterations
		try {
			Thread.sleep(150000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
