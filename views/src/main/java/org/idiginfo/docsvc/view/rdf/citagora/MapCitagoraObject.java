package org.idiginfo.docsvc.view.rdf.citagora;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.view.rdf.vocabulary.BIBO;
import org.idiginfo.docsvc.view.rdf.vocabulary.Content;
import org.idiginfo.docsvc.view.rdf.vocabulary.OAF;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfReview;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
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
	//model.
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
	if (from == null)
	    return null;
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
	if (from instanceof Review)
	    return addReview((Review) from);
	if (from instanceof RatingType)
	    return addRatingType((RatingType) from);
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
	addProperty(resource, DCTerms.type, from.getType());
	addProperty(resource, DCTerms.identifier, from.getId());
	addProperty(resource, BIBO.uri, from.getUri());
	addProperty(resource, DCTerms.creator, from.getWasAttributedTo());
	addProperty(resource, DCTerms.created, from.getCreated());
	addProperty(resource, DCTerms.source, from.getSource());
	addProperty(resource, DCTerms.rights, from.getRights());
	addObject(resource, OAF.generator, from.getGenerator());
	addProperty(resource, DCTerms.dateSubmitted, from.getGenerated());
	addObjects(resource, BIBO.annotates, from.getAnnotations());

	return resource;
    }

    public Resource addCitagoraDocument(CitagoraDocument from) {
	// add parent class object and its properties
	Resource resource = addCitagoraObject(from);

	// add CitagoraDocument properties
	addObject(resource, null, from.getIsAbout());
	addObjects(resource, RdfReview.rating, from.getReviews());
	addObjects(resource, RdfReview.rating, from.getTags());
	addObjects(resource, RdfReview.hasComment, from.getComments());
	return resource;
    }

    private Resource addAnnotation(Annotation from) {
	Resource resource = addCitagoraObject(from);
	addObject(resource, DCTerms.creator, from.getAnnotator());
	addProperty(resource, DCTerms.dateSubmitted, from.getAnnotated());
	addObject(resource, Content.bytes, from.getBody());
	addProperty(resource, DCTerms.source, from.getModelVersion());
	return resource;
    }

    private Resource addComment(Comment from) {
	Resource resource = addAnnotation(from);

	addObject(resource, RdfReview.type, from.getRatingType());
	addObject(resource, RdfReview.reviewer, from.getReviewer());
	addProperty(resource, RdfReview.rating, from.getRating());
	// TODO check type of getReviews
	Resource reviews = (Resource) from.getReviews();
	// addProperty( resource, RdfReview.hasReview,
	// reviews);

	addObjects(resource, null, from.getReplies());
	return resource;
    }

    private Resource addPerson(Person from) {
	String uri = null;// TODO get uri of person
	Resource resource = RdfUtilities.getOrAddResource(model, uri);

	addProperty(resource, FOAF.title, from.getType());
	addProperty(resource, FOAF.givenname, from.getGivenName());
	addProperty(resource, FOAF.family_name, from.getFamilyName());
	addProperty(resource, FOAF.name, from.getName());
	addProperty(resource, FOAF.accountName, from.getAccountName());
	addProperty(resource, FOAF.holdsAccount, from.getAccount());
	addProperty(resource, FOAF.homepage, from.getHomePage());

	return resource;
    }

    private Resource addReview(Review from) {
	Resource resource = addCitagoraObject(from);
	addProperty(resource, RdfReview.type, from.getRatingType());
	addObject(resource, RdfReview.reviewer, from.getReviewer());
	addProperty(resource, RdfReview.rating, from.getRating());
	addProperty(resource, RdfReview.totalVotes, from.getTotalVotes());
	// reverse addObject(resource, null, from.getDocumentReviewed());

	return resource;
    }

    private Resource addCitagoraAnnotationBody(AnnotationBody from) {
	String uri = from.getUri();
	Resource resource = RdfUtilities.getOrAddResource(model, uri);

	addProperty(resource, Content.characterEncoding,
		from.getCharacterEncoding());
	addProperty(resource, Content.chars, from.getChars());

	return resource;
    }

    public Resource addCitagoraAgent(CitagoraAgent from) {
	Resource resource = addPerson(from);
	return resource;
    }

    public Resource addAuthor(Author from) {
	Resource resource = addPerson(from);
	addObject(resource, null, from.getReferences());

	return resource;
    }

    public Resource addReference(Reference from) {
	Resource resource = addCitagoraObject(from);

	addProperty(resource, null, from.getSource());
	addProperty(resource, DCTerms.abstract_, from.getAbstract());
	addProperty(resource, DCTerms.title, from.getTitle());
	addProperty(resource, BIBO.pageStart, from.getPageStart());
	addProperty(resource, BIBO.pageEnd, from.getPageEnd());
	addProperty(resource, BIBO.volume, from.getVolume());
	addProperty(resource, BIBO.issue, from.getIssued());
	addProperty(resource, BIBO.pmid, from.getPmid());
	addProperty(resource, BIBO.doi, from.getDoi());
	addObject(resource, DCTerms.isPartOf, from.isPartOf());
	addObjects(resource, BIBO.authorList, from.getAuthorList());
	addObjects(resource, null, from.getCitationList());
	addObjects(resource, DCTerms.references, from.getSeeAlso());
	addProperty(resource, RdfReview.rating, from.getOverallRating());
	addProperty(resource, RdfReview.rating, from.getReadabilityRating());
	addObject(resource, RdfReview.rating, from.getCitagoraDocuments());
	addProperty(resource, RdfReview.rating, from.getAccuracyRating());
	addProperty(resource, RdfReview.rating, from.getOriginalityRating());
	return resource;

    }

    public Resource addRatingType(RatingType from) {
	String uri = null;// from.getUri(type);
	Resource resource = RdfUtilities.getOrAddResource(model, uri);

	addProperty(resource, null, "");// RatingType.getUri(type));
	return resource;

    }

    public Resource addTag(Tag from) {
	Resource resource = addAnnotation(from);
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
	    addObject(resource, relationship, iterator.next());
	}
    }

    private void addObject(Resource resource, Property relationship,
	    Object target) {
	Resource targetResource = add(target);
	RdfUtilities.addProperty(model, resource, relationship, targetResource);

    }

    private void addProperty(Resource resource, Property property, String target) {
	RdfUtilities.addProperty(model, resource, property, target);
    }

    private void addProperties(Resource resource, Property property,
	    List<String> targets) {
	if (targets == null)
	    return;
	Iterator<String> iterator = targets.iterator();
	while (iterator.hasNext()) {
	    RdfUtilities
		    .addProperty(model, resource, property, iterator.next());
	}
    }

    private void addProperty(Resource resource, Property property, Date target) {
	if (target == null)
	    return;
	addProperty(resource, property, target.toString());

    }

    private void addProperty(Resource resource, Property property,
	    Integer target) {
	// TODO Auto-generated method stub
	if (target == null)
	    return;
	addProperty(resource, property, target.toString());

    }

    private void addProperty(Resource resource, Property property, Double target) {
	// TODO Auto-generated method stub
	if (target == null)
	    return;
	addProperty(resource, property, target.toString());

    }

}