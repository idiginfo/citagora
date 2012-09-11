package org.idiginfo.docsvc.view.rdf.citagora;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.*;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;

/**
 * Tools for mapping from CitagoraObject to/from RDF
 * 
 * @author ssflager
 * 
 */
public class MapCitagoraObject {

	public void buildCitagoraRDF(Model model, CitagoraDocument document) {
		String guid = document.getId();
		Resource docResource = model.createResource(guid);
		addCitagoraDocument(model, docResource, document);
	}
	
	public void addCitagoraDocument(Model model, Resource resource, CitagoraDocument from) {
		RdfUtilities.addProperty(model, resource, DCTerms.type,
				from.getType());
		// getId()
		// getUri()
		// getWasAttributedTo()
		// getCreated()
		// getSource()
		// getRights()
		// getGenerator()
		// getGenerated()
		// getAnnotations()
	// CitagoraDocument
		// ratings
		// tags
		// comments
		// reviews
		
	}

	public void addCitagoraAnnotations() {
//		mapCitagoraObject(this);
		// getTarget()
		// getAnnotator()
		// getAnnotated()
		// getBody()
		// getModelVersion()

	}

	public void addCitagoraAnnotationBody() {
//		mapCitagoraObject(this);
		// getCharacterEncoding()
		// getChars()

	}
	
	public void addCitagoraAgent() {
//		mapCitagoraObject(this);
		// getDocuments()
		// getReferences()
		// getAnnotations()
			// getComments()
			// getTags()

	}
	
	public void addCitagoraAuthor() {
//		mapCitagoraObject(this);
		// getRferences()

	}
	
	public void addCitagoraComments() {
//		mapCitagoraObject(this);
		// getRatingType()
		// getReviewer()
		// getRating()
		// getReviews()
		// getReplies()

	}

	
	public void addCitagoraPerson() {
//		mapCitagoraObject(this);
		// getType()
		// getGivenName()
		// getFamilyName()
		// getName()
		// getAccountName()
		// getAccount()
		// getHomePage()

	}

	public void addCitagoraReviews() {
//		mapCitagoraObject(this);
		// getRatingType()
		// getReviewer()
		// getRating()
		// getTotalVotes()
		// getDocumentReviewed()

	}

	public void addCitagoraReferences() {
//		mapCitagoraObject(this);
		// getSource()
		// getAbstract()
		// getTitle()
		// getPageStart()
		// getPageEnd()
		// getVolume()
		// getIssued()
		// getPmid()
		// getDoi()
		// isPartOf()
		// getAuthorList()
		// getCitationList()
		// getSeeAlso()
		// getCitagoraDocuments()
		// getOverallRatings()
		// getReadabilityRating()
		// getAccuracyRating()
		// getOriginalityRating()

	}

	public void addRatingType() {
//		mapCitagoraObject(this);
		// getUri()

	}
	
	public void addCitagoraTags() {
//		mapCitagoraObject(this);
		// getDocumentTagged()

	}
	
}