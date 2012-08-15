package org.apache.jena.examples;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class ExampleIO_02 {

	public static void main(String[] args) {
		Model model = ModelFactory.createDefaultModel();
		// Retrieve a URL corresponding to RDF Schema vocabulary in RDF/XML
		// format
		model.read("http://www.w3.org/TR/rdf-schema/rdfs-namespace", null,
				"RDF/XML");

		model.write(System.out, "TURTLE");
		main2(args);
	}

	public static void main2(String[] args) {
		FileManager fm = FileManager.get();
		fm.addLocatorClassLoader(ExampleAPI_01.class.getClassLoader());
		// load an RDF file using the FileManager
		Model model = fm.loadModel("c:/dev/data.ttl", null, "TURTLE");
		model.write(System.out, "TURTLE");
	}

}
