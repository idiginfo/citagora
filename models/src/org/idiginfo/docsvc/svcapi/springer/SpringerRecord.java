package org.idiginfo.docsvc.svcapi.springer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.apache.commons.lang.StringUtils;

/**
 * Class to map Spring json object to Document
 * 
 * @author griccardi
 * 
 */
public class SpringerRecord implements Document {
    String identifier;
    String title;
    Creator[] creators;
    String publicationName;
    String issn;
    String isbn;
    String doi;
    String publisher;
    String publicationDate;
    String volume;
    String number;
    String startingPage;
    String url;
    String copyright;

    static class Creator {
	String creator;

	public String toString() {
	    return creator;
	}
    }

    @Override
    public String getId() {
	return "doi:" + doi;
    }

    @Override
    public void setId(String id) {
	doi = id;
    }

    @Override
    public String getDate() {
	return publicationDate;
    }

    transient DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date getDateObject() {
	if (publicationDate == null)
	    return null;
	try {
	    return formatter.parse(publicationDate);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}
    }

    @Override
    public void setDate(Date date) {
    }

    @Override
    public String getName() {
	return null;
    }

    @Override
    public void setName(String name) {
    }

    @Override
    public String getOwner() {
	return null;
    }

    @Override
    public void setOwner(String owner) {
    }

    @Override
    public String getType() {
	return null;
    }

    @Override
    public void setType(String type) {
    }

    @Override
    public Document getParent() {
	return null;
    }

    @Override
    public void setParent(Document parent) {
    }

    @Override
    public Annotation[] getAnnotations() {
	return null;
    }

    @Override
    public String getTitle() {
	return title;
    }

    @Override
    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public String getAuthors() {
	String authors = StringUtils.join(creators, ", ");
	return authors;
    }

    @Override
    public List<String> getAuthorList() {
	List<String> authorList = new Vector<String>();
	for (int i = 0; i < creators.length; i++) {
	    authorList.add(creators[i].creator);
	}
	return authorList;

    }

    @Override
    public void setAuthors(String authors) {
	// TODO Auto-generated method stub

    }

    @Override
    public int getNumAnnotations() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public Annotation getAnnotation(int i) {
	// TODO Auto-generated method stub
	return null;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public Creator[] getCreators() {
	return creators;
    }

    public void setCreators(Creator[] creators) {
	this.creators = creators;
    }

    public String getPublicationName() {
	return publicationName;
    }

    public void setPublicationName(String publicationName) {
	this.publicationName = publicationName;
    }

    public String getIssn() {
	return issn;
    }

    public void setIssn(String issn) {
	this.issn = issn;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    public String getDoi() {
	return doi;
    }

    public void setDoi(String doi) {
	this.doi = doi;
    }

    public String getPublisher() {
	return publisher;
    }

    public void setPublisher(String publisher) {
	this.publisher = publisher;
    }

    public String getPublicationDate() {
	return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
	this.publicationDate = publicationDate;
    }

    public String getVolume() {
	return volume;
    }

    public void setVolume(String volume) {
	this.volume = volume;
    }

    public String getNumber() {
	return number;
    }

    public void setNumber(String number) {
	this.number = number;
    }

    public String getStartingPage() {
	return startingPage;
    }

    public void setStartingPage(String startingPage) {
	this.startingPage = startingPage;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getCopyright() {
	return copyright;
    }

    public void setCopyright(String copyright) {
	this.copyright = copyright;
    }

    @Override
    public String getGUID() {
	if (doi != null) {
	    if (doi.startsWith("doi:"))
		return doi;
	    return "doi:" + doi;
	}
	return "http://ids.idiginfo.org/" + getId();
    }

    @Override
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Integer getPageStart() {
	try {
	    return Integer.getInteger(startingPage);
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    @Override
    public Integer getPageEnd() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPages() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssue() {
	return number;
    }

    @Override
    public void setIssue(String issue) {
	this.number = issue;
    }

    @Override
    public String getUri() {
	if (doi != null && doi.length() > 0) {
	    if (doi.startsWith("doi:"))
		return doi;
	    return "doi:" + doi;
	}
	return url;
    }

    @Override
    public List<String> getKeywords() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addKeyword(String keywords) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public List<String> getMeshTerms() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addMeshTerm(String meshTerms) {
	// TODO Auto-generated method stub
	
    }
}
