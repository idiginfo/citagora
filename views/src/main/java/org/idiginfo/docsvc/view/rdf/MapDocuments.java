package org.idiginfo.docsvc.view.rdf;

import java.util.Iterator;

import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Tools for mapping from model.Document to/from RDF
 * @author griccardi
 *
 */
public class MapDocuments {

	MapDocument mapDocument = new MapDocument();
	/**
	 * Add the documents to the model
	 * @param model
	 * @param documents
	 * @return
	 */
	public Model addDocuments(Model model, Documents documents) {
		Iterator<Document> docs = documents.iterator();
		while (docs.hasNext()){
			Document document = docs.next();
			mapDocument.addDocument(model, document);
		}
		return model;
	}
}
