package org.idiginfo.docsvc.svcapi.citagora;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Reference;

public class ReferenceImpl extends CitagoraObjectImpl implements Reference {

	String abstractText; // 'abstract' is a Java keyword
	String title;
	String subject;
	String language;
	Integer pageStart;
	Integer pageEnd;
	String volume;
	Date issued;
	String pmid;
	String doi;
	Reference isPartOf;
	List<Author> authorList;
	List<Reference> citationList;
	List<String> seeAlso;
	String shortTitle;
	String publisher;
	String pages;

	transient List<CitagoraDocument> citagoraDocuments;

	Double overallRating;
	
	public ReferenceImpl() {
		type = Reference.TYPE;
		setCollection(Reference.COLLECTION);
		initId();
	}

	public String getAbstract() {
		return abstractText;
	}

	public void setAbstract(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getPageStart() {
		return pageStart;
	}

	public void setPageStart(Integer pageStart) {
		this.pageStart = pageStart;
	}

	public Integer getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(Integer pageEnd) {
		this.pageEnd = pageEnd;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public Date getIssued() {
		return issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public Date getGeneratedAtTime() {
		return generated;
	}

	public void setGeneratedAtTime(Date generatedAtTime) {
		this.generated = generatedAtTime;
	}

	public Reference getIsPartOf() {
		return isPartOf;
	}

	public void setIsPartOf(Reference isPartOf) {
		this.isPartOf = isPartOf;
	}

	public List<Author> getAuthorList() {
		return authorList;
	}

	public void setAuthorList(List<Author> authorList) {
		this.authorList = authorList;
	}

	public List<Reference> getCitationList() {
		return citationList;
	}

	public void setCitationList(List<Reference> citationList) {
		this.citationList = citationList;
	}

	public List<String> getSeeAlso() {
		return seeAlso;
	}

	public List<CitagoraDocument> getCitagoraDocuments() {
		return citagoraDocuments;
	}

	public void setCitagoraDocuments(List<CitagoraDocument> citagoraDocuments) {
		this.citagoraDocuments = citagoraDocuments;
	}

	public Double getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Double overallRating) {
		this.overallRating = overallRating;
	}

	@Override
	public Reference isPartOf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getReadabilityRating() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getAccuracyRating() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getOriginalityRating() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCitagoraDocument(CitagoraDocument document) {
		if (citagoraDocuments == null) {
			citagoraDocuments = new Vector<CitagoraDocument>();
		}
		citagoraDocuments.add(document);
	}

	public void addSeeAlso(String link) {
		if (seeAlso == null) {
			seeAlso = new Vector<String>();
		}
		seeAlso.add(link);
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void setSubject(String string) {
		// TODO Auto-generated method stub

	}

	public String getSubject() {
		return subject;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}

}