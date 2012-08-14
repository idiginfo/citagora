package org.idiginfo.docservices.rdf;

import org.idiginfo.docservices.rdf.vocabularies.BiboVocabulary;
import org.idiginfo.docservices.rdf.vocabularies.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class BiboModel {

	static final String BIBO_NAMESPACE = "http://purl.org/ontology/bibo";

	static final String BIBO_URI = BIBO_NAMESPACE;

	// C:\dev\apache-jena-2.7.3\bat>schemagen.bat -i
	// "http://purl.org/ontology/bibo/" -o c:\dev -n BiboVocabulary -a
	// "http://purl.org/ontology/bibo/" --inference

	Model createSample() {
		Model model = ModelFactory.createDefaultModel();
		Resource bob = ResourceFactory
				.createResource("doi:10.1023/A:1017264110396");

		RdfUtilities.addProperty(model, bob, BiboVocabulary.doi,
				doiUri("doi:10.1023/A:1017264110396"));
		RdfUtilities.addProperty(model, bob, BiboVocabulary.authorList,
				"Garcia");
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
