package org.idiginfo.docsvc.controller.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.idiginfo.docsvc.svcapi.msrc.MsrcService;

import com.google.api.client.http.HttpTransport;

public class MsrcHarvest {
    // TODO modify this class to gather documents from the MSRC repository
    final static String FILE_DIR = "c:/dev/harvest/msrc/part2/";
    final static String FILE_PREFIX = FILE_DIR + "doc_";
    private static final int START_INDEX = 2597;

    private void run(String[] args) {
	String test;
	String filePrefix = FILE_PREFIX;
	if (args != null && args.length > 0) {
	    filePrefix = args[0];
	}
	test = harvestFiles(filePrefix);
    }

    public String harvestFiles(String filePrefix) {
	MsrcService service = new MsrcService();
	List<Integer> documentIds = service.getMsrcDocumentIds();
	int totalResults = documentIds.size();
	System.out.println("Total results: " + totalResults);
	for (int i = START_INDEX; i < totalResults; i++) {
	    int documentId = documentIds.get(i);
	    String fileName = filePrefix + String.format("%06d", documentId)
		    + ".json";
	    System.out.println("getting file " + fileName);
	    String contents = service.getJsonDocument(documentId);
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
	MsrcHarvest harvester = new MsrcHarvest();
	harvester.run(args);
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
