package org.idiginfo.docsvc.view.rdf;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.vocabulary.BIBO;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class MapAnnotation {

	/**
	 * Add apisvc annotation object to the model, using either non-null DOI or
	 *  Document ID as URI to create Resource; add annotation fields as
	 *  Object of RFD Statements, with corresponding Vocabulary as Predicate. 
	 * 
	 * @param model
	 * @param document
	 * 
	 * @return model
	 */
	Model addDocument(Model model, Document document) {
		String doi = document.getDoi();
		Resource docResource;
		if (doi != null) {
			docResource = model.createResource(doi);
		} else {
			docResource = model.createResource(document.getId());
		}
		model.add(docResource, BIBO.authorList, document.getAuthors());
		model.add(docResource, BIBO.doi, doi);
		return model;
	}
}
