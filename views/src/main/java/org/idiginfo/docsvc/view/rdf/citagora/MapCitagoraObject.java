package org.idiginfo.docsvc.view.rdf.citagora;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
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
import org.idiginfo.docsvc.view.rdf.vocabulary.CommonTag;
import org.idiginfo.docsvc.view.rdf.vocabulary.Content;
import org.idiginfo.docsvc.view.rdf.vocabulary.DCTERMS;
import org.idiginfo.docsvc.view.rdf.vocabulary.FOAF;
import org.idiginfo.docsvc.view.rdf.vocabulary.OAF;
import org.idiginfo.docsvc.view.rdf.vocabulary.OAX;
import org.idiginfo.docsvc.view.rdf.vocabulary.Provenance;
import org.idiginfo.docsvc.view.rdf.vocabulary.RDF;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfReview;
import org.idiginfo.docsvc.view.rdf.vocabulary.RdfUtilities;
import org.idiginfo.docsvc.view.rdf.vocabulary.ResourceList;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

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
	model.setNsPrefix("dcterms", DCTERMS.NS);
	model.setNsPrefix("bibo", BIBO.NS);
	model.setNsPrefix("cnt", Content.NS);
	model.setNsPrefix("oa", OAF.NS);
	model.setNsPrefix("oax", OAX.NS);
	model.setNsPrefix("prov", Provenance.NS);
	model.setNsPrefix("rev", RdfReview.NS);
	model.setNsPrefix("foaf", FOAF.NS);
	model.setNsPrefix("cit", Citagora.NS);
	model.setNsPrefix("res", ResourceList.NS);
	model.setNsPrefix("ctag", CommonTag.NS);
    }

    /**
     * Add any object to the model
     * 
     * @param from
     * @param level TODO
     * @return
     */
    public Resource add(UriObject from, int level) {
	if (from == null)
	    return null;
	String uri = from.getUri();
	if (uri == null || uri.length() == 0)
	    return null;
	Resource resource = model.getResource(uri);
	if (resource.hasProperty(RDF.type)) // already added this object to
					    // the model
	    return resource;
	if (level==0) return resource;
	addObject(resource, RDF.type, getCitagoraType(from.getType()));
	//from.getClass();
	if (from instanceof Container)
	    return addContainer((Container) from, level);
	if (from instanceof Comment)
	    return addComment((Comment) from, level);
	if (from instanceof Tag)
	    return addTag((Tag) from, level);
	if (from instanceof Annotation)
	    return addAnnotation((Annotation) from, level);
	if (from instanceof Person)
	    return addPerson((Person) from, level);
	if (from instanceof AnnotationBody)
	    return addCitagoraAnnotationBody((AnnotationBody) from);
	if (from instanceof Review)
	    return addReview((Review) from, level);
	if (from instanceof RatingType)
	    return addRatingType((RatingType) from);
	if (from instanceof Reference)
	    return addReference((Reference) from, level);
	System.err.println("Type not recognized: "
		+ from.getClass().getCanonicalName());
	return null;
    }

    /**
     * Add CitagoraObject (parent class) fields to the resource
     * 
     * @param from
     *            the object to be mapped
     * @param level TODO
     */
    public Resource addCitagoraObject(CitagoraObject from, int level) {
	// create resource in the model for this object
	String uri = from.getUri();
	Resource resource = model.getResource(uri);

	// add properties of CitagoraObject
	//addObject(resource, RDF.type, getCitagoraType(from.getType()));
	// addProperty(resource, DublinCore.identifier, from.getId());
	// addProperty(resource, BIBO.uri, from.getUri());
	addProperty(resource, DCTERMS.creator, from.getWasAttributedTo());
	addProperty(resource, DCTERMS.created, from.getCreated());
	addProperty(resource, DCTERMS.source, from.getSource());
	addProperty(resource, DCTERMS.rights, from.getRights());
	addObject(resource, OAF.generator, from.getGenerator(), level);
	addProperty(resource, DCTERMS.dateSubmitted, from.getGenerated());
	System.out.println("object: " + RdfUtilities.getProperties(resource));
	return resource;
    }

    public Resource addContainer(Container from, int level) {
	// add parent class object and its properties
	Resource resource = addCitagoraObject(from, level);

	// add Container properties
	addObject(resource, CommonTag.isAbout, from.getIsAbout(), level);
	addObjects(resource, RdfReview.rating, from.getReviews(), level);
	addObjects(resource, Citagora.hasTag, from.getTags(), level);
	addObjects(resource, Citagora.hasComment, from.getComments(), level);
	System.out.println("document: " + RdfUtilities.getProperties(resource));

	return resource;
    }

    private Resource addAnnotation(Annotation from, int level) {
	Resource resource = addCitagoraObject(from, level);
	addObject(resource, OAF.hasTarget, from.getTarget(), level);
	addObject(resource, DCTERMS.creator, from.getAnnotator(), level);
	addProperty(resource, DCTERMS.dateSubmitted, from.getAnnotated());
	addObject(resource, OAF.hasBody, from.getBody(), level);
	addProperty(resource, DCTERMS.source, from.getModelVersion());
	return resource;
    }

    private Resource addComment(Comment from, int level) {
	Resource resource = addAnnotation(from, level);

	addProperty(resource, RdfReview.type, from.getCommentType());
	addObject(resource, RdfReview.reviewer, from.getAnnotator(), level);
	addProperty(resource, RdfReview.rating, from.getRating());

	addObjects(resource, null, from.getReplies(), level);
	return resource;
    }

    private Resource addPerson(Person from, int level) {
	String uri = from.getUri();// TODO get uri of person
	Resource resource = model.getResource(uri);

	addObject(resource, RDF.type, getCitagoraType(from.getType()));
	// addProperty(resource, FOAF.title, from.getTitle());
	addProperty(resource, FOAF.givenName, from.getGivenName());
	addProperty(resource, FOAF.familyName, from.getFamilyName());
	addProperty(resource, FOAF.name, from.getName());
	addProperty(resource, FOAF.accountName, from.getAccountName());
	addProperty(resource, FOAF.account, from.getAccount());
	addProperty(resource, FOAF.homepage, from.getHomePage());
	if (from.getIsAgent())//TODO ok to include multiple types?
	    addObject(resource, RDF.type, FOAF.Agent);
	if (from.getIsAuthor()) {

	}
	return resource;
    }

    private Resource addReview(Review from, int level) {
	Resource resource = addCitagoraObject(from, level);
	addProperty(resource, RdfReview.type, from.getRatingType());
	addObject(resource, RdfReview.reviewer, from.getReviewer(), level);
	addProperty(resource, RdfReview.rating, from.getRating());
	addProperty(resource, RdfReview.totalVotes, from.getTotalVotes());
	// reverse addObject(resource, null, from.getDocumentReviewed());

	return resource;
    }

    private Resource addCitagoraAnnotationBody(AnnotationBody from) {
	String uri = from.getUri();
	Resource resource = model.getResource(uri);

	addObject(resource, RDF.type, getCitagoraType(from.getType()));
	addProperty(resource, Content.characterEncoding,
		from.getCharacterEncoding());
	addProperty(resource, Content.chars, from.getChars());
	return resource;
    }

    public Resource addCitagoraAgent(CitagoraAgent from, int level) {
	Resource resource = addPerson(from, level);
	return resource;
    }

    public Resource addAuthor(Author from, int level) {
	Resource resource = addPerson(from, level);
	addObjects(resource, null, from.getAuthorReferences(), level);

	return resource;
    }

    public Resource addReference(Reference from, int level) {
	Resource resource = addCitagoraObject(from, level);

	addProperty(resource, null, from.getSource());
	addProperty(resource, DCTERMS.abstract_, from.getAbstract());
	addProperty(resource, DCTERMS.title, from.getTitle());
	addProperty(resource, BIBO.pageStart, from.getPageStart());
	addProperty(resource, BIBO.pageEnd, from.getPageEnd());
	addProperty(resource, BIBO.volume, from.getVolume());
	addProperty(resource, DCTERMS.date, from.getIssued());
	addProperty(resource, BIBO.pmid, from.getPmid());
	addProperty(resource, BIBO.doi, from.getDoi());
	addObject(resource, DCTERMS.isPartOf, from.isPartOf(), level);
	addObjects(resource, BIBO.authorList, from.getAuthors(), level);
	addObjects(resource, null, from.getCitationList(), level);
	addProperties(resource, DCTERMS.references, from.getSeeAlso());
	addProperty(resource, RdfReview.rating, from.getOverallRating());
	addProperty(resource, RdfReview.rating, from.getReadabilityRating());
	// addObjects(resource, RdfReview.rating, from.getContainers());
	// // note: we are driving the transformation from the citagora Document
	addProperty(resource, RdfReview.rating, from.getAccuracyRating());
	addProperty(resource, BIBO.issn, from.getIssn());
	addProperty(resource, BIBO.issue, from.getIssue());
	addProperty(resource, DCTERMS.publisher, from.getPublisher());
	addProperty(resource, BIBO.volume, from.getVolume());
	addProperty(resource, DCTERMS.creator, from.getAuthorString());

	return resource;

    }

    public Resource addRatingType(RatingType from) {
	String uri = null;// from.getUri(type);
	Resource resource = model.getResource(uri);
	addObject(resource, RDF.type, getCitagoraType(from.getType()));

	addProperty(resource, null, "");// RatingType.getUri(type));
	return resource;

    }

    public Resource addTag(Tag from, int level) {
	Resource resource = addAnnotation(from, level);
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
     * @param level TODO
     * @param annotations
     */
    private void addObjects(Resource resource, Property relationship,
	    List<?> targets, int level) {
	if (targets == null)
	    return;
	Iterator<?> iterator = targets.iterator();
	while (iterator.hasNext()) {
	    addObject(resource, relationship, (UriObject) iterator.next(), level);
	}
    }

    private void addObject(Resource resource, Property relationship,
	    Resource target) {
	System.out.println("addObject: " + resource.getURI() + " target: "
		+ target.getURI());
	RdfUtilities.addProperty(model, resource, relationship, target);
    }

    private void addObject(Resource resource, Property relationship,
	    UriObject target, int level) {
	Resource targetResource = add(target, level-1);
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

    private Resource getCitagoraType(String type) {
	if (Container.TYPE.equals(type))
	    return Citagora.documentType;
	if (Reference.TYPE.equals(type))
	    return BIBO.Document;
	if (Reference.JOURNAL_TYPE.equals(type))
	    return BIBO.Journal;
	if (Tag.TYPE.equals(type))
	    return Citagora.tagType;
	if (Comment.TYPE.equals(type))
	    return Citagora.commentType;
	if (Person.TYPE.equals(type))
	    return FOAF.OnlineAccount;
	if (CitagoraAgent.TYPE.equals(type))
	    return FOAF.Agent;
	if (Author.TYPE.equals(type))
	    return FOAF.Person;
	if (AnnotationBody.TYPE.equals(type))
	    return model.createResource(Content.ContentAsText.getURI());
	if (Review.TYPE.equals(type)){
	    return RdfReview.Review;
	}
	return model.createResource(type);
    }
}