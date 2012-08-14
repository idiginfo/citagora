package org.idiginfo.docservices.rdf;

import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.rdf.vocabulary.BIBO;
import org.idiginfo.docservices.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Tools for mapping from model.Document to/from RDF
 * @author griccardi
 *
 */
public class MapDocument {

	/**
	 * Add the document to the model
	 * @param model
	 * @param document
	 * @return
	 */
	Model addDocument(Model model, Document document) {
		String guid = document.getGUID();
		Resource docResource = model.createResource(guid);

		RdfUtilities.addProperty(model, docResource, BIBO.authorList,
				document.getAuthors());
		RdfUtilities.addProperty(model, docResource, BIBO.doi, document.getDoi());
		return model;
	}
}
