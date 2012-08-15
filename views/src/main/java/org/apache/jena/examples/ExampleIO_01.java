package org.apache.jena.examples;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.openjena.riot.RIOT;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class ExampleIO_01 {

	public static void main(String[] args)  {
		InputStream in = null;
		try {
			in = new FileInputStream("c:/dev/data.ttl");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Utils.getResourceAsStream("c:/dev/data.ttl");

		RIOT.init();

		Model model = ModelFactory.createDefaultModel(); // creates an in-memory
															// Jena Model
		model.read(in, null, "TURTLE"); // parses an InputStream assuming RDF in
										// Turtle format

		// Write the Jena Model in Turtle, RDF/XML and N-Triples format
		System.out.println("\n---- Turtle ----");
		model.write(System.out, "TURTLE");
		System.out.println("\n---- RDF/XML ----");
		model.write(System.out, "RDF/XML");
		System.out.println("\n---- RDF/XML Abbreviated ----");
		model.write(System.out, "RDF/XML-ABBREV");
		System.out.println("\n---- N-Triples ----");
		model.write(System.out, "N-TRIPLES");
		System.out.println("\n---- RDF/JSON ----");
		model.write(System.out, "RDF/JSON");
	}

}