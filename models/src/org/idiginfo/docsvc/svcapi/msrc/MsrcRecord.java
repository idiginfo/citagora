package org.idiginfo.docsvc.svcapi.msrc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

/**
 * Class
 * 
 * @author griccardi
 * 
 */
public class MsrcRecord implements Document {

    String id;
    String created;
    String changed;
    String documentType; // ": "journal_article",
    String methodsSection;
    MsrcDocument document;
    List<String> creators;
    List<MsrcAnnotationRef> annotations;

    @Override
    public Annotation getAnnotation(int i) {
	// TODO get annotation from Annotate service
	return null;
    }
    
    public List<MsrcAnnotationRef> getAnnotationRefs(){
	return annotations;
    }

    @Override
    public Annotation[] getAnnotations() {
	// TODO get annotations from Annotate service
	return null;
    }

    @Override
    public String getAuthors() {
	if (document == null)
	    return null;
	return document.getAuthors();
    }

    @Override
    public List<String> getAuthorList() {
	if (document == null)
	    return null;
	return document.getAuthorList();
    }

    @Override
    public String getCopyright() {
	if (document == null)
	    return null;
	return document.getCopyright();
    }

    @Override
    public String getDate() {
	if (document == null)
	    return null;
	return document.getDate();
    }

    static DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX"); //"2011-11-04T15:40:15-04:00"

    public static Date parseDate(String date) {
	try {
	    return formatter.parse(date);
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	    return null;
	}

    }

    @Override
    public Date getDateObject() {
	return parseDate(created);
    }

    @Override
    public String getDoi() {
	if (document == null)
	    return null;
	return document.getDoi();
    }

    @Override
    public String getGUID() {
	if (document == null)
	    return null;
	return document.getGUID();
    }

    @Override
    public String getId() {
	if (document == null)
	    return null;
	return document.getId();
    }

    @Override
    public String getIsbn() {
	if (document == null)
	    return null;
	return document.getIsbn();
    }

    @Override
    public String getIssn() {
	if (document == null)
	    return null;
	return document.getIssn();
    }

    @Override
    public String getName() {
	if (document == null)
	    return null;
	return document.getName();
    }

    @Override
    public int getNumAnnotations() {
	if (document == null)
	    return 0;
	return document.getNumAnnotations();
    }

    @Override
    public String getOwner() {
	if (document == null)
	    return null;
	return document.getOwner();
    }

    @Override
    public Document getParent() {
	if (document == null)
	    return null;
	return document.getParent();
    }

    @Override
    public String getPublicationName() {
	if (document == null)
	    return null;
	return document.getPublicationName();
    }

    @Override
    public String getPublisher() {
	if (document == null)
	    return null;
	return document.getPublisher();
    }

    @Override
    public String getSource() {
	if (document == null)
	    return null;
	return document.getSource();
    }

    @Override
    public String getTitle() {
	if (document == null)
	    return null;
	return document.getTitle();
    }

    @Override
    public String getType() {
	return documentType;
    }

    @Override
    public String getUrl() {
	if (document == null)
	    return null;
	return document.getUrl();
    }

    @Override
    public String getVolume() {
	if (document == null)
	    return null;
	return document.getVolume();
    }

    @Override
    public Integer getPageStart() {
	if (document == null)
	    return null;
	return document.getPageStart();
    }

    @Override
    public Integer getPageEnd() {
	if (document == null)
	    return null;
	return document.getPageEnd();
    }

    @Override
    public String getPages() {
	if (document == null)
	    return null;
	return document.getPages();
    }

    @Override
    public void setAuthors(String authors) {
	if (document != null)
	    document.setAuthors(authors);
    }

    @Override
    public void setCopyright(String copyright) {
	if (document != null)
	    document.setCopyright(copyright);
    }

    @Override
    public void setDate(Date date) {
	if (document != null)
	    document.setDate(date);
    }

    @Override
    public void setId(String id) {
	if (document != null)
	    document.setId(id);
    }

    @Override
    public void setIsbn(String isbn) {
	if (document != null)
	    document.setIsbn(isbn);
    }

    @Override
    public void setIssn(String issn) {
	if (document != null)
	    document.setIssn(issn);
    }

    @Override
    public void setName(String name) {
	if (document != null)
	    document.setName(name);
    }

    @Override
    public void setOwner(String owner) {
	if (document != null)
	    document.setOwner(owner);
    }

    @Override
    public void setParent(Document parent) {
	if (document != null)
	    document.setParent(parent);
    }

    @Override
    public void setPublicationName(String name) {
	if (document != null)
	    document.setPublicationName(name);
    }

    @Override
    public void setPublisher(String publisher) {
	if (document != null)
	    document.setPublisher(publisher);
    }

    @Override
    public void setTitle(String title) {
	if (document != null)
	    document.setTitle(title);
    }

    @Override
    public void setType(String type) {
	if (document != null)
	    document.setType(type);
    }

    @Override
    public void setUrl(String url) {
	if (document != null)
	    document.setUrl(url);
    }

    @Override
    public void setVolume(String volume) {
	if (document != null)
	    document.setVolume(volume);
    }

    @Override
    public String getIssue() {
	if (document == null)
	    return null;
	return document.getIssue();
    }

    @Override
    public void setIssue(String issue) {
	if (document != null)
	    document.setIssue(issue);
    }

    @Override
    public String getUri() {
	if (document == null)
	    return null;
	return document.getUri();
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
