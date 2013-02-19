package org.idiginfo.docsvc.model.crossref;

import org.idiginfo.docsvc.model.apisvc.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

public class CrossrefUrl extends GenericUrl {

    public CrossrefUrl(String collection, String resultFormat) {
	super(CrossrefApiParams.API_URL);
	// apiKey = CrossrefApiParams.API_KEY;
    }

    // sample URL
    // http://api.nature.com/content/opensearch/request?queryType=searchTerms&query=darwin+lamarck
    // &api_key=<API key string here>

    // parameters
    // facetLimit, httpAccept, maximumRecords, operation, queryType,
    // recordPacking, recordSchema, sortKeys, startRecord, version

    // CQL query sample

    // http://api.nature.com/content/opensearch/request?queryType=cql
    // &query=cql.keywords+any+darwin+OR+cql.keywords+any+lamarck
    // &httpAccept=application/json
    // &api_key=<API key string here>

    public CrossrefUrl(String collection, String resultFormat, String function,
	    ApiParams params) {
	this(collection, resultFormat);
	queryString = params.getSearchTerms();
    }

    @Key("q")
    protected String queryString = "";
    @Key
    String sort;
    @Key
    Integer rows;
    @Key
    Integer page;
    // facets
    // science category, year of publication, publication type, open access
    // status and publication name.
    @Key
    Integer year;
    // not sure of parameter names
    @Key
    String type;
    @Key
    String status;
    @Key
    String name;
    @Key
    String header = "true";

    /**
     * Get the URL ready for execution
     */
    public void prepare() {

    }

    public static boolean isError(String content) {
	if (content.startsWith("XXX")) {
	    return true;
	}
	return false;
    }

    protected void mapParams(CrossrefApiParams params) {
	if (params == null)
	    return;
	// TODO finish method
    }

    public static boolean isError(JsonElement json) {
	if (json == null)
	    return true;
	// TODO Auto-generated method stub
	return false;
    }

}
