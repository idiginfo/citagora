package org.idiginfo.docsvc.controller.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.security.auth.Refreshable;

import java.io.IOException;
import java.io.PrintWriter;

import org.idiginfo.docsvc.controller.request.DocServicesParams;
import org.idiginfo.docsvc.controller.request.RequestProcessor;
import org.idiginfo.docsvc.model.ServiceFactory;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.view.JsonWriter;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Class to test generation of JSON from Citagora Container object 
 * 
 * @author griccardi
 * 
 */

public class TestCitagoraJSON {

	CitagoraFactory factory = new CitagoraFactoryImpl();

	public TestCitagoraJSON() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCitagoraJSON tester = new TestCitagoraJSON();
		tester.testContainer();
	}

	private void testContainer() {
		String body = null;
		//Container document = createContainer();
	    //JsonWriter jsonWriter = new JsonWriter();
	    //body = jsonWriter.write(document);
		//JsonWriter jsonWriter = new JsonWriter();
		//Reference reference = createReference();
	    //body = jsonWriter.write(reference);
		//System.out.println(body);
		//Review review = createReview();
		//body = jsonWriter.write(review);
		//System.out.println(body);
		//testReference();
		//testReview();
		//testTag();
		//testComment();
		//testReply();
		testAll();
	}

	private void testAll() {
		String body = null;
		JsonWriter jsonWriter = new JsonWriter();
		Container document = factory.createContainer();
		document.setUri("ids.idiginfo.org/123456");
		document.setSource("http://example.com/article");
		document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
		Reference reference = createReference();
		reference.addContainer(document);
		document.setIsAbout(reference);
		Review review = createReview();
		review.setDocumentReviewed(document);
		Tag tag = createTag();
		tag.setTarget(document);
		document.addTag(tag);
		Comment comment = createComment();
		Reply reply = createReply();
		comment.addReply(reply);
		document.addComment(comment);
		body = jsonWriter.write(document);
		System.out.println(body);
		
	}
	

	private Person createAuthor1() {
		Person testAuthor = factory.createPerson();
		testAuthor.setAccountName("registered_author_1");
		testAuthor.setAccount("AuthorOne");
		testAuthor.setAccountName("AuthOne");
		testAuthor.setFamilyName("Aone");
		testAuthor.setGivenName("Autha");
		testAuthor.setHomePage("http://wwww.author.org/~Auth1");
		testAuthor.setName("Autha A Aone");
		testAuthor.setPersonType("Author");
		testAuthor.setIsAuthor(true);
	
		return testAuthor;
	}

	private Person createAuthor2() {
		Person testAuthor = factory.createPerson();
		testAuthor.setAccountName("registered_author_2");
		testAuthor.setAccount("AuthorTwo");
		testAuthor.setAccountName("AuthTwo");
		testAuthor.setFamilyName("Atwo");
		testAuthor.setGivenName("Authb");
		testAuthor.setHomePage("http://wwww.author.org/~Auth2");
		testAuthor.setName("Authb B Atwo");
		testAuthor.setPersonType("Author");
		testAuthor.setIsAuthor(true);
	
		return testAuthor;
	}
	
	private Reference createReference() {
		Reference testReference = factory.createReference();
		testReference.setLanguage("English");
		testReference
				.addSeeAlso("another link that also provides some information about this article");
		testReference.setSource("http://example.com/article");
		testReference.setTitle("Some Journal Article");
		testReference.setSubject("some keyword");

		testReference.setShortTitle("Short Article");
		testReference
				.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
		testReference.setDoi("doi id");
		testReference.setUri("doi:doi id");
		testReference.setPmid("pmid number");
		// note identifier is multi-valued
		// <dcterms:identifier>pmid:pmid number</dcterms:identifier>

		testReference.setPublisher("a publisher as plain text");
		testReference.setVolume("6");
		testReference.setPageStart(8);
		testReference.setPageEnd(8);
		testReference.setPages("234 - 343");
		testReference.setBiboType("Journal");
		testReference.setIssued(new GregorianCalendar(2013, 04, 10).getTime());
		testReference.setAuthorString("some Author String");
		testReference.setIssn("issn id");
		testReference.setIsbn("isbn id");
		testReference.setIssue("issue name/number");
		testReference.setUrl("url");
		List<String> keylist = new ArrayList<>();
		keylist.add("keyword1");
		keylist.add("keyword2");
		keylist.add("keyword3");
		testReference.setKeywords(keylist);
		List<String> meshlist = new ArrayList<>();
		meshlist.add("meshterm1");
		meshlist.add("meshterm2");
		meshlist.add("meshterm3");
		testReference.setMeshTerms(meshlist);
		testReference.setReadabilityRating(3);
		testReference.setOverallRating(2);
		testReference.setAccuracyRating(1);
		testReference.setOriginalityRating(0);
		testReference.setAggregationType("journal");
		testReference.setArXivId("arXivId");
		testReference.setCoverDate("Cover Date");
		testReference.setEdition("edition");
		testReference.seteIssn("eIssn");
		testReference.setGenre("genre name");
		testReference.setAuthorNotes("author notes");
		testReference.setItemNumber("11");
		testReference.setPublicationDate("publication date");
		testReference.setRights("rights string");
		testReference.setSeriesTitle("series title");
		Person testAuthor1 = createAuthor1();
		Person testAuthor2 = createAuthor2();
		testReference.addAuthor((Author) testAuthor1);
		testReference.addAuthor((Author) testAuthor2);

		return testReference;
	}

	private Review createReview() {
		Review testReview = factory.createReview();
		//review.setDocumentReviewed(document);
		testReview.setRatingType(RatingType.getUri("overall"));
		testReview.setRating(4);
		testReview.setTotalVotes(11);
		Person person = factory.createPerson();
		testReview.setReviewer(person);
		person.setAccount("ReviewerOne");
		person.setAccountName("ReviewOne");
		person.setFamilyName("Rone");
		person.setGivenName("Rvua");
		person.setHomePage("http://wwww.author.org/~Revu1");
		person.setName("Rvua R Rone");
		person.setPersonType("Reviewer");
		person.setIsPerson(true);
		
		return testReview;
	}
	
	private Person createTagger() {
		Person testTagger = factory.createPerson();
		testTagger.setAccountName("registered_annotator_t");
		testTagger.setAccount("TaggerOne");
		testTagger.setAccountName("TagOne");
		testTagger.setFamilyName("Tone");
		testTagger.setGivenName("Taga");
		testTagger.setHomePage("http://wwww.comment.org/~Tag1");
		testTagger.setName("Taga T Tone");
		testTagger.setPersonType("Annonymous User");
	
		return testTagger;
	}
	
	private Tag createTag() {
		Tag testTag = factory.createTag();
		testTag.setChars("actual tag");
		testTag.setTags("tags string");
		testTag.setCharacterEncoding("UTF-8");
		testTag.setContext("context string");
		testTag.setSpecifier("specifier string");
		testTag.setGenerated(new GregorianCalendar(2013, 4, 10).getTime());
		testTag.setModelVersion("http://www.openannotation.org/spec/core/");
		Person testTagger = createTagger();
		testTag.setAnnotator(testTagger);
		testTag.setAnnotated(new GregorianCalendar(2013, 4, 10).getTime());
		testTag.setGenerator((CitagoraAgent) testTagger);

		return testTag;
	}

	private Person createCommentor() {
		Person testCommentor = factory.createPerson();
		testCommentor.setAccountName("registered_annotator_c");
		testCommentor.setAccount("CommentorOne");
		testCommentor.setAccountName("CommOne");
		testCommentor.setFamilyName("Cone");
		testCommentor.setGivenName("Commaa");
		testCommentor.setHomePage("http://wwww.comment.org/~Comm1");
		testCommentor.setName("Comma C. One");
		testCommentor.setPersonType("Annonymous User");
	
		return testCommentor;
	}
	
	private Comment createComment() {
		Comment testComment = factory.createComment();
		testComment.setAnnotated(new GregorianCalendar(2012, 01, 01).getTime());
		testComment.setCharacterEncoding("UTF-8");
		testComment.setChars("string being commented upon");
		testComment.setCommentType("content critique");
		testComment.setCreated(new GregorianCalendar(2012, 02, 02).getTime());
		testComment.setGenerated(new GregorianCalendar(2012, 02, 03).getTime());
		testComment.setRating(4);
		testComment.setModelVersion("0.1");
		testComment.setRights("notate");
		testComment.setSource("comment source");
		testComment.setUri("comment uri");
		testComment.setWasAttributedTo("hearsay");
		Person testCommentor = createCommentor();
		testCommentor.addAgentComment(testComment);
		testComment.setReviewer((CitagoraAgent) testCommentor);

		return testComment;
	}
	
	private Person createReplier() {
		Person testReplier = factory.createPerson();
		testReplier.setAccount("ReplyorOne");
		testReplier.setAccountName("ReplOne");
		testReplier.setFamilyName("One");
		testReplier.setGivenName("Repla");
		testReplier.setHomePage("http://wwww.comment.org/~Repl1");
		testReplier.setName("Repla N. One");
		testReplier.setPersonType("Annonymous User");
		
		return testReplier;
	}
	
	private Reply createReply() {
		Person commentor = factory.createPerson();
		commentor.setAccountName("registered_annotator");
		Person testReplier = createReplier();
		Reply testReply = factory.createReply();
		testReply.setAnnotated(new GregorianCalendar(2012, 03, 01).getTime());
		testReply.setAnnotator(commentor);
		testReply.setCharacterEncoding("UTF-8");
		testReply.setChars("string being replyed to");
		testReply.setCommentType("comment critique");
		testReply.setCreated(new GregorianCalendar(2012, 03, 04).getTime());
		testReply.setGenerated(new GregorianCalendar(2012, 03, 05).getTime());
		testReplier.addAgentComment(testReply);
		testReply.setReviewer((CitagoraAgent) testReplier);
		testReply.setRating(2);
		testReply.setAnnotator(testReplier);
		testReply.setModelVersion("0.1");
		testReply.setRights("notate");
		testReply.setSource("reply source");
		testReply.setUri("reply uri");
		testReply.setWasAttributedTo("hearsay");

		return testReply;
	}
}
/*
private void testReference() {
String body = null;
JsonWriter jsonWriter = new JsonWriter();
Reference reference = createReference();
body = jsonWriter.write(reference);
System.out.println(body);

}

private void testReview() {
String body = null;
JsonWriter jsonWriter = new JsonWriter();
Review review = createReview();
body = jsonWriter.write(review);
System.out.println(body);
}

private void testTag() {
String body = null;
JsonWriter jsonWriter = new JsonWriter();
Tag tag = createTag();
body = jsonWriter.write(tag);
System.out.println(body);
}

private void testComment() {
String body = null;
JsonWriter jsonWriter = new JsonWriter();
Comment comment = createComment();
body = jsonWriter.write(comment);
System.out.println(body);
}

private void testReply() {
String body = null;
JsonWriter jsonWriter = new JsonWriter();
Reply reply = createReply();
body = jsonWriter.write(reply);
System.out.println(body);
}
*/