package org.idiginfo.docsvc.view.rdf.citagora;

import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.citagora.*;
import org.idiginfo.docsvc.view.rdf.vocabulary.*;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.sparql.pfunction.library.str;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DC;
import com.hp.hpl.jena.vocabulary.DCTerms;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.OWL2;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Tools for mapping from CitagoraObject to/from RDF
 * 
 * @author ssflager
 * 
 */
public class MapCitagoraObject {

	Model model = null;

	public MapCitagoraObject() {
		model = ModelFactory.createDefaultModel();
	}

	public MapCitagoraObject(Model model) {
		this.model = model;
	}

	/**
	 * Add any object to the model
	 * 
	 * @param from
	 * @return
	 */
	public Resource add(Object from) {
		from.getClass();
		if (from instanceof CitagoraDocument)
			return addCitagoraDocument((CitagoraDocument) from);
		if (from instanceof Comment) 
			return addComment((Comment) from);
		if (from instanceof Tag) 
			return addTag((Tag) from);
		if (from instanceof Annotation)
			return addAnnotation((Annotation) from);
		if (from instanceof Person)
			return addPerson((Person) from);
		if (from instanceof AnnotationBody)
			return addCitagoraAnnotationBody((AnnotationBody) from);
		// Annotation
		return null;
	}

	/**
	 * Add CitagoraObject (parent class) fields to the resource
	 * 
	 * @param from
	 *            the object to be mapped
	 */
	public Resource addCitagoraObject(CitagoraObject from) {
		// create resource in the model for this object
		String uri = from.getId();
		Resource resource = RdfUtilities.getOrAddResource(model, uri);

		// add properties of CitagoraObject
		RdfUtilities.addProperty(model, resource, DCTerms.type, from.getType());
		RdfUtilities.addProperty(model, resource, DCTerms.identifier, from.getId());

		RdfUtilities.addProperty(model, resource, BIBO.uri, from.getUri());
		RdfUtilities.addProperty(model, resource, DCTerms.creator,
				from.getWasAttributedTo());
		RdfUtilities.addProperty(model, resource, DCTerms.created, from.getCreated());
		RdfUtilities.addProperty(model, resource, DCTerms.source, from.getSource());
		RdfUtilities.addProperty(model, resource, DCTerms.rights, from.getRights());
		Resource generator = addPerson(from.getGenerator());
		RdfUtilities.addProperty(model, resource, OAF.generator, generator);
		RdfUtilities.addProperty(model, resource, DCTerms.dateSubmitted, from.getGenerated());
		addObjects(resource, BIBO.annotates, from.getAnnotations());
		
		return resource;
	}

	public Resource addCitagoraDocument(CitagoraDocument from) {
		// add parent class object and its properties
		Resource resource = addCitagoraObject(from);
		Resource isAbout = addReference(from.getIsAbout());
		RdfUtilities.addProperty(model, resource, null, isAbout);
		// add CitagoraDocument properties
		addObjects(resource, RdfReview.rating, from.getReviews());
		addObjects(resource, RdfReview.rating, from.getTags());
		addObjects(resource, RdfReview.hasComment, from.getComments());
		return resource;
	}

	private Resource addAnnotation(Annotation from) {
		Resource resource = addCitagoraObject(from);

		Resource target = addCitagoraDocument((CitagoraDocument) 
				from.getTarget());
		RdfUtilities.addProperty(model, resource, null, target);
		Resource person = addPerson(from.getAnnotator());
		RdfUtilities.addProperty(model, resource, DCTerms.creator, person);

		RdfUtilities.addProperty(model, resource, DCTerms.dateSubmitted, 
				from.getAnnotated());
		Resource body = add(from.getBody());
		RdfUtilities.addProperty(model, resource, Content.bytes, body);
		RdfUtilities.addProperty(model, resource, DCTerms.source, 
				from.getModelVersion());
		// if annotation is a comment add additional fields
		return resource;
	}

	private Resource addComment(Comment from) {
		Resource resource = addAnnotation(from);

		Resource ratingType = (Resource) from.getRatingType(); 
		RdfUtilities.addProperty(model, resource, RdfReview.type, ratingType);
		Resource reviewer = addPerson(from.getReviewer());
		RdfUtilities.addProperty(model, resource, RdfReview.reviewer, reviewer);
		RdfUtilities.addProperty(model, resource, RdfReview.rating, 
				from.getRating().toString());
		Resource reviews = (Resource) from.getReviews();
		RdfUtilities.addProperty(model, resource, RdfReview.hasReview, reviews);
		Resource replys = (Resource) from.getReplies();
		RdfUtilities.addProperty(model, resource, RdfReview.hasFeedback, replys);
		return resource;
	}

	private Resource addPerson(Person from) {
		String uri = null;// TODO get uri of person
		Resource resource = RdfUtilities.getOrAddResource(model, uri);

		RdfUtilities.addProperty(model, resource, FOAF.title, 
				from.getType());
		RdfUtilities.addProperty(model, resource, FOAF.givenname, 
				from.getGivenName());
		RdfUtilities.addProperty(model, resource, FOAF.family_name, 
				from.getFamilyName());
		RdfUtilities.addProperty(model, resource, FOAF.name, 
				from.getName());
		RdfUtilities.addProperty(model, resource, FOAF.accountName, 
				from.getAccountName());
		RdfUtilities.addProperty(model, resource, FOAF.holdsAccount, 
				from.getAccount());
		RdfUtilities.addProperty(model, resource, FOAF.homepage, 
				from.getHomePage());

		return resource;
	}

