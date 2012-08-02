package org.idiginfo.springer.services;

import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class SpringerUrl extends GenericUrl {

	public SpringerUrl(String collection, String resultFormat) {
		super(SpringerApiParams.API_URL + collection + "/" + resultFormat + "/");
		apiKey = SpringerApiParams.API_KEY;
	}

	// sample URL
	// http://api.springer.com/metadata/json?q=10.1007/s11276-008-0131-4&api_key=yprt5a5cy4pgj3788ewfj7wz

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
		parameterString += constraint + ":" + term;
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
		if (params == null) return;
		// TODO finish method
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
