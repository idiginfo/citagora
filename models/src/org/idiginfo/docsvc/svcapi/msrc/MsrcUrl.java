package org.idiginfo.docsvc.svcapi.msrc;

import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

public class MsrcUrl extends GenericUrl {

    public MsrcUrl(String collection, String time) {
	this();
	List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
	setPathParts(pathParts);
    }

    // sample URL
    // http://api.Altmetric.com/metadata/json?q=10.1007/s11276-008-0131-4&api_key=yprt5a5cy4pgj3788ewfj7wz

    public MsrcUrl() {
	super();
	setHost(MsrcApiParams.API_HOST);
	setScheme("https");
    }

    public MsrcUrl(MsrcApiParams params) {
	this();
	// List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
	// List<String> pathParts = Arrays.asList("", "v1", "details",
	// "241939");201135
	String id = params.getId();
	String collection = params.getCollection();
	if (id != null) {
	    // get all citations by timeframe
	    List<String> pathParts = Arrays.asList("",
		    MsrcApiParams.API_SERVICE_NAME,
		    MsrcApiParams.BIBLIO_COLLECTION, id);
	    setPathParts(pathParts);
	} else {// get single document by id
	    List<String> pathParts = Arrays.asList("",
		    MsrcApiParams.API_SERVICE_NAME,
		    MsrcApiParams.BIBLIO_COLLECTION);
	    setPathParts(pathParts);
	}
    }

    /**
     * Get the URL ready for execution
     */
    public void prepare() {

    }

    public static boolean isError(String content) {
	if (content.startsWith("<html")) {
	    return true;
	}
	return false;
    }

    protected void mapParams(MsrcApiParams params) {
	if (params == null)
	    return;
	// TODO finish method
    }

    public static boolean isError(JsonElement json) {
	// TODO Auto-generated method stub
	if (json == null)
	    return true;
	return false;
    }

    public static String getMessage(JsonElement json) {
	// TODO Auto-generated method stub
	if (json == null)
	    return "no json objects found";
	return null;
    }

}
