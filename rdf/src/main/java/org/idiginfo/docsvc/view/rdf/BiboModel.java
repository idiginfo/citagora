package org.idiginfo.docsvc.view.rdf;

import org.idiginfo.docsvc.view.rdf.vocabulary.BIBO;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;
import org.openjena.riot.RIOT;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class BiboModel {

	// sample command to run schemagen
	// C:\dev\apache-jena-2.7.3\bat>schemagen.bat -i
	// "http://purl.org/ontology/bibo/" -o c:\dev -n BiboVocabulary -a
	// "http://purl.org/ontology/bibo/" --inference

	Model createSample() {
		RIOT.init();// initialize the I/O tools

		Model model = ModelFactory.createDefaultModel();

		Resource bob = ResourceFactory
				.createResource("doi:10.1023/A:1017264110396");

		RdfUtilities.addProperty(model, bob, BIBO.doi,
				doiUri("doi:10.1023/A:1017264110396"));
		RdfUtilities.addProperty(model, bob, BIBO.authorList, "Garcia");
		model.add(bob, FOAF.knows, bob);
		return model;
	}

	String doiUri(String doi) {
		if (doi == null)
			return null;
		if (doi.startsWith("doi:"))
			return doi;
		return "doi:" + doi;
	}

	static public void main(String[] args) {
		BiboModel biboModel = new BiboModel();
		Model model = biboModel.createSample();
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
