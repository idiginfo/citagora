package org.idiginfo.docsvc.svcapi.nature;

import org.idiginfo.docsvc.model.apisvc.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

/**
 * Class to implement GenericUrl object for Nature
 * 
 * @author griccardi
 * 
 */

public class NatureUrl extends GenericUrl {

	public NatureUrl(String collection, String resultFormat) {
		super(NatureApiParams.API_URL);
		apiKey = NatureApiParams.API_KEY;
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

	public NatureUrl(String collection, String resultFormat, String function,
			ApiParams params) {
		this(collection, resultFormat);
	}

	@Key("api_key")
	protected String apiKey;

	@Key("query")
	protected String queryString = "";
	@Key
	String facetLimit;
	@Key
	String httpAccept = "application/json";
	@Key
	Integer maximumRecords;
	@Key
	String operation;
	@Key
	String queryType; // "cql", "searchTerms"
	@Key
	String recordPacking;
	@Key
	String recordSchema;
	@Key
	String sortKeys;
	@Key
	Integer startRecord;
	@Key
	String version;

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

	protected void mapParams(NatureApiParams params) {
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
