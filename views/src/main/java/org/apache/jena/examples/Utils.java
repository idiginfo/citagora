package org.apache.jena.examples;

import java.io.InputStream;

public class Utils {

	public static InputStream getResourceAsStream(String filename) {
		ClassLoader loader = Utils.class.getClassLoader();
		InputStream in = loader.getResourceAsStream(
				filename);
		return in;
	}

}