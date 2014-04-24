package org.idiginfo.docsvc.svcapi.altmetric;

import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

/**
 * Class to implement Url for Altmetric
 * 
 * @author griccardi
 * 
 */

public class AltmetricUrl extends GenericUrl {

	public AltmetricUrl(String collection, String time) {
		this();
		List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
		setPathParts(pathParts);
	}

	// sample URL
	// http://api.Altmetric.com/metadata/json?q=10.1007/s11276-008-0131-4&api_key=yprt5a5cy4pgj3788ewfj7wz

	public AltmetricUrl() {
		super();
		apiKey = AltmetricApiParams.API_KEY;
		setHost(AltmetricApiParams.API_HOST);
		setScheme("http");
	}

	public AltmetricUrl(AltmetricApiParams params) {
		this();
		// List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
		// List<String> pathParts = Arrays.asList("", "v1", "details",
		// "241939");201135
		String collection = params.getAltmetricCollection();
		if (AltmetricApiParams.CITATION_COLLECTION.equals(collection)) {
			// get all citations by timeframe
			String timeframe = params.getTimeframe()==null?"":params.getTimeframe();
			List<String> pathParts = Arrays.asList("",
					AltmetricApiParams.API_VERSION, collection, timeframe);
			setPathParts(pathParts);
		} else {// get single document by id
			String id = params.getId();
			List<String> pathParts = Arrays.asList("",
					AltmetricApiParams.API_VERSION, collection, id);
			setPathParts(pathParts);
		}
	}

	@Key("key")
	protected String apiKey;

	protected Integer numberResults; // default=10

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

	protected void mapParams(AltmetricApiParams params) {
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
