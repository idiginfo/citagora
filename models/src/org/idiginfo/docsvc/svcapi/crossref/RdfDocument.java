package org.idiginfo.docsvc.svcapi.crossref;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.vocabulary.BIBO;
import org.idiginfo.docsvc.model.vocabulary.DCTERMS;
import org.idiginfo.docsvc.model.vocabulary.RDF;

import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.vocabulary.FOAF;

public class RdfDocument implements Document {

    Resource document;
    Model model;
    Resource journal;
    List<Resource> creators = null;

    // The code in comments below may be needed to get information from Prism
    // properties
    // So far, all Prism properties seem to be duplicated as BIBO
    // static final Model MODEL = ModelFactory.createDefaultModel();
    // static final String PRISM =
    // "http://prismstandard.org/namespaces/basic/2.1";
    // static final Property ENDING_PAGE = MODEL.getProperty(PRISM +
    // "endingPage");
    // static final Property STARTING_PAGE = MODEL.getProperty(PRISM
    // + "startingPage");
    // static final Property ISSN = MODEL.getProperty(PRISM + "issn");
    // static final Property ISBN = MODEL.getProperty(PRISM + "isbn");
    // static final Property EISSN = MODEL.getProperty(PRISM + "eIssn");

    public RdfDocument(Model model, Resource document) {
	this.document = document;
	this.model = model;
	// <ns0:isPartOf rdf:resource="http://id.crossref.org/issn/1476-4687"/>
	journal = document.getPropertyResourceValue(DCTERMS.isPartOf);
    }

    public Resource getRdfDocument() {
	return document;
    }

    @Override
    public String getAbstractText() {
	return null;
    }

    @Override
    public String getAggregationType() {
	return null;
    }

    @Override
    public Annotation getAnnotation(int i) {
	return null;
    }

    @Override
    public Annotation[] getAnnotations() {
	return null;
    }

    @Override
    public String getarXivId() {
	return null;
    }

    public List<Resource> getCreators() {
	if (creators != null)
	    return creators;
	creators = new ArrayList<Resource>();
	StmtIterator creatorStmts = document.listProperties(DCTERMS.creator);
	while (creatorStmts.hasNext()) {
	    Statement creatorStmt = creatorStmts.next();
	    creators.add(creatorStmt.getResource());
	}
	return creators;
    }

    @Override
    public List<String> getAuthorList() {
	List<Resource> creators = getCreators();
	if (creators == null)
	    return null;
	List<String> authors = new ArrayList<String>();
	for (Resource creator : creators) {
	    String id = creator.getURI();
	    Resource author = model.getResource(id);
	    StmtIterator props = author.listProperties();
	    while (props.hasNext()) {
		Statement stmt = props.next();
		System.out.println("property: " + stmt.getPredicate().getURI()
			+ " value: " + stmt.getObject().toString());
	    }
	    String authorName = getString(author, FOAF.name);
	    authors.add(authorName);
	}
	return authors;
    }

    @Override
    public String getAuthorNotes() {
	return null;
    }

    @Override
    public String getAuthors() {
	List<String> authors = getAuthorList();
	return StringUtils.join(authors, ", ");
    }

    @Override
    public String getCopyright() {
	return null;
    }

    @Override
    public String getCoverDate() {
	return null;
    }

    @Override
    public String getDate() {
	return getString(document, DCTERMS.date);
    }

    @Override
    public Date getDateObject() {
	try {
	    XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
		    .newXMLGregorianCalendar(getDate());
	    return calendar.toGregorianCalendar().getTime();
	} catch (DatatypeConfigurationException e) {
	    e.printStackTrace();
	}
	return null;
    }

    @Override
    public String getDoi() {
	return getString(document, BIBO.doi);
    }

    @Override
    public String getEdition() {
	String edition = getString(document, BIBO.edition);
	if (edition != null)
	    return edition;
	return getString(journal, BIBO.edition);
    }

    @Override
    public String geteIssn() {
	return getString(journal, BIBO.eissn);
    }

    @Override
    public String getGenre() {
	return null;
    }

    @Override
    public String getGUID() {
	return getId();
    }

    @Override
    public String getId() {
	return getDoi();
    }

    @Override
    public String getIsbn() {
	return getString(journal, BIBO.isbn);
    }

    @Override
    public String getIssn() {
	return getString(journal, BIBO.issn);
    }

    @Override
    public String getIssue() {
	return getString(document, BIBO.issue);
    }

    @Override
    public List<String> getKeywords() {
	// TODO Auto-generated method stub
	// BIBO.
	return null;
    }

    @Override
    public String getLanguage() {
	return getString(document, DCTERMS.language);
    }

    @Override
    public List<String> getMeshTerms() {
	return null;
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public int getNumAnnotations() {
	return 0;
    }

    @Override
    public String getItemNumber() {
	return null;
    }

    @Override
    public String getOwner() {
	return null;
    }

    @Override
    public Integer getPageEnd() {
	return getInteger(document, BIBO.pageEnd);
    }

    @Override
    public String getPages() {
	return getString(document, BIBO.pages);
    }

    @Override
    public Integer getPageStart() {
	return getInteger(document, BIBO.pageStart);
    }

    @Override
    public Document getParent() {
	return null;
    }

    @Override
    public String getPublicationDate() {
	return getDate();
    }

    @Override
    public String getPublicationName() {
	return getString(journal, DCTERMS.title);
    }

    @Override
    public String getPublisher() {
	return getString(document, DCTERMS.publisher);
    }

    @Override
    public String getPMId() {
	return null;
    }

    @Override
    public String getRights() {
	return null;
    }

    @Override
    public String getSeriesTitle() {
	return getString(journal, DCTERMS.title);
    }

    @Override
    public String getSource() {
	return ServiceFactory.COLLECTION_CROSSREF;
    }

    @Override
    public String getTitle() {
	return getString(document, DCTERMS.title);
    }

    @Override
    public String getType() {
	return getResourceName(document, RDF.type);
    }

    @Override
    public String getUri() {
	// if the URI given by the service is "http://dx.doi.org/10..." return "doi:10..."
	String docUri = document.getURI();
	int doiPos = docUri.indexOf("10.");
	if (doiPos > 0) {
	    return "doi:" + docUri.substring(doiPos - 1);
	}
	return docUri;
    }

    @Override
    public String getUrl() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
	return getString(document, BIBO.volume);
    }

    @Override
    public String getIssued() {
	return getDate();
    }

    @Override
    public Date getIssuedDate() {
	return getDateObject();
    }

    public Model getRdfModel() {
	return model;
    }

    // Methods for extracting information from the RDF Model

    static String getResourceName(Resource resource, Property property) {
	if (resource == null)
	    return null;
	try {
	    Resource object = resource.getPropertyResourceValue(property);
	    return object.getLocalName();
	} catch (Exception e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    static String getString(Resource resource, Property property) {
	if (resource == null)
	    return null;
	try {
	    Statement stmt = resource.getProperty(property);
	    return stmt.getString();
	} catch (Exception e) {
	    // e.printStackTrace();
	    return null;
	}
    }

    static Integer getInteger(Resource resource, Property property) {
	if (resource == null)
	    return null;
	try {
	    Statement stmt = resource.getProperty(property);
	    return stmt.getInt();
	} catch (Exception e) {
	    return null;
	}
    }

    @Override
    public String getSourceId() {
	// TODO Auto-generated method stub
	return null;
    }

}
