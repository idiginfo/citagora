package org.idiginfo.docsvc.view.rdf;

import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.view.rdf.vocabulary.BIBO;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;

/**
 * Tools for mapping from model.Document to/from RDF
 * 
 * @author griccardi
 * 
 */
public class MapDocument {

	/**
	 * Add the document to the model
	 * 
	 * @param model
	 * @param document
	 * @return
	 */
	public Model addDocument(Model model, Document document) {
		String guid = document.getGUID();
		Resource docResource = model.createResource(guid);

		RdfUtilities.addProperty(model, docResource, BIBO.authorList,
				document.getAuthors());
		RdfUtilities.addProperty(model, docResource, BIBO.doi,
				document.getDoi());
		RdfUtilities.addProperty(model, docResource, BIBO.identifier,
				document.getId());
		RdfUtilities.addProperty(model, docResource, DCTerms.date,
				document.getDate());
		RdfUtilities.addProperty(model, docResource, DCTerms.source,
				document.getName());
		RdfUtilities.addProperty(model, docResource, BIBO.owner,
				document.getOwner());
		RdfUtilities.addProperty(model, docResource, DCTerms.type,
				document.getType());
		RdfUtilities.addProperty(model, docResource, DCTerms.title,
				document.getTitle());
		return model;
	}
}
