package org.idiginfo.docsvc.controller.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.jpa.citagora.ReferenceImpl;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
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

    CitagoraFactoryImpl factory = new CitagoraFactoryImpl();

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

	Reference ref = null;
	String requestDoi = "doi:10.1007/s11276-008-0131-4";

	//ref = getSpringerDocument(requestDoi);
	// ref.setUri(null);
	// ref.setDoi(ref.getDoi() + ":new2");
	EntityManager em = factory.getEntityManager();
	System.out.println("Contains before persist: " + em.contains(doc)
		+ " uri: " + doc.getUri());

	// ref.setLanguage("French");
	// factory.openTransaction();
	//factory.merge(doc);
	// factory.merge(ref);
	System.out.println("Contains after persist: " + em.contains(doc)
		+ " uri: " + doc.getUri());
	// factory.flush();
	System.out.println("Contains after flush: " + em.contains(doc)
		+ "uri: " + doc.getUri());

	// factory.commitTransaction();
	System.out.println("Contains after commit: " + em.contains(doc)
		+ " uri: " + doc.getUri());
	// factory.refresh(doc);
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

    void printInfo(String objName, String objUri) {
	System.out.println("+++Object " + objName + " : " + objUri);
    }

    private CitagoraDocument createCitagoraDocument() {
	// document.setId("http://citagora.com/documents/123456");
	Date nov2011 = new GregorianCalendar(2011, 11, 11).getTime();
	Date june2012 = new GregorianCalendar(2012, 6, 12).getTime();

	//factory.openTransaction();

	CitagoraAgent docgenerator = (CitagoraAgent) factory
		.createPerson(CitagoraAgent.class);
	factory.merge(docgenerator);
	docgenerator.setAccountName("CitagoraSoftwareAgent");
	docgenerator.setAccount("http://citagora.com/softwareAgent");
	docgenerator.setPersonType("SoftwareAgent");

	CitagoraDocument document = factory.createCitagoraDocument();
	System.out.println("doc uri: " + document.getUri());
	factory.merge(document);
	// factory.commitTransaction();
	System.out.println("doc uri: " + document.getUri());
	System.out.println("contains? "
		+ factory.getEntityManager().contains(document));
	// factory.refresh(document);
	document.setSource("http://example.com/article");
	document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
	document.setCreated(nov2011);
	document.setGenerated(nov2011);
	docgenerator.addAgentDocument(document);
	// TODO what is this?
	// document.setRatingType("http://purl.org/ontology/bibo/AcademicArticle");
	document.setWasAttributedTo("http://citagora.com/softwareAgent");

	Person reviewer = factory.createPerson();
	factory.merge(reviewer);
	reviewer.setAccountName("a_user_foaf");

	// first review
	Review review1 = factory.createReview();
	factory.merge(review1);
	document.addReview(review1);
	// review.setDocumentReviewed(document);
	review1.setRatingType(RatingType.getUri("overall"));
	review1.setRating(4);
	reviewer.addAgentReview(review1);
	// review.setReviewer(reviewer);

	// second review
	Review review2 = factory.createReview();
	factory.merge(review2);
	document.addReview(review2);
	// review.setDocumentReviewed(document);
	review2.setRatingType(RatingType.getUri("accuracy"));
	review2.setTotalVotes(50);
	review2.setRating(4);

	// third review
	Review review3 = factory.createReview();
	factory.merge(review3);
	document.addReview(review3);
	// review.setDocumentReviewed(document);
	review3.setRatingType(RatingType.getUri("originality"));
	review3.setTotalVotes(10);
	review3.setRating(3);

	Person annotator = factory.createPerson();
	factory.merge(annotator);
	annotator.setAccountName("registered_annotator");
	Person generator = factory.createCitagoraAgent();
	generator.setAccountName("http://citagora.com/annotator");

	// tag
	Tag tag = factory.createTag();
	factory.merge(tag);
	document.addTag(tag);
	// tag.setTarget(document);
	tag.setChars("actual tag");
	tag.setCharacterEncoding("UTF-8");
	// annotator.addAgentTag(tag);
	// tag.setAnnotator(annotator);
	tag.setAnnotated(june2012);
	tag.setGenerator((CitagoraAgent) generator);
	tag.setGenerated(june2012);
	tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

	// Reference
	Reference reference = factory.createReference();
	factory.merge(reference);
	reference.addCitagoraDocument(document);
	// reference.setLanguage("English");
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
	reference.setUri("doi:doi id:"+((ReferenceImpl)reference).getMyId());
	reference.setDoi(reference.getUri());
	reference.setPmid("pmid number"+((ReferenceImpl)reference).getMyId());
	reference.setPublisher("a publisher as plain text");
	reference.setVolume("6");
	reference.setPageStart(8);
	reference.setPageEnd(8);
	reference.setPages("234 - 343");

	// commentor
	Person commentor = factory.createPerson();
	factory.merge(commentor);
	commentor.setAccount("CommentorOne");
	commentor.setAccountName("CommOne");
	commentor.setFamilyName("One");
	commentor.setGivenName("Comma");
	commentor.setHomePage("http://wwww.comment.org/~Comm1");
	commentor.setName("Comma N. One");
	commentor.setPersonType("Annonymous User");

	// comment
	Comment comment = factory.createComment();
	factory.merge(comment);
	document.addComment(comment);
	// comment.setTarget(document);
	comment.setAnnotated(new GregorianCalendar(2012, 01, 01).getTime());
	// commentor.addAgentComment(comment);
	// comment.setAnnotator(commentor);
	comment.setCharacterEncoding("UTF-8");
	comment.setChars("string being commented upon");
	comment.setCommentType("content critique");
	comment.setCreated(new GregorianCalendar(2012, 02, 02).getTime());
	comment.setGenerated(new GregorianCalendar(2012, 02, 02).getTime());
	commentor.addAgentComment(comment);
	// comment.setGenerator((CitagoraAgent) commentor);
	comment.setRating(4);
	// comment.setAnnotator(annotator);
	comment.setModelVersion("0.1");
	comment.setRights("notate");
	comment.setSource("comment source");
	// comment.setUri("comment uri");
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
	replier.addAgentComment(reply);
	// reply.setGenerator((CitagoraAgent) replier);
	reply.setRating(2);
	// reply.setAnnotator(replier);
	reply.setModelVersion("0.1");
	reply.setRights("notate");
	reply.setSource("reply source");
	// reply.setUri("reply uri");
	reply.setCommentType(RatingType.getUri("readability"));
	reply.setWasAttributedTo("hearsay");

	//factory.commitTransaction();

	printInfo("docgenerator", docgenerator.getUri());
	printInfo("document", document.getUri());
	printInfo("reviewer", reviewer.getUri());
	printInfo("review1", review1.getUri());
	printInfo("review2", review2.getUri());
	printInfo("review3", review3.getUri());
	printInfo("annotator", annotator.getUri());
	printInfo("tag", tag.getUri());
	printInfo("commentor", commentor.getUri());
	printInfo("reference", reference.getUri());
	printInfo("comment", comment.getUri());
	printInfo("replier", replier.getUri());
	printInfo("reply", reply.getUri());

	return document;

    }

    Reference getSpringerDocument(String requestDoi) {
	List<Reference> references = CitagoraFactory.getFactory()
		.findReferences(requestDoi);
	// references = null;
	if (references != null && references.size() > 0)
	    return references.get(0);
	SpringerService service = new SpringerService();
	ApiParams params = new SpringerApiParams();
	// params.setDoi("doi:10.1007/s11276-008-0131-4");
	params.setId(requestDoi);
	Document document = service.getDocument(params);
	System.out.println(document.getId());

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
