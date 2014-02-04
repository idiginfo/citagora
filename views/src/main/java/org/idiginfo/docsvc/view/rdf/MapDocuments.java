package org.idiginfo.docsvc.view.rdf;

import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Tools for mapping from model.Document to/from RDF
 * 
 * @author griccardi
 * 
 */
public class MapDocuments {

	MapDocument mapDocument = new MapDocument();

	/**
	 * Add List of apisvc document objects to the model, using an Iterator if
	 * the List is not null to map each element.
	 * 
	 * @param model
	 * @param documents
	 * 
	 * @return model
	 */
	public Model addDocuments(Model model, List<Document> documents) {
		if (documents == null)
			return model;
		Iterator<Document> docs = documents.iterator();
		while (docs.hasNext()) {
			Document document = docs.next();
			mapDocument.addDocument(model, document);
		}
		return model;
	}
}
