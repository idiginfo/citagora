package org.idiginfo.docsvc.controller.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.GregorianCalendar;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;
import org.opensaml.ws.wssecurity.impl.ReferenceImpl;

import com.hp.hpl.jena.rdf.model.Model;

public class TestCitagoraPersistence {

	CitagoraFactory factory = CitagoraFactory.getFactory();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCitagoraPersistence tester = new TestCitagoraPersistence();
		tester.run(args);

	}

	private void run(String[] args) {
		Container doc = null;
		doc = createContainer();
		// doc = (Container) factory.findCitagoraObject(12);

		// String requestDoi = "doi:10.1007/s11276-008-0131-4";

		// doc = getSpringerDocument(requestDoi);
		String rdf = writeCitagora(doc, null, -1);
		FileWriter out;
		try {
			out = new FileWriter("c:/dev/api samples/citagora_2.rdf");
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

	private Container createContainer() {
		// document.setId("http://citagora.com/documents/123456");
		Date nov2011 = new GregorianCalendar(2011, 11, 11).getTime();
		Date june2012 = new GregorianCalendar(2012, 6, 12).getTime();

		// factory.openTransaction();

		CitagoraAgent docgenerator = (CitagoraAgent) factory
				.createPerson(CitagoraAgent.class);
		factory.merge(docgenerator);
		docgenerator.setAccountName("CitagoraSoftwareAgent");
		docgenerator.setAccount("http://citagora.com/softwareAgent");
		docgenerator.setIsAgent(true);

		Container document = factory.createContainer();
		System.out.println("doc uri: " + document.getUri());
		factory.merge(document);
		// factory.commitTransaction();
		System.out.println("doc uri: " + document.getUri());
		System.out.println("contains? " + factory.contains(document));
		// factory.refresh(document);
		document.setSource("http://example.com/article");
		document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
		document.setCreated(nov2011);
		document.setGenerated(nov2011);
		docgenerator.addAgentDocument(document);
		document.setGenerator(docgenerator);
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
		review1.setDocumentReviewed(document);
		review1.setRatingType(RatingType.getUri("overall"));
		review1.setRating(4);
		reviewer.addAgentReview(review1);
		review1.setReviewer(reviewer);

		// second review
		Review review2 = factory.createReview();
		factory.merge(review2);
		document.addReview(review2);
		review2.setDocumentReviewed(document);
		review2.setRatingType(RatingType.getUri("accuracy"));
		review2.setTotalVotes(50);
		review2.setRating(4);

		// third review
		Review review3 = factory.createReview();
		factory.merge(review3);
		document.addReview(review3);
		review3.setDocumentReviewed(document);
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
		tag.setTarget(document);
		tag.setChars("actual tag");
		tag.setCharacterEncoding("UTF-8");
		annotator.addAgentTag(tag);
		tag.setAnnotator(annotator);
		tag.setAnnotated(june2012);
		tag.setGenerator((CitagoraAgent) generator);
		tag.setGenerated(june2012);
		tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

		// Reference
		Reference reference = factory.createReference();
		factory.merge(reference);
		reference.setLanguage("English");
		reference.addContainer(document);
		document.setIsAbout(reference);
		reference
				.addSeeAlso("another link that also provides some information about this article");
		reference.setSource("http://example.com/article");
		reference.setTitle("Some Journal Article");
		reference.setSubject("some keyword");
		reference.setBiboType("http://purl.org/ontology/bibo/AcademicArticle");
		reference.setShortTitle("Short Article");
		reference
				.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
		reference.setUri("doi:doi id:" + ((Reference) reference).getMyId());
		reference.setDoi(reference.getUri());
		reference.setPmid("pmid number" + ((Reference) reference).getMyId());
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
		commentor.setIsAgent(true);

		// comment
		Comment comment = factory.createComment();
		factory.merge(comment);
		document.addComment(comment);
		comment.setTarget(document);
		comment.setAnnotated(new GregorianCalendar(2012, 01, 01).getTime());
		commentor.addAgentComment(comment);
		comment.setAnnotator(commentor);
		comment.setCharacterEncoding("UTF-8");
		comment.setChars("string being commented upon");
		comment.setCommentType("content critique");
		comment.setCreated(new GregorianCalendar(2012, 02, 02).getTime());
		comment.setGenerated(new GregorianCalendar(2012, 02, 02).getTime());
		commentor.addAgentComment(comment);
		comment.setGenerator((CitagoraAgent) commentor);
		comment.setRating(4);
		comment.setAnnotator(annotator);
		comment.setModelVersion("0.1");
		comment.setRights("notate");
		comment.setSource("comment refSource");
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
		reply.setTarget(comment);
		reply.setAnnotated(new GregorianCalendar(2012, 03, 01).getTime());
		reply.setAnnotator(commentor);
		reply.setCharacterEncoding("UTF-8");
		reply.setChars("string being replyed to");
		reply.setCommentType("comment critique");
		reply.setCreated(new GregorianCalendar(2012, 03, 03).getTime());
		reply.setGenerated(new GregorianCalendar(2012, 03, 03).getTime());
		replier.addAgentComment(reply);
		reply.setGenerator((CitagoraAgent) replier);
		reply.setRating(2);
		reply.setAnnotator(replier);
		reply.setModelVersion("0.1");
		reply.setRights("notate");
		reply.setSource("reply refSource");
		// reply.setUri("reply uri");
		reply.setCommentType(RatingType.getUri("readability"));
		reply.setWasAttributedTo("hearsay");

		// factory.commitTransaction();

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

	Container getSpringerDocument(String requestDoi) {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		DocService service = serviceFactory
				.createService(ServiceFactory.COLLECTION_SPRINGER);
		factory.openTransaction();
		ApiParams params = serviceFactory
				.createApiParams(ServiceFactory.COLLECTION_SPRINGER);
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId(requestDoi);
		Document document = service.getDocument(params);
		System.out.println(document.getId());

		MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
		Container container = documentMapper.createContainer(null, document);
		container.getIsAbout().addSeeAlso("see 1");
		container.getIsAbout().addSeeAlso("see 2");
		factory.commitTransaction();
		return container;
	}

	public String writeCitagora(UriObject document, String version, int level) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.add(document, level);
		Model model = mapper.getModel();
		StringWriter out = new StringWriter();
		model.write(out, version);
		return out.toString();
	}
}
