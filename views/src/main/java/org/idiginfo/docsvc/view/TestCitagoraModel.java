package org.idiginfo.docsvc.view;

import java.io.StringWriter;
import java.util.GregorianCalendar;

import org.idiginfo.docsvc.mapping.DocumentReference;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.svcapi.citagora.AnnotationBodyImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraAgentImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraDocumentImpl;
import org.idiginfo.docsvc.svcapi.citagora.PersonImpl;
import org.idiginfo.docsvc.svcapi.citagora.ReferenceImpl;
import org.idiginfo.docsvc.svcapi.citagora.ReviewImpl;
import org.idiginfo.docsvc.svcapi.citagora.TagImpl;
import org.idiginfo.docsvc.svcapi.springer.SpringerApiParams;
import org.idiginfo.docsvc.svcapi.springer.SpringerService;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hp.hpl.jena.rdf.model.Model;

public class TestCitagoraModel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCitagoraModel tester = new TestCitagoraModel();
		tester.createCitagoraDocument();
		tester.testSpringDocument();
	}

	public String writeCitagora(Object document,
			String version) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.add(document);
		Model model = mapper.getModel();
		StringWriter out = new StringWriter();
		model.write(out, version);
		return out.toString();
	}

	void testSpringDocument() {
		SpringerService service = new SpringerService();

		ApiParams params = new SpringerApiParams();
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId("doi:10.1007/s11276-008-0131-4");
		Document document = service.getDocument(params);
		System.out.println(document.getId());

		DocumentReference documentMapper = new DocumentReference();
		Reference reference = documentMapper.map(document);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String string = gson.toJson(reference);
		System.out.println(string);

		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.addReference(reference);

	}

	private void createCitagoraDocument() {
		CitagoraDocumentImpl document = new CitagoraDocumentImpl();
		// document.setId("http://citagora.com/documents/123456");
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
		reference.setLanguage("English");
		reference.addCitagoraDocument(document);
		reference
				.addSeeAlso("another link that also provides some information about this article");
		reference.setUri("http://example.com/article");
		reference.setTitle("Some Journal Article");
		reference.setSubject("some keyword");

		reference.setShortTitle("Short Article");
		reference
				.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
		reference.setDoi("doi id");
		reference.setId("doi:doi id");
		reference.setPmid("pmid number");
		// note identifier is multi-valued
		// <dcterms:identifier>pmid:pmid number</dcterms:identifier>

		reference.setPublisher("a publisher as plain text");
		reference.setVolume("6");
		reference.setPageStart(8);
		reference.setPageEnd(8);
		reference.setPages("234 - 343");

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String string = gson.toJson(document);
		System.out.println(string);

		String rdf = writeCitagora(document, null);
		System.out.println(rdf);

	}

}
