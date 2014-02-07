package org.idiginfo.docsvc.view;

import java.io.StringWriter;
import java.util.GregorianCalendar;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Class to test Citagora model
 * 
 * @author griccardi
 * 
 */

public class TestCitagoraModel {

	CitagoraFactory factory = CitagoraFactory.getFactory();

	// private EntityManagerFactory emf;
	// private EntityManager em;

	public TestCitagoraModel() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestCitagoraModel tester = new TestCitagoraModel();
		tester.testContainer();
		// tester.testSpringDocument();
	}

	private void testContainer() {
		factory.openTransaction();

		Container container = createContainer();
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// String string = gson.toJson(document);
		// System.out.println(string);
		factory.persist(container);
		factory.commitTransaction();

		String rdf = writeCitagora(container, "TURTLE");
		System.out.println(rdf);
	}

	public String writeCitagora(UriObject document, String version) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.add(document, -1);
		Model model = mapper.getModel();
		StringWriter out = new StringWriter();
		model.write(out, version);
		return out.toString();
	}

	private Container createContainer() {
		CitagoraFactory factory = CitagoraFactory.getFactory();
		Container document = factory.createContainer();
		// document.setId("http://citagora.com/documents/123456");
		document.setSource("http://example.com/article");
		document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
		// first review
		Review review = factory.createReview();
		// document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType(RatingType.getUri("overall"));
		review.setRating(4);
		Person person = factory.createPerson();
		review.setReviewer(person);
		person.setAccountName("a_user_foaf");
		// second review
		review = factory.createReview();
		// document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(50);
		review.setRating(4);
		// third review
		review = factory.createReview();
		// document.addReview(review);
		review.setDocumentReviewed(document);
		review.setRatingType("overall");
		review.setTotalVotes(10);
		review.setRating(3);

		// tag
		Tag tag = factory.createTag();
		// document.addTag(tag);
		tag.setTarget(document);
		tag.setChars("actual tag");
		tag.setCharacterEncoding("UTF-8");
		Person annotator = factory.createPerson();
		annotator.setAccountName("registered_annotator");
		tag.setAnnotator(annotator);
		tag.setAnnotated(new GregorianCalendar(2012, 6, 12).getTime());
		Person generator = factory.createPerson();
		generator.setAccountName("http://citagora.com/annotator");
		tag.setGenerator((CitagoraAgent) generator);
		tag.setGenerated(new GregorianCalendar(2012, 6, 12).getTime());
		tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

		// Reference
		Reference reference = factory.createReference();
		document.setIsAbout(reference);
		reference.setLanguage("English");
		// reference.addContainer(document);
		document.setIsAbout(reference);
		reference
				.addSeeAlso("another link that also provides some information about this article");
		reference.setSource("http://example.com/article");
		reference.setTitle("Some Journal Article");
		reference.setSubject("some keyword");

		reference.setShortTitle("Short Article");
		reference
				.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
		reference.setDoi("doi id");
		reference.setUri("doi:doi id");
		reference.setPmid("pmid number");
		// note identifier is multi-valued
		// <dcterms:identifier>pmid:pmid number</dcterms:identifier>

		reference.setPublisher("a publisher as plain text");
		reference.setVolume("6");
		reference.setPageStart(8);
		reference.setPageEnd(8);
		reference.setPages("234 - 343");

		return document;
	}

	void testSpringDocument() {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		DocService service = serviceFactory
				.createService(ServiceFactory.COLLECTION_SPRINGER);
		ApiParams params = serviceFactory
				.createApiParams(ServiceFactory.COLLECTION_SPRINGER);
		// params.setDoi("doi:10.1007/s11276-008-0131-4");
		params.setId("doi:10.1007/s11276-008-0131-4");
		Document document = service.getDocument(params);
		System.out.println(document.getId());

		MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
		Container container = documentMapper.createContainer(null, document);
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// String string = gson.toJson(reference);
		// System.out.println(string);

		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.add(container, 1);
		String rdf = writeCitagora(container, null);
		System.out.println(rdf);

	}
}
