package org.idiginfo.springer.services;

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
import org.idiginfo.annotate.services.AnnotateApiParams;

import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Simple example for the <a
 * href="http://www.dailymotion.com/doc/api/graph-api.html">Dailymotion Graph
 * API</a>.
 * 
 * @author Yaniv Inbar
 */
public class SciVerseSample {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static HttpRequestFactory requestFactory;

	private static void run() {
		requestFactory = HTTP_TRANSPORT
				.createRequestFactory(new HttpRequestInitializer() {
					public void initialize(HttpRequest request)
							throws IOException {
					}
				});
		String test = testSciVerse();
	}

	public static String testSciVerse() {
		String content;
		try {
			AnnotateApiParams params = new AnnotateApiParams();
			SciVerseUrl url = new SciVerseUrl("article/DOI:10.1016/j.jpsychires.2008.05.001");
			//url.view="META_ABS";
			url.prepare();
			System.out.println(url.build());
			HttpRequest request = requestFactory.buildGetRequest(url);
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept("application/json");
			headers.set("X-ELS-APIKey", "32044be7be3a652a32654afeae5bf4d1");
			//headers.set("X-ELS-APIKey", "5135c5817a6d86b633013ee9e4d120b5");
			headers.set("X-ELS-ResourceVersion", "XOCS");
			request.setHeaders(headers);
			HttpResponse result = request.execute();
			content = IOUtils.toString(result.getContent());
			//System.out.print(content);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser parser = new JsonParser();
			JsonObject tree = parser.parse(content).getAsJsonObject();
			System.out.println(gson.toJson(tree));
			
			SciVerseDocument data = gson.fromJson(content, SciVerseDocument.class);
			System.out.println("Id is: "+data.getId());
			System.out.println("Title is: "+data.getTitle());
			System.out.println("Pub name is: "+data.getPubName());
		
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (SciVerseUrl.isError(content)) {
			System.err.println(content);
			return null;
		}
		return null;
	}


	public static void main(String[] args) {
		run();
		return;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
