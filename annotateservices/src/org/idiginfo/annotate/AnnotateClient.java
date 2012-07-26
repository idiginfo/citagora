package org.idiginfo.annotate;

import java.io.IOException;

import com.google.api.client.*;
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

	private static void parseResponse(HttpResponse response) throws IOException {
	}
}
