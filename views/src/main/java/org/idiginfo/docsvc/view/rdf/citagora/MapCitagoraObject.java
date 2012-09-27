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
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.view.rdf.vocabulary.BIBO;
import org.idiginfo.docsvc.view.rdf.vocabulary.Citagora;
import org.idiginfo.docsvc.view.rdf.vocabulary.Content;
import org.idiginfo.docsvc.view.rdf.vocabulary.OAF;
import org.idiginfo.docsvc.view.rdf.vocabulary.Provenance;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfReview;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;
import com.hp.hpl.jena.vocabulary.DCTerms;
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
	initPrefixes();
    }

    public MapCitagoraObject(Model model) {
	this.model = model;
	initPrefixes();
    }

    public void initPrefixes() {
	model.setNsPrefix("dcterms", DCTerms.NS);
	model.setNsPrefix("bibo", BIBO.NS);
	model.setNsPrefix("cnt", Content.NS);
	model.setNsPrefix("oaf", OAF.NS);
	model.setNsPrefix("prov", Provenance.NS);
	model.setNsPrefix("rev", RdfReview.NS);
	model.setNsPrefix("foaf", FOAF.NS);
	model.setNsPrefix("cit", Citagora.NS);
    }

    /**
     * Add any object to the model
     * 
     * @param from
     * @return
     */
    public Resource add(UriObject from) {
	if (from == null)
	    return null;
	String uri = from.getUri();
	if (uri == null || uri.length() == 0)
	    return null;
	Resource resource = model.getResource(uri);
	if (resource.hasProperty(RDF.type)) // already added this object to
					    // the model
	    return resource;
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
	if (from instanceof Reference)
	    return addReference((Reference) from);
	System.err.println("Type not recognized: "
		+ from.getClass().getCanonicalName());
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
	String uri = from.getUri();
	Resource resource = model.getResource(uri);

	// add properties of CitagoraObject
	addProperty(resource, RDF.type, getCitagoraType(from.getType()));
	addProperty(resource, DCTerms.identifier, from.getId());
	addProperty(resource, BIBO.uri, from.getUri());
	addProperty(resource, DCTerms.creator, from.getWasAttributedTo());
	addProperty(resource, DCTerms.created, from.getCreated());
	addProperty(resource, DCTerms.source, from.getSource());
	addProperty(resource, DCTerms.rights, from.getRights());
	addObject(resource, OAF.generator, from.getGenerator());
	addProperty(resource, DCTerms.dateSubmitted, from.getGenerated());
	System.out.println("object: "+RdfUtilities.getProperties(resource));
	return resource;
    }

    public Resource addCitagoraDocument(CitagoraDocument from) {
	// add parent class object and its properties
	Resource resource = addCitagoraObject(from);

	// add CitagoraDocument properties
	addObject(resource, DCTerms.source, from.getIsAbout());
	addObjects(resource, RdfReview.rating, from.getReviews());
	addObjects(resource, Citagora.hasTag, from.getTags());
	addObjects(resource, Citagora.hasComment, from.getComments());
	System.out.println("document: "+RdfUtilities.getProperties(resource));

	return resource;
    }

    private Resource addAnnotation(Annotation from) {
	Resource resource = addCitagoraObject(from);
	addObject(resource, OAF.hasTarget, from.getTarget());
	addObject(resource, DCTerms.creator, from.getAnnotator());
	addProperty(resource, DCTerms.dateSubmitted, from.getAnnotated());
	addObject(resource, OAF.hasBody, from.getBody());
	addProperty(resource, DCTerms.source, from.getModelVersion());
	return resource;
    }

    private Resource addComment(Comment from) {
	Resource resource = addAnnotation(from);

	addProperty(resource, RdfReview.type, from.getCommentType());
	addObject(resource, RdfReview.reviewer, from.getAnnotator());
	addProperty(resource, RdfReview.rating, from.getRating());

	addObjects(resource, null, from.getReplies());
	return resource;
    }

    private Resource addPerson(Person from) {
	String uri = from.getUri();// TODO get uri of person
	Resource resource = model.getResource(uri);

	addProperty(resource, RDF.type, getCitagoraType(from.getType()));
	// addProperty(resource, FOAF.title, from.getTitle());
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
	Resource resource = model.getResource(uri);

	addProperty(resource, RDF.type, getCitagoraType(from.getType()));
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
	addObjects(resource, null, from.getAuthorReferences());

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
	addObjects(resource, BIBO.authorList, from.getAuthors());
	addObjects(resource, null, from.getCitationList());
	addProperties(resource, DCTerms.references, from.getSeeAlso());
	addProperty(resource, RdfReview.rating, from.getOverallRating());
	addProperty(resource, RdfReview.rating, from.getReadabilityRating());
	addObjects(resource, RdfReview.rating, from.getCitagoraDocuments());
	addProperty(resource, RdfReview.rating, from.getAccuracyRating());
	addProperty(resource, RdfReview.rating, from.getOriginalityRating());
	return resource;

    }

    public Resource addRatingType(RatingType from) {
	String uri = null;// from.getUri(type);
	Resource resource = model.getResource(uri);
	addProperty(resource, RDF.type, getCitagoraType(from.getType()));

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
	    addObject(resource, relationship, (UriObject) iterator.next());
	}
    }

    private void addObject(Resource resource, Property relationship,
	    UriObject target) {
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

    private String getCitagoraType(String type) {
	if (CitagoraDocument.TYPE.equals(type))
	    return Citagora.documentType;
	if (Tag.TYPE.equals(type))
	    return Citagora.tagType;
	if (Comment.TYPE.equals(type))
	    return Citagora.commentType;
	if (Person.TYPE.equals(type))
	    return FOAF.Person.getURI();
	if (CitagoraAgent.TYPE.equals(type))
	    return Citagora.agentType;
	if (Author.TYPE.equals(type))
	    return FOAF.Person.getURI();
	if (AnnotationBody.TYPE.equals(type))
	    return Content.ContentAsText.getURI();
	return type;
    }
}