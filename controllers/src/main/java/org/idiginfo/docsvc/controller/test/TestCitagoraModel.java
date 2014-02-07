package org.idiginfo.docsvc.controller.test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

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

public class TestCitagoraModel {

    CitagoraFactory factory = CitagoraFactory.getFactory();

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

	//Container document = createContainer();
    Container document = createCont();
	Reference ref = createRef();
	Person reviewer = createReviewer();
	Review rev = createReview(document, reviewer);
	Tag tag = createTag(document);
	
	
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document, -1);
	mapper.add(ref, 1);
	mapper.add(rev, 1);
	mapper.add(tag, 1);
	mapper.add(reviewer, 1);
	
	Model model = mapper.getModel();
	StringWriter out = new StringWriter();
	model.write(out, "RDF/XML");
	System.out.println(out.toString());
    
	//String rdf = writeCitagora(document, ref,  "RDF/XML");
	// System.out.println(rdf);
	
    }

    public String writeCitagora(UriObject document, UriObject ref, String version) {
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document, -1);
	mapper.add(ref, 1);
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
	reference.setBiboType("Journal");
	reference.setIssued(new GregorianCalendar(2013, 04, 10).getTime());
	reference.setAuthorString("some Author String");
	reference.setIssn("issn id");
	reference.setIsbn("isbn id");
	reference.setIssue("issue name/number");
	reference.setUrl("url");
	List<String> keylist = new ArrayList<>();
	keylist.add("keyword1");
	keylist.add("keyword2");
	keylist.add("keyword3");
	reference.setKeywords(keylist);
	List<String> meshlist = new ArrayList<>();
	meshlist.add("meshterm1");
	meshlist.add("meshterm2");
	meshlist.add("meshterm3");
	reference.setMeshTerms(meshlist);
	reference.setReadabilityRating(3);
	reference.setOverallRating(2);
	reference.setAccuracyRating(1);
	reference.setOriginalityRating(0);
	reference.setAggregationType("journal");
	reference.setArXivId("arXivId");
	reference.setCoverDate("Cover Date");
	reference.setEdition("edition");
	reference.seteIssn("eIssn");
	reference.setGenre("genre name");
	reference.setAuthorNotes("author notes");
	reference.setItemNumber("11");
	reference.setPublicationDate("publication date");
	reference.setRights("rights string");
	reference.setSeriesTitle("series title");

	return document;
    }

    private Container createCont() {
    	Container cont = factory.createContainer();
    	cont.setSource("http://example.com/article");
    	cont.setRights("http://www.nlm.nih.gov/databases/license/license.html");
    	cont.setUri("http://citagora.org/documents/1");
    	cont.setCreated(new GregorianCalendar(2013, 04, 16).getTime());
    	cont.setGenerated(new GregorianCalendar(2013, 04, 10).getTime());
    	cont.setSource("demo source");
    	cont.setWasAttributedTo("attributed to");
    	
    	return cont;
    }
    
    private Reference createRef() {
    	Reference reference = factory.createReference();
    	reference.setLanguage("English");
    	reference
    		.addSeeAlso("another link that also provides some information about this article");
    	reference.setSource("http://example.com/article");
    	reference.setTitle("Some Journal Article");
    	reference.setSubject("some keyword");

    	reference.setShortTitle("Short Article");
    	reference
    		.setAbstract("This is an abstract for a journal article. This article discusses something very important. This is an example.");
    	reference.setDoi("doi id");
    	reference.setUri("http://citagora.org/references/1");
    	reference.setPmid("pmid number");
    	// note identifier is multi-valued
    	// <dcterms:identifier>pmid:pmid number</dcterms:identifier>

    	reference.setPublisher("a publisher as plain text");
    	reference.setVolume("6");
    	reference.setPageStart(8);
    	reference.setPageEnd(8);
    	reference.setPages("234 - 343");
    	reference.setBiboType("http://idiginfo.org/Journal");
    	reference.setIssued(new GregorianCalendar(2013, 04, 10).getTime());
    	reference.setAuthorString("some Author String");
    	reference.setIssn("http://citagora.org/issn/1");
    	reference.setIsbn("isbn id");
    	reference.setIssue("issue name/number");
    	reference.setUrl("http://citagora.org/refurl/1");
    	List<String> keylist = new ArrayList<>();
    	keylist.add("keyword1");
    	keylist.add("keyword2");
    	keylist.add("keyword3");
    	reference.setKeywords(keylist);
    	List<String> meshlist = new ArrayList<>();
    	meshlist.add("meshterm1");
    	meshlist.add("meshterm2");
    	meshlist.add("meshterm3");
    	reference.setMeshTerms(meshlist);
    	reference.setReadabilityRating(3);
    	reference.setOverallRating(2);
    	reference.setAccuracyRating(1);
    	reference.setOriginalityRating(0);
    	reference.setAggregationType("journal");
    	reference.setArXivId("arXivId");
    	reference.setCoverDate("Cover Date");
    	reference.setEdition("edition");
    	reference.seteIssn("eIssn");
    	reference.setGenre("genre name");
    	reference.setAuthorNotes("author notes");
    	reference.setItemNumber("11");
    	reference.setPublicationDate("publication date");
    	reference.setRights("rights string");
    	reference.setSeriesTitle("series title");

    	return reference;    	
    }
    
    private Review createReview(Container document, Person person) {
    	Review review = factory.createReview();
    	review.setUri("http://citagora.org/review/1");
    	review.setDocumentReviewed(document);
    	review.setRatingType(RatingType.getUri("overall"));
    	review.setRating(4);
    	review.setTotalVotes(11);
    	review.setReviewer(person);

    	return review;
    }

    private Person createReviewer() {
    	Person person = factory.createPerson();
    	person.setIsPerson(true);
    	person.setAccountName("a_user_foaf");
    	person.setAccount("foaf_account");
    	person.setHomePage("http://reviewer.com/a_user");

    	return person;
    }
    
    private Tag createTag(Container document) {
    	Tag tag = factory.createTag();
    	tag.setUri("http://citagora.org/tag/1");
    	tag.setTarget(document);
    	tag.setChars("actual tag");
    	tag.setCharacterEncoding("UTF-8");
    	Person annotator = factory.createPerson();
    	annotator.setAccountName("registered_annotator");
    	tag.setAnnotator(annotator);
    	tag.setAnnotated(new GregorianCalendar(2013, 4, 20).getTime());
    	Person generator = factory.createPerson();
    	generator.setAccountName("http://citagora.com/annotator");
    	tag.setGenerator((CitagoraAgent) generator);
    	tag.setGenerated(new GregorianCalendar(2013, 4, 20).getTime());
    	tag.setModelVersion("http://www.openannotation.org/spec/core/20120509.html");

    	return tag;
    }
    
    void testSpringDocument() {
    	ServiceFactory serviceFactory = ServiceFactory.getFactory();
	DocService service = serviceFactory.createService(ServiceFactory.COLLECTION_SPRINGER);
	ApiParams params = serviceFactory.createApiParams(ServiceFactory.COLLECTION_SPRINGER);
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
	mapper.add(reference, -1);
	//String rdf = writeCitagora(reference, "TURTLE");
	//System.out.println(rdf);

    }
}
