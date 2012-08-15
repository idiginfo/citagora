package org.idiginfo.docsvc.model.exploration;

import java.io.File;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.*;

public class TestJackson {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		JacksonUser user = mapper
				.readValue(new File("c:\\dev\\user.json"), JacksonUser.class);
		System.out.println(user.getGender());
		mapper.writeValue(new File("c:/dev/user-modified.json"), user);
		parseObject();
		parse();
	}

	public static void parseObject() throws Exception {
		ObjectMapper m = new ObjectMapper();
		// can either use mapper.readTree(JsonParser), or bind to JsonNode
		JsonNode rootNode = m.readValue(new File("c:/dev/user.json"), JsonNode.class);
		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		String lastName = nameNode.path("last").getTextValue();
		if ("xmler".equalsIgnoreCase(lastName)) {
		  ((ObjectNode)nameNode).put("last", "Jsoner");
		}
		// and write it out:
		m.writeValue(new File("user-modified.json"), rootNode);
	}

	public static void parse() throws Exception {
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(new File("c:/dev/user.json"));
		JacksonUser user = new JacksonUser();
		jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			String fieldname = jp.getCurrentName();
			jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
			if ("name".equals(fieldname)) { // contains an object
				JacksonUser.Name name = new JacksonUser.Name();
				while (jp.nextToken() != JsonToken.END_OBJECT) {
					String namefield = jp.getCurrentName();
					jp.nextToken(); // move to value
					if ("first".equals(namefield)) {
						name.setFirst(jp.getText());
					} else if ("last".equals(namefield)) {
						name.setLast(jp.getText());
					} else {
						throw new IllegalStateException("Unrecognized field '"
								+ fieldname + "'!");
					}
				}
				user.setName(name);
			} else if ("gender".equals(fieldname)) {
				user.setGender(JacksonUser.Gender.valueOf(jp.getText()));
			} else if ("verified".equals(fieldname)) {
				user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
			} else if ("userImage".equals(fieldname)) {
				user.setUserImage(jp.getBinaryValue());
			} else {
				throw new IllegalStateException("Unrecognized field '"
						+ fieldname + "'!");
			}
		}
		jp.close(); // ensure resources get cleaned up timely and properly
	}

}
