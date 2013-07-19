package org.idiginfo.docsvc.view.rdf;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.vocabulary.BIBO;
import org.idiginfo.docsvc.model.vocabulary.RdfUtilities;

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
	 * Add apisvc document object to the model, using Document GUID as URI to
	 * create Resource; add document fields as Object of RFD Statements, with
	 * corresponding Vocabulary as Predicate.
	 * 
	 * @param model
	 * @param document
	 * 
	 * @return model
	 */
	public Model addDocument(Model model, Document document) {
		// create resource in the model for this object
		String guid = document.getGUID();
		Resource docResource = model.createResource(guid);
		// add properties of DocumentObject
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
