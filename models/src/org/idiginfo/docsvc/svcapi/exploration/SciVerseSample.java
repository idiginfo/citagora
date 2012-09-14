package org.idiginfo.docsvc.svcapi.exploration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.*;

import org.apache.commons.io.IOUtils;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseDocument;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseUrl;

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

public class SciVerseSample {

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static HttpRequestFactory requestFactory;

    @SuppressWarnings("unused")
    private static void run() {
	requestFactory = HTTP_TRANSPORT
		.createRequestFactory(new HttpRequestInitializer() {
		    public void initialize(HttpRequest request)
			    throws IOException {
		    }
		});
	String test;
	test = testSciVerseDocument();
	// test = testSciVerseSearch();
    }

    public static String testSciVerseDocument() {
	String content;
	try {
	    // AnnotateApiParams params = new AnnotateApiParams();
	    SciVerseUrl url = new SciVerseUrl(
		    "article/DOI:10.1016/j.jpsychires.2008.05.001");
	    // url.view = "META_ABS";
	    enableLogging();
	    url.prepare();
	    System.out.println(url.build());
	    HttpRequest request = requestFactory.buildGetRequest(url);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept("application/json");
	    headers.set("X-ELS-APIKey", "32044be7be3a652a32654afeae5bf4d1");// griccardi
	    headers.set("X-ELS-ResourceVersion", "XOCS");
	    request.setHeaders(headers);
	    HttpResponse result = request.execute();
	    // HttpHeaders httpHeaders = result.getHeaders();
	    content = IOUtils.toString(result.getContent());
	    System.out.print(content);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    JsonParser parser = new JsonParser();
	    JsonObject tree = parser.parse(content).getAsJsonObject();
	    System.out.println(gson.toJson(tree));

	    SciVerseDocument data = gson.fromJson(content,
		    SciVerseDocument.class);
	    System.out.println("Id is: " + data.getId());
	    System.out.println("Title is: " + data.getTitle());
	    System.out.println("Pub name is: " + data.getPubName());

	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
	if (SciVerseUrl.isError(content)) {
	    System.err.println(content);
	    return null;
	}
	return null;
    }

    public static String testSciVerseSearch() {
	String content;
	try {
	    SciVerseUrl url = new SciVerseUrl("search/index:hub");
	    // url.view = "META_ABS";
	    url.setQuery("suicide");
	    url.setCount("1");
	    // url.httpAccept="application/json";
	    url.prepare();
	    System.out.println(url.build());
	    HttpRequest request = requestFactory.buildGetRequest(url);
	    HttpHeaders headers = new HttpHeaders();
	    // headers.setAccept("application/json");
	    headers.set("X-ELS-APIKey", "32044be7be3a652a32654afeae5bf4d1");// griccardi
	    // headers.set("X-ELS-APIKey", "5135c5817a6d86b633013ee9e4d120b5");
	    headers.set("X-ELS-ResourceVersion", "XOCS");
	    request.setHeaders(headers);
	    HttpResponse result = null;
	    try {
		result = request.execute();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    content = IOUtils.toString(result.getContent());
	    FileWriter outFile = new FileWriter("c:/dev/sciverse.html");
	    outFile.write(content);
	    outFile.close();
	    // System.out.print(content);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    JsonParser parser = new JsonParser();
	    JsonObject tree = parser.parse(content).getAsJsonObject();
	    System.out.println(gson.toJson(tree));

	    // SciVerseDocument data = gson.fromJson(content,
	    // SciVerseDocument.class);
	    // System.out.println("Id is: " + data.getId());
	    // System.out.println("Title is: " + data.getTitle());
	    // System.out.println("Pub name is: " + data.getPubName());

	} catch (IOException e) {
	    e.printStackTrace();
	    return null;
	}
	if (SciVerseUrl.isError(content)) {
	    System.err.println(content);
	    return null;
	}
	return null;
    }

    public static void main(String[] args) {
	run();
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