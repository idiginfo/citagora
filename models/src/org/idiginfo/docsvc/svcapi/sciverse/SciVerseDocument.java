package org.idiginfo.docsvc.svcapi.sciverse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

public class SciVerseDocument implements Document {

    @SerializedName("prism:url")
    String url; // "http://api.elsevier.com/content/article/DOI:10.1016/j.jpsychires.2008.05.001",

    @SerializedName("link")
    List<SciVerseLink> links;

    @SerializedName("dc:identifier")
    String id; // : "DOI:10.1016/j.jpsychires.2008.05.001",
    @SerializedName("prism:doi")
    String doi; // : "10.1016/j.jpsychires.2008.05.001",
    @SerializedName("pii")
    String pii; // : "S0022-3956(08)00114-3",
    @SerializedName("dc:title")
    String title; // "Substance use disorders and ...",
    @SerializedName("dc:creator")
    String creator; // : "Mayes, S.D.",

    @SerializedName("prism:publicationName")
    String pubName; // : "Journal of Psychiatric Research",
    @SerializedName("prism:aggregationType")
    String aggregationType; // : "Journal",
    @SerializedName("prism:issn")
    String issn; // : "00223956",
    @SerializedName("prism:coverDate")
    String coverDate;// : "2009-01-31"
    @SerializedName("prism:eIssn")
    String eIssn; // : "18780237",
    @SerializedName("prism:volume")
    String volume; // : "7",
    @SerializedName("prism:issueIdentifier")
    String issueId; // : "1",
    @SerializedName("prism:pageRange")
    String pageRange;// : "109-119",

    @SerializedName("prism:coverDisplayDate")
    String displayDate; // : "January 2013",
    @SerializedName("citedby-count")
    Integer citedByCount; // : "0",
    List<Affiliation> affiliation;
    String subtype; // : "ip",
    String subtypeDescription; // : "Article in Press"

    public class Affiliation {
	@SerializedName("affilname")
	String affiliationName; // "Penn State College of Medicine | Pennsylvania State University ^ Pennsylvania State Univ. ^ Pennsylvania State University College of Medicine ^ Pennsylvania State Univ. Coll. Med."
    }

    @Override
    public String getId() {
	return id;
    }

    @Override
    public void setId(String id) {
	this.id = id;

    }

    @Override
    public String getDate() {
	return coverDate;
    }

    static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

    @Override
    public Date getDateObject() {
	// try {
	// return formatter.parse(date);
	// } catch (ParseException e) {
	// }
	return null;
    }

    public void setDate(String date) {
	coverDate = date;
    }

    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setName(String name) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getOwner() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setOwner(String owner) {
	// TODO Auto-generated method stub

    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setType(String type) {
	// TODO Auto-generated method stub

    }

    @Override
    public Document getParent() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setParent(Document parent) {
	// TODO Auto-generated method stub

    }

    @Override
    public Annotation[] getAnnotations() {
	// TODO Auto-generated method stub
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
	// TODO Auto-generated method stub
	return null;
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

    @Override
    public void setDate(Date date) {
	// TODO Auto-generated method stub

    }

    public String getPubName() {
	return pubName;
    }

    @Override
    public String getDoi() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getGUID() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getCopyright() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIsbn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getIssn() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPublicationName() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getPublisher() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getUrl() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public String getVolume() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setCopyright(String copyright) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setIsbn(String isbn) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setIssn(String issn) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPublicationName(String name) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setPublisher(String publisher) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setUrl(String url) {
	// TODO Auto-generated method stub

    }

    @Override
    public void setVolume(String volume) {
	// TODO Auto-generated method stub

    }

    @Override
    public Integer getPageStart() {
	// TODO Auto-generated method stub
	return null;
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
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void setIssue(String issue) {
	// TODO Auto-generated method stub

    }

    @Override
    public List<String> getAuthorList() {
	// TODO Auto-generated method stub
	return null;
    }

}
