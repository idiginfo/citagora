package org.idiginfo.docsvc.controller.test;

import java.io.StringWriter;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.svcapi.springer.SpringerApiParams;
import org.idiginfo.docsvc.svcapi.springer.SpringerService;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

public class TestCitagoraModel {

    CitagoraFactory factory = new CitagoraFactoryImpl();
    private EntityManagerFactory emf;
    private EntityManager em;

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
	emf = Persistence.createEntityManagerFactory("repositories");
	em = emf.createEntityManager();
	em.getTransaction().begin();

	Container document = createContainer();
	// Gson gson = new GsonBuilder().setPrettyPrinting().create();
	// String string = gson.toJson(document);
	// System.out.println(string);
	em.persist(document);
	em.getTransaction().commit();

	String rdf = writeCitagora(document, null);
	System.out.println(rdf);
    }

    public String writeCitagora(UriObject document, String version) {
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document);
	Model model = mapper.getModel();
	StringWriter out = new StringWriter();
	model.write(out, version);
	return out.toString();
    }

    private Container createContainer() {
	Container document = factory.createContainer();
	// document.setId("http://citagora.com/documents/123456");
	document.setSource("http://example.com/article");
	document.setRights("http://www.nlm.nih.gov/databases/license/license.html");
	// first review
	Review review = factory.createReview();
	review.setDocumentReviewed(document);
	review.setRatingType(RatingType.getUri("overall"));
	review.setRating(4);
	Person person = factory.createPerson();
	review.setReviewer(person);
	person.setAccountName("a_user_foaf");
	// second review
	review = factory.createReview();
	review.setDocumentReviewed(document);
	review.setRatingType("overall");
	review.setTotalVotes(50);
	review.setRating(4);
	// third review
	review = factory.createReview();
	review.setDocumentReviewed(document);
	review.setRatingType("overall");
	review.setTotalVotes(10);
	review.setRating(3);

	// tag
	Tag tag = factory.createTag();
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
	SpringerService service = new SpringerService();

	ApiParams params = new SpringerApiParams();
	// params.setDoi("doi:10.1007/s11276-008-0131-4");
	params.setId("doi:10.1007/s11276-008-0131-4");
	Document document = service.getDocument(params);
	System.out.println(document.getId());

	MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
	Reference reference = documentMapper.map(document);
	// Gson gson = new GsonBuilder().setPrettyPrinting().create();
	// String string = gson.toJson(reference);
	// System.out.println(string);

	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(reference);
	String rdf = writeCitagora(reference, null);
	System.out.println(rdf);

    }
}
