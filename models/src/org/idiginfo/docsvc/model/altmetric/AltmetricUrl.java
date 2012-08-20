package org.idiginfo.docsvc.model.altmetric;

import java.util.Arrays;
import java.util.List;

import org.idiginfo.docsvc.model.model.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

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

	public AltmetricUrl(ApiParams params) {
		this();
		List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
		setPathParts(pathParts);
		// TODO Auto-generated constructor stub
	}

	public AltmetricUrl(String function, ApiParams params) {
		this();
		// List<String> pathParts = Arrays.asList("", "v1", "citations", "1d");
		List<String> pathParts = Arrays.asList("", "v1", "details", "241939");
		setPathParts(pathParts);
		// TODO Auto-generated constructor stub
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

}
