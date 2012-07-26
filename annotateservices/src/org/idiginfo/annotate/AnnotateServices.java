package org.idiginfo.annotate;

/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class AnnotateServices {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static JsonParser parser = new JsonParser();

	private static HttpRequestFactory requestFactory = HTTP_TRANSPORT
			.createRequestFactory(new HttpRequestInitializer() {
				public void initialize(HttpRequest request) throws IOException {
				}
			});
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	public String format(String content) {
		String formattedContent;
		try {
			JsonElement tree = parser.parse(content);
			formattedContent = gson.toJson(tree);
			return formattedContent;
		} catch (JsonParseException e) {
		}
		return null;
	}

	public AnnotateUsers getUsers(AnnotateApiParams params) {
		String content = queryService("listUsers.php", params);
		// map to AnnotateUsers
		AnnotateUsers users = gson.fromJson(content, AnnotateUsers.class);
		return users;
	}

	public AnnotateDocuments getDocuments(String user) {
		return getDocuments(user, false, false);
	}

	public AnnotateDocuments getDocuments(String user, boolean withMeta,
			boolean withNotes) {
		String content;
		AnnotateApiParams params = new AnnotateApiParams();
		params.setApiAnnotateUser(user);
		params.setWithMeta(withMeta);
		params.setWithNotes(withNotes);
		content = queryService("listDocuments.php", params);
		// map to AnnotateDocuments
		AnnotateDocuments documents = new AnnotateDocuments(gson.fromJson(
				content, AnnotateDocument[][].class));
		return documents;
	}

	public AnnotateDocumentNotes getNotes(String documentCode) {
		AnnotateDocument document = null;
		//TODO get the document!
		return getNotes(document);
	}

	public AnnotateDocumentNotes getNotes(AnnotateDocument document) {
		return getNotes(document.getCode(),document.getDate());
	}

	public AnnotateDocumentNotes getNotes(String code, String date) {
		String content;
		AnnotateApiParams params = new AnnotateApiParams();
		params.setCode(code);
		params.setDate(date);// doesn't work without date
		content = queryService("listNotes.php", params);
		// map to AnnotateDocumentNotes
		AnnotateDocumentNotes documentNotes = gson.fromJson(content,
				AnnotateDocumentNotes.class);
		return documentNotes;

	}

	/**
	 * Invoke the a.nnotate service
	 * 
	 * @param function
	 * @return
	 */
	private String queryService(String function, AnnotateApiParams params) {
		String content;
		try {
			AnnotateUrl url = new AnnotateUrl(function, params);
			url.prepare();
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
			result.disconnect();
			if (AnnotateUrl.isError(content)) {
				System.err.println(content);
				return null;
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
