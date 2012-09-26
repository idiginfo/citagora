package org.idiginfo.docsvc.controller.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapDocumentToReference;
import org.idiginfo.docsvc.svcapi.springer.SpringerApiParams;
import org.idiginfo.docsvc.svcapi.springer.SpringerService;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

public class TestCitagoraPersistence {

    CitagoraFactory factory = new CitagoraFactoryImpl();

    /**
     * @param args
     */
    public static void main(String[] args) {
	TestCitagoraPersistence tester = new TestCitagoraPersistence();
	tester.run(args);

    }

    private void run(String[] args) {
	CitagoraDocument doc = null;
	doc = createCitagoraDocument();
	// doc = (CitagoraDocument) factory.findCitagoraObject(12);

	// Reference ref = testSpringerDocument();
	// ref.setLanguage("French");
	factory.openTransaction();
	factory.merge(doc);
	// factory.merge(ref);
	factory.commitTransaction();
	EntityManager em = ((CitagoraFactoryImpl) factory).getEntityManager();
	em.refresh(doc);
	String rdf = writeCitagora(doc, null);
	FileWriter out;
	try {
	    out = new FileWriter("c:/dev/api samples/citagora_new.rdf");
	    out.write(rdf);
	    out.close();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// CitagoraObject doc2 = factory.findCitagoraObject(52);
	// System.out.println(doc2.getClass().getName());
    }

    private CitagoraDocument createCitagoraDocument() {
	// document.setId("http://citagora.com/documents/123456");
	Date nov2011 = new GregorianCalendar(2011, 11, 11).getTime();
	Date june2012 = new GregorianCalendar(2012, 6, 12).getTime();

	Person docgenerator = factory.createPerson();
	docgenerator.setAccountName("CitagoraSoftwareAgent");
	docgenerator.setAccount("http://citagora.com/softwareAgent");
	docgenerator.setPersonType("SoftwareAgent");

	CitagoraDocument document = factory.createCitagoraDocument();
	document.setSource("http://example.com/article");
	document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
	document.setCreated(nov2011);
	document.setGenerated(nov2011);
	document.setGenerator((CitagoraAgent) docgenerator);
	// TODO what is this?
	// document.setRatingType("http://purl.org/ontology/bibo/AcademicArticle");
	document.setWasAttributedTo("http://citagora.com/softwareAgent");

	Person reviewer = factory.createPerson();
	reviewer.setAccountName("a_user_foaf");

	// first review
	Review review = factory.createReview();
	document.addReview(review);
	// review.setDocumentReviewed(document);
	review.setRatingType(RatingType.getUri("overall"));
	review.setRating(4);
	review.setReviewer(reviewer);

	// second review
	review = factory.createReview();
	document.addReview(review);
	// review.setDocumentReviewed(document);
	review.setRatingType(RatingType.getUri("accuracy"));
	review.setTotalVotes(50);
	review.setRating(4);

	// third review
	review = factory.createReview();
	 document.addReview(review);
	//review.setDocumentReviewed(document);
	review.setRatingType(RatingType.getUri("originality"));
	review.setTotalVotes(10);
	review.setRating(3);

	Person annotator = factory.createPerson();
	annotator.setAccountName("registered_annotator");
	Person generator = factory.createCitagoraAgent();
	generator.setAccountName("http://citagora.com/annotator");

	// tag
	Tag tag = factory.createTag();
	document.addTag(tag);
	//tag.setTarget(document);
	tag.setChars("actual tag");
	tag.setCharacterEncoding("UTF-8");
	tag.setAnnotator(annotator);
	tag.setAnnotated(june2012);
	tag.setGenerator((CitagoraAgent) generator);
	tag.setGenerated(june2012);
	tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

	// Reference
	Reference reference = factory.createReference();
	 document.setIsAbout(reference);
	//reference.setLanguage("English");
	reference.addCitagoraDocument(document);
	reference
		.addSeeAlso("another link that also provides some information about this article");
	reference.setSource("http://example.com/article");
	reference.setTitle("Some Journal Article");
	reference.setSubject("some keyword");
	reference.setBiboType("http://purl.org/ontology/bibo/AcademicArticle");
	reference.setShortTitle("Short Article");
	reference
		.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
	reference.setDoi("doi id");
	reference.setUri("doi:doi id");
	reference.setPmid("pmid number");
	reference.setPublisher("a publisher as plain text");
	reference.setVolume("6");
	reference.setPageStart(8);
	reference.setPageEnd(8);
	reference.setPages("234 - 343");

	// commentor
	Person commentor = factory.createPerson();
	commentor.setAccount("CommentorOne");
	commentor.setAccountName("CommOne");
	commentor.setFamilyName("One");
	commentor.setGivenName("Comma");
	commentor.setHomePage("http://wwww.comment.org/~Comm1");
	commentor.setName("Comma N. One");
	commentor.setPersonType("Annonymous User");

	// comment
	Comment comment = factory.createComment();
	// document.addComment(comment);
	comment.setTarget(document);
	comment.setAnnotated(new GregorianCalendar(2012, 01, 01).getTime());
	comment.setAnnotator(commentor);
	comment.setCharacterEncoding("UTF-8");
	comment.setChars("string being commented upon");
	comment.setCommentType("content critique");
	comment.setCreated(new GregorianCalendar(2012, 02, 02).getTime());
	comment.setGenerated(new GregorianCalendar(2012, 02, 02).getTime());
	comment.setGenerator((CitagoraAgent) commentor);
	comment.setRating(4);
	comment.setAnnotator(annotator);
	comment.setModelVersion("0.1");
	comment.setRights("notate");
	comment.setSource("comment source");
	comment.setUri("comment uri");
	comment.setCommentType(RatingType.getUri("readability"));
	comment.setWasAttributedTo("hearsay");

	// replier
	Person replier = factory.createPerson();
	replier.setAccount("ReplyorOne");
	replier.setAccountName("ReplOne");
	replier.setFamilyName("One");
	replier.setGivenName("Repla");
	replier.setHomePage("http://wwww.comment.org/~Repl1");
	replier.setName("Repla N. One");
	replier.setPersonType("Annonymous User");

	// reply
	Reply reply = factory.createReply();
	comment.addReply(reply);
	reply.setAnnotated(new GregorianCalendar(2012, 03, 01).getTime());
	reply.setAnnotator(commentor);
	reply.setCharacterEncoding("UTF-8");
	reply.setChars("string being replyed to");
	reply.setCommentType("comment critique");
	reply.setCreated(new GregorianCalendar(2012, 03, 03).getTime());
	reply.setGenerated(new GregorianCalendar(2012, 03, 03).getTime());
	reply.setGenerator((CitagoraAgent) replier);
	reply.setRating(2);
	reply.setAnnotator(replier);
	reply.setModelVersion("0.1");
	reply.setRights("notate");
	reply.setSource("reply source");
	reply.setUri("reply uri");
	reply.setCommentType(RatingType.getUri("readability"));
	reply.setWasAttributedTo("hearsay");

	return document;

    }

    Reference testSpringerDocument() {
	SpringerService service = new SpringerService();
	ApiParams params = new SpringerApiParams();
	// params.setDoi("doi:10.1007/s11276-008-0131-4");
	params.setId("doi:10.1007/s11276-008-0131-4");
	Document document = service.getDocument(params);
	System.out.println(document.getId());

	List<Reference> references = CitagoraFactory.getFactory()
		.findReferences(document.getDoi());
	if (references != null && references.size() > 0)
	    return references.get(0);
	MapDocumentToReference documentMapper = new MapDocumentToReference();
	Reference reference = documentMapper.map(document);
	return reference;
    }

    public String writeCitagora(UriObject document, String version) {
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document);
	Model model = mapper.getModel();
	StringWriter out = new StringWriter();
	model.write(out, version);
	return out.toString();
    }
}
