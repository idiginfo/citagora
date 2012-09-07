package org.idiginfo.docsvc.svcapi.exploration;

import java.sql.Date;
import java.util.GregorianCalendar;

import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.svcapi.citagora.AnnotationBodyImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraAgentImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraDocumentImpl;
import org.idiginfo.docsvc.svcapi.citagora.PersonImpl;
import org.idiginfo.docsvc.svcapi.citagora.ReferenceImpl;
import org.idiginfo.docsvc.svcapi.citagora.TagImpl;

import org.idiginfo.docsvc.svcapi.citagora.ReviewImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TestCitagoraModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCitagoraModel tester = new TestCitagoraModel();
		tester.run();

	}

	private void run() {
		CitagoraDocumentImpl document = new CitagoraDocumentImpl();
		document.setId("http://citagora.com/documents/123456");
		document.setSource("http://example.com/article");
		document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
		// first review
		ReviewImpl review = new ReviewImpl();
		document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType(RatingType.getUri("overall"));
		review.setRating(4);
		PersonImpl person = new PersonImpl();
		review.setReviewer(person);
		person.setAccountName("a_user_foaf");
		// second review
		review = new ReviewImpl();
		document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(50);
		review.setRating(4);
		// third review
		review = new ReviewImpl();
		document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(10);
		review.setRating(3);

		// tag
		TagImpl tag = new TagImpl();
		document.addAnnotation(tag);
		tag.setTarget(document);
		AnnotationBodyImpl body = (AnnotationBodyImpl) tag.getBody();
		body.setChars("actual tag");
		body.setCharacterEncoding("UTF-8");
		PersonImpl annotator = new PersonImpl();
		annotator.setAccountName("registered_annotator");
		tag.setAnnotator(annotator);
		tag.setAnnotated(new GregorianCalendar(2012, 6, 12).getTime());
		CitagoraAgentImpl generator = new CitagoraAgentImpl();
		generator.setAccountName("http://citagora.com/annotator");
		tag.setGenerator(generator);
		tag.setGenerated(new GregorianCalendar(2012, 6, 12).getTime());
		tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

		// Reference
		ReferenceImpl reference = new ReferenceImpl();
		document.setIsAbout(reference);
		reference.addCitagoraDocument(document);
		reference
				.addSeeAlso("another link that also provides some information about this article");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String string = gson.toJson(document);
		System.out.println(string);

	}

}
