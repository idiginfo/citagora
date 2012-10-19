package org.idiginfo.docsvc.controller.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseApiParams;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseDocument;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseService;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseUrl;
import org.idiginfo.docsvc.svcapi.sciverse.ScopusAuthUrl;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SciVerseHarvest {

    private final static String FILE_PREFIX = "c:/dev/harvest/sciverse/";

    @SuppressWarnings("unused")
    private static void run(String[] args) {
	String test;
	String searchText="";
	String filePrefix = FILE_PREFIX;
	if (args != null && args.length > 0) {
	    searchText = args[0];
	}
	if (args != null && args.length > 1) {
	    filePrefix = args[1];
	}
	test = harvestFiles(searchText, filePrefix);
    }

    public static String harvestFiles(String keywords, String filePrefix) {
	SciVerseService service = new SciVerseService();
	SciVerseApiParams params = new SciVerseApiParams();
	params.setKeyword(keywords);
	params.setNumResults(1);
	Documents data = service.getDocuments(params);
	

	return null;
    }

    public static void main(String[] args) {
	run(args);
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
