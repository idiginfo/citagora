package org.idiginfo.docsvc.view;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

public class JsonWriter implements ObjectWriter {
	static JsonParser parser = new JsonParser();
	static Gson gson = new GsonBuilder().setPrettyPrinting().create();

	@Override
	public String writeDocument(Document document) {
		return gson.toJson(document);
	}

	@Override
	public String writeDocuments(Documents documents) {
		return gson.toJson(documents);
	}

	@Override
	public String write(Object objects) {
		String formattedContent = gson.toJson(objects);
		return formattedContent;
	}

	public String format(String content) {
		String formattedContent;
		if (content == null)
			return null;
		try {
			JsonElement tree = parser.parse(content);
			formattedContent = gson.toJson(tree);
			return formattedContent;
		} catch (JsonParseException e) {
		}
		return null;
	}

}