	private Resource addReview(Review from) {
		Resource resource = addReview(from);
		RdfUtilities.addProperty(model, resource, RdfReview.type, 
				from.getRatingType());
		Resource reviewer = (Resource) from.getReviewer();
		RdfUtilities.addProperty(model, resource, RdfReview.reviewer, reviewer);
		RdfUtilities.addProperty(model, resource, RdfReview.rating, 
				from.getRating().toString());
		RdfUtilities.addProperty(model, resource, RdfReview.totalVotes, 
				from.getTotalVotes().toString());
		Resource reviewed = addCitagoraDocument((CitagoraDocument)
				from.getDocumentReviewed());
		RdfUtilities.addProperty(model, resource, null, reviewed);

		return resource;
	}

	private Resource addCitagoraAnnotationBody(AnnotationBody from) {
		Resource resource = addCitagoraAnnotationBody(from);
		
		RdfUtilities.addProperty(model, resource, Content.characterEncoding, 
				from.getCharacterEncoding());
		RdfUtilities.addProperty(model, resource, Content.chars, from.getChars());
		
		return resource;
	}

	public Resource addCitagoraAgent(CitagoraAgent from) {
		Resource resource = addCitagoraAgent(from);
		addPerson(from);
		// getDocuments() Don't do this one!
		Resource reference = (Resource) from.getReferences();
		RdfUtilities.addProperty(model, resource, null, reference);
		Resource annotations = (Resource) from.getAnnotations();
		RdfUtilities.addProperty(model, resource, null, annotations);
		Resource comments = (Resource) from.getComments();
		RdfUtilities.addProperty(model, resource, null, comments);
		Resource tags = (Resource) from.getTags();
		RdfUtilities.addProperty(model, resource, null, tags);

		return resource;
	}

	public Resource addAuthor(Author from) {
		Resource resource = addAuthor(from);
		addPerson(from);
		Resource reference = (Resource) from.getReferences();
		RdfUtilities.addProperty(model, resource, null, reference);

		return resource;
	}

	public Resource addReference(Reference from) {
		Resource resource = add(from);
		RdfUtilities.addProperty(model, resource, null, from.getSource());
		RdfUtilities.addProperty(model, resource, DCTerms.abstract_, from.getAbstract());
		RdfUtilities.addProperty(model, resource, DCTerms.title, from.getTitle());
		RdfUtilities.addProperty(model, resource, BIBO.pageStart, 
				from.getPageStart().toString());
		RdfUtilities.addProperty(model, resource, BIBO.pageEnd, 
				from.getPageEnd().toString());
		RdfUtilities.addProperty(model, resource, BIBO.volume, from.getVolume());
		RdfUtilities.addProperty(model, resource, BIBO.issue, from.getIssued());
		RdfUtilities.addProperty(model, resource, BIBO.pmid, from.getPmid());
		RdfUtilities.addProperty(model, resource, BIBO.doi, from.getDoi());
		Resource isPartOf = (Resource) from.isPartOf();
		RdfUtilities.addProperty(model, resource, DCTerms.isPartOf, isPartOf);
		Resource authorList = (Resource) from.getAuthorList();
		RdfUtilities.addProperty(model, resource, BIBO.authorList, authorList);
		Resource citationList = (Resource) from.getCitationList();
		RdfUtilities.addProperty(model, resource, null, citationList);
		Resource seeAlso = (Resource) from.getSeeAlso();
		RdfUtilities.addProperty(model, resource, DCTerms.references, seeAlso);
		RdfUtilities.addProperty(model, resource, RdfReview.rating,  
				from.getOverallRating().toString());
		RdfUtilities.addProperty(model, resource, RdfReview.rating, 
				from.getReadabilityRating().toString());
		Resource citagoraDocuments = (Resource) from.getCitagoraDocuments();
		RdfUtilities.addProperty(model, resource, RdfReview.rating, 
				citagoraDocuments);
		RdfUtilities.addProperty(model, resource, RdfReview.rating,
				from.getAccuracyRating().toString());
		RdfUtilities.addProperty(model, resource, RdfReview.rating, 
				from.getOriginalityRating().toString());
		return resource;

	}

	public Resource addRatingType(RatingType from, String type) {
		Resource resource = add(from);
		RdfUtilities.addProperty(model, resource, null, RatingType.getUri(type));
		
		return resource;

	}

	public Resource addTag(Tag from) {
		Resource resource = add(from);
		Resource tagged = addCitagoraDocument((CitagoraDocument) 
				from.getDocumentTagged());
		RdfUtilities.addProperty(model, resource, null, tagged);
		
		return resource;
	}

	public Model getModel() {
		return model;
	}

	/**
	 * Add annotations and their relationships
	 * 
	 * @param resource
	 * @param relationship
	 * @param annotations
	 */
	private void addObjects(Resource resource, Property relationship,
			List<?> targets) {
		if (targets == null)
			return;
		Iterator<?> iterator = targets.iterator();
		while (iterator.hasNext()) {

			Resource annotation = add(iterator.next());
			RdfUtilities.addProperty(model, resource, relationship, annotation);
		}
	}

}