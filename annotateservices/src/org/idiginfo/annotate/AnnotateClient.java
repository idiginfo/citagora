package org.idiginfo.annotate;

import java.io.IOException;

import com.google.api.client.http.HttpResponse;

public class AnnotateClient {
	private String apiUrl = null;
	private String apiUser = null;
	private String apiKey = null;

	public AnnotateClient(String apiUrl, String apiUser, String apiKey) {
		this.apiUrl = apiUrl;
		this.apiUser = apiUser;
		this.apiKey = apiKey;
	}

	protected static void parseResponse(HttpResponse response)
			throws IOException {
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
