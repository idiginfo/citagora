package org.idiginfo.docsvc.view;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

/**
 * Class to implement ObjectWriter for JSON output of Citagora content
 * 
 * @author griccardi
 * 
 */

public class JsonWriter implements ObjectWriter {
	static JsonParser parser = new JsonParser();
	static ExclusionStrategy excludeFields = new MyExclusionStrategy();
	static Gson gson = new GsonBuilder().setExclusionStrategies(excludeFields)
			.setPrettyPrinting().create();

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
