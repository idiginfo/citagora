package org.idiginfo.docsvc.model.exploration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class TestJacksonJson {

	public static void main(String[] args) {
		// TestJsonObject obj = new TestJsonObject();

		ObjectMapper mapper = new ObjectMapper();

		// convert java object to JSON format,
		// and returned as JSON formatted string

		// try {
		// // write converted json data to a file named "file.json"
		// String json = mapper.writeValueAsString(obj);
		// FileWriter writer = new FileWriter("c:\\dev\\file.json");
		// writer.write(json);
		// writer.close();
		// System.out.println(json);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		try {

			BufferedReader br = new BufferedReader(new FileReader(
					"c:\\dev\\file.json"));

			// convert the json string back to object
			TestJsonObject obj2 = mapper.readValue(br, TestJsonObject.class);

			System.out.println(obj2);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// try {
		//
		// BufferedReader br = new BufferedReader(new FileReader(
		// "c:\\dev\\file.json"));
		//
		// // convert the json string back to object
		// JsonParser parser = new JsonParser();
		//
		// JsonObject tree = parser.parse(json).getAsJsonObject();
		//
		// System.out
		// .println("From string to JsonObject " + gson.toJson(tree));
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
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

	}
}
