package org.idiginfo.docsvc.view.rdf.citagora;

import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.*;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.DCTerms;

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
		if (from instanceof Comment) {
			return addComment((Comment) from);
		}
		if (from instanceof Annotation) {
			return addAnnotation((Annotation) from);
		}
		// TODO add rest of the methods
		// Person
		// Annotation
		return null;
	}

	/**
	 * Add CitagoraObject (parent class) fields to the resource
	 * 
	 * @param resource
	 *            RDF resource for the object
	 * @param from
	 *            the object to be mapped
	 */
	public Resource addCitagoraObject(CitagoraObject from) {
		// create resource in the model for this object
		String uri = from.getId();
		Resource resource = RdfUtilities.getOrAddResource(model, uri);

		// TODO replace 'null' by correct property
		// add properties of CitagoraObject
		RdfUtilities.addProperty(model, resource, DCTerms.type, from.getType());
		RdfUtilities.addProperty(model, resource, null, from.getId());

		RdfUtilities.addProperty(model, resource, null, from.getUri());
		RdfUtilities.addProperty(model, resource, null,
				from.getWasAttributedTo());
		RdfUtilities.addProperty(model, resource, null, from.getCreated());
		RdfUtilities.addProperty(model, resource, null, from.getSource());
		RdfUtilities.addProperty(model, resource, null, from.getRights());
		Resource generator = addPerson(from.getGenerator());
		// TODO create relationship
		RdfUtilities.addProperty(model, resource, null, from.getGenerated());
		addObjects(resource, null, from.getAnnotations());
		return resource;
	}

	public Resource addCitagoraDocument(CitagoraDocument from) {
		// add parent class object and its properties properties

		Resource resource = addCitagoraObject(from);
		// Reference getIsAbout();
		Resource isAbout = addReference(from.getIsAbout());
		RdfUtilities.addProperty(model, resource, null, isAbout);
		// add CitagoraDocument properties
		addObjects(resource, null, from.getReviews());
		addObjects(resource, null, from.getTags());
		addObjects(resource, null, from.getComments());
		return resource;
	}

	private Resource addAnnotation(Annotation from) {
		Resource resource = addCitagoraObject(from);

		// TODO Auto-generated method stub
		Resource target = addCitagoraDocument((CitagoraDocument) from
				.getTarget());
		RdfUtilities.addProperty(model, resource, null, target);
		// TODO person!
		Resource person = addPerson(from.getAnnotator());
		RdfUtilities.addProperty(model, resource, null, person);

		RdfUtilities.addProperty(model, resource, null, from.getAnnotated());
		// TODO body! RdfUtilities.addProperty(model, resource, null,
		// from.getBody());
		RdfUtilities.addProperty(model, resource, null, from.getModelVersion());
		// if annotation is a comment add additional fields
		return resource;
	}

	Resource addComment(Comment from) {

		Resource resource = addAnnotation(from);
		// RatingType getRatingType();
		// Person getReviewer();
		// Integer getRating();
		// CitagoraDocument getReviews();
		// List<Comment> getReplies();

		return resource;
	}

	private Resource addPerson(Person from) {
		String uri = null;// TODO get uri of person
		Resource resource = RdfUtilities.getOrAddResource(model, uri);
		// mapCitagoraObject(this);
		// getType()
		// getGivenName()
		// getFamilyName()
		// getName()
		// getAccountName()
		// getAccount()
		// getHomePage()

		// getDocuments() Don't do this one!
		// getReferences()
		// getAnnotations()
		// getComments()
		// getTags()
		return null;
	}

	private Resource addReview(Review next) {
		// TODO Auto-generated method stub
		// mapCitagoraObject(this);
		// getRatingType()
		// getReviewer()
		// getRating()
		// getTotalVotes()
		// getDocumentReviewed()

		return null;
	}

	public void addCitagoraAnnotationBody() {
		// mapCitagoraObject(this);
		// getCharacterEncoding()
		// getChars()

	}

	public void addCitagoraAgent(CitagoraAgent from) {

	}

	public Resource addAuthor(Author from) {
		addPerson(from);
		// getRferences()
		return null;
	}

	public Resource addReference(Reference from) {
		// mapCitagoraObject(this);
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
		return null;

	}

	public Resource addRatingType(RatingType from) {
		//
		// getUri()
		return null;

	}

	public Resource addTag(Tag from) {
		// mapCitagoraObject(this);
		// getDocumentTagged()
		return null;
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