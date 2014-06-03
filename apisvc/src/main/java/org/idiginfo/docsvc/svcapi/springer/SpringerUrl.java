package org.idiginfo.docsvc.svcapi.springer;

import org.idiginfo.docsvc.model.apisvc.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

/**
 * Class to implement the GenericUrl object for Springer
 * 
 * @author griccardi
 * 
 */

public class SpringerUrl extends GenericUrl {

	public SpringerUrl(String collection, String resultFormat) {
		super(SpringerApiParams.API_URL + collection + "/" + resultFormat + "/");
		if (collection.equals("metadata")) {
			apiKey = SpringerApiParams.API_KEY;
		} else {
			apiKey = SpringerApiParams.OPENACCESS_API_KEY;
		}

	}

	// sample URL
	// http://api.springer.com/metadata/json?q=10.1007/s11276-008-0131-4&api_key=yprt5a5cy4pgj3788ewfj7wz

	public SpringerUrl(String collection, String resultFormat, String function,
			ApiParams params) {
		this(collection, resultFormat);
		if ("getdocument".equals(function)) {
			addParameter("doi", params.getId());
		} else if ("getdocuments".equals(function)) {
			addParameter("title", params.getSearchTerms());
		}
		numberResults = params.getNumResults();
		start = params.getFirstResult();
	}

	@Key("api_key")
	protected String apiKey;

	@Key("q")
	protected String parameterString = ""; // "constraint:term" pairs
	// separated by "+"

	@Key("s")
	protected Integer start; // default=0
	@Key("p")
	protected Integer numberResults; // default=10

	public void addParameter(String constraint, String term) {
		if (parameterString == null || parameterString.length() == 0) {
			parameterString = "";
		} else {
			parameterString += "+";
		}
		if (constraint != null)
			parameterString += constraint + ":";
		if ("doi".equals(constraint)) {// add term with no quotes
			parameterString += term;
		} else {// add term with quotes
			parameterString += "\"" + term + "\"";
		}
	}

	public void addExcludingParameter(String constraint, String term) {
		if (parameterString == null || parameterString.length() == 0) {
			parameterString = "";
		} else {
			parameterString += "+";
		}
		parameterString += "-(" + constraint + ":" + term + ")";
	}

	public String getParameterString() {
		return parameterString;
	}

	// @Key
	// List<String> field;

	// view, field

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

	protected void mapParams(SpringerApiParams params) {
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
