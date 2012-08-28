package org.idiginfo.docsvc.svcapi.exploration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestGSON {

	public static void main(String[] args) {
		//TestJsonObject obj = new TestJsonObject();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		// String json = gson.toJson(obj);
		//
		// try {
		// // write converted json data to a file named "file.json"
		// FileWriter writer = new FileWriter("c:\\dev\\file.json");
		// writer.write(json);
		// writer.close();
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println(json);

		try {

			BufferedReader br = new BufferedReader(new FileReader(
			// "c:/dev/api samples/altmetric_citation.json"));
					"c:/dev/api samples/microsoft search.json"));

			JsonParser parser = new JsonParser();

			JsonObject tree = parser.parse(br).getAsJsonObject();
			System.out.println(gson.toJson(tree));
			// result = gson.fromJson(br);
			// Query query = result.getQuery();
			// System.out.println(query.numResults);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// // convert the json string back to object
		// JsonParser parser = new JsonParser();
		//
		// JsonObject tree = parser.parse(json).getAsJsonObject();
		//
		// System.out.println("From string to JsonObject " + gson.toJson(tree));
		// String data1 = tree.get("data1").toString();
		// System.out.println("data1: " + data1);
		// Set<Entry<String, JsonElement>> entrySet = tree.entrySet();
		// Iterator<Entry<String, JsonElement>> entries = entrySet.iterator();
		// while (entries.hasNext()) {
		// Entry<String, JsonElement> entry = entries.next();
		// String key = entry.getKey();
		// JsonElement value = entry.getValue();
		// if (value.isJsonArray()) {
		// System.out.print("List [");
		// JsonArray array = (JsonArray) value;
		// Iterator<JsonElement> values = array.iterator();
		// while (values.hasNext()) {
		// JsonElement valueElement = values.next();
		// System.out.print("(" + valueElement.getClass() + ") "
		// + valueElement.getAsString() + ",");
		// }
		// System.out.println("]");
		// } else {
		// System.out.println(key + ": " + value.getAsString());
		// }
		// }
	}
}
