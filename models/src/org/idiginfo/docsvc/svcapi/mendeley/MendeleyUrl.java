package org.idiginfo.docsvc.svcapi.mendeley;

import org.idiginfo.docsvc.model.apisvc.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

/**
 * Class that implements the Mendeley URL 
 *  Note: Function Parameters are built with appendRawPath
 *        and API Key & record control (start/count) are standard ("?") type
 * 
 * @author sflager
 * 
 */

public class MendeleyUrl extends GenericUrl {

    public MendeleyUrl() {
	super(MendeleyApiParams.API_URL + "/" + MendeleyApiParams.API_SERVICE + "/");
	apiKey = MendeleyApiParams.API_KEY;
	}

    // sample URL
    // http://api.mendeley.com/oapi/documents/search/<terms>/apikey
    // http://api.mendeley.com/oapi/documents/details/<recordkey>/apikey
    public MendeleyUrl(String function, ApiParams params) {
    this();

    numberResults = params.getNumResults();
	start = params.getFirstResult();
    }

	@Key("consumer_key")
    protected String apiKey;

    // @Key("q")
    protected String parameterString = ""; // "constraint/term" pair
	
    // Record control: which page of results
    @Key("page")
    protected Integer start; // default=0
    
    // Record control: how many results per page
    @Key("items")
    protected Integer numberResults; // default=20

    private void addParameter(String terms) {
    	if (parameterString == null || parameterString.length() == 0) {
    	    parameterString = "";
    	} else {
    	    parameterString += " ";
    	}
    	parameterString += terms;
	}

    public void addParameter(String constraint, String term) {
	if (parameterString == null || parameterString.length() == 0) {
	    parameterString = "";
	} else {
	    parameterString += " ";
	}
	if (constraint != null)
	    parameterString += constraint + ":";
    parameterString += term;
    }

    public String getParameterString() {
	return parameterString;
    }

    /**
     * Get the URL ready for execution
     */
    public void prepare(String function, String search) {
    	if ("details".equals(function)) {
    		appendRawPath("details/");
    		appendRawPath(search);
    		appendRawPath("/");
    	} else if ("search".equals(function)) {
    		appendRawPath("search/");
    		appendRawPath(search);
    		appendRawPath("/");
    	}
    }

    public static boolean isError(String content) {
	if (content.startsWith("XXX")) {
	    return true;
	}
	return false;
    }

    protected void mapParams(MendeleyApiParams params) {
	if (params == null)
	    return;
	// TODO finish method
    }

    public String getApiKey() {
	return apiKey;
    }

    public void setApiKey(String apiKey) {
	this.apiKey = apiKey;
    }

    public static boolean isError(JsonElement json) {
	if (json == null)
	    return true;
	// TODO Auto-generated method stub
	return false;
    }

}
