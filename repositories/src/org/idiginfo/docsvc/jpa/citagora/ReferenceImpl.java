package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Reference;

@Entity(name = "citagora_references")
@DiscriminatorValue(value = "reference")
public class ReferenceImpl extends CitagoraObjectImpl implements Reference {

    String abstractText; // 'abstract' is a Java keyword
    String title;
    String subject;
    String language;
    Integer pageStart;
    Integer pageEnd;
    String volume;
    String biboType;
    @Temporal(TemporalType.TIMESTAMP)
    Date issued;
    @Column(unique = true)
    String pmid;
    @Column(unique = true)
    String doi;

    @ManyToOne(targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "isPartOf")
    Reference isPartOf;

    @OneToMany(mappedBy = "isPartOf", targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL)
    List<Reference> contains;

    @OneToMany(mappedBy = "isAbout", targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.ALL)
    List<CitagoraDocument> citagoraDocuments;

    @ManyToMany(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
    @JoinTable(name = "reference_authors")
    List<Author> authorList;

    @ManyToMany(targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL)
    @JoinTable(name = "reference_citations")
    List<Reference> isCitedBy;
    @ManyToMany(mappedBy = "isCitedBy", targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL)
    List<Reference> citationList;

    List<String> seeAlso;

    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
    CitagoraAgent contributedBy;

    String shortTitle;
    String publisher;
    String pages;

    Double readabilityRating;
    Double overallRating;
    Double accuracyRating;
    Double originalityRating;

    public ReferenceImpl() {
	type = Reference.TYPE;
	setCollection(Reference.COLLECTION);
	// initId();
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

    public void setPageEnd(int pageEnd) {
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

    public List<Author> getAuthors() {
	if (authorList == null)
	    authorList = new Vector<Author>();
	return authorList;
    }

    public List<Reference> getCitationList() {
	if (citationList == null)
	    citationList = new Vector<Reference>();
	return citationList;
    }

    public List<String> getSeeAlso() {
	if (seeAlso == null)
	    seeAlso = new Vector<String>();
	return seeAlso;
    }

    public List<CitagoraDocument> getCitagoraDocuments() {
	if (citagoraDocuments == null)
	    citagoraDocuments = new Vector<CitagoraDocument>();
	return citagoraDocuments;
    }

    public Double getOverallRating() {
	return overallRating;
    }

    public void setOverallRating(Double overallRating) {
	this.overallRating = overallRating;
    }

    @Override
    public Reference isPartOf() {
	return isPartOf;
    }

    @Override
    public Double getReadabilityRating() {
	return readabilityRating;
    }

    @Override
    public Double getAccuracyRating() {
	return accuracyRating;
    }

    @Override
    public Double getOriginalityRating() {
	return originalityRating;
    }

    public void addCitagoraDocument(CitagoraDocument document) {
	if (document == null)
	    return;
	getCitagoraDocuments().add(document);
	document.setIsAbout(this);
    }

    public void addSeeAlso(String link) {
	if (link == null)
	    return;
	getSeeAlso().add(link);
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

    public void setSubject(String subject) {
	this.subject = subject;
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

    @Override
    public List<Reference> getContains() {
	if (contains == null)
	    contains = new Vector<Reference>();
	return contains;
    }

    @Override
    public List<Reference> getIsCitedBy() {
	if (isCitedBy == null)
	    isCitedBy = new Vector<Reference>();
	return isCitedBy;
    }

    @Override
    public CitagoraAgent getContributedBy() {
	return contributedBy;
    }

    @Override
    public String getBiboType() {
	return biboType;
    }

    @Override
    public void setBiboType(String biboType) {
	this.biboType = biboType;
    }

    @Override
    public void setContributedBy(CitagoraAgent contributedBy) {
	this.contributedBy = contributedBy;
    }

    @Override
    public void addAuthor(Author author) {
	if (author == null || getAuthors().contains(author))
	    return;
	author.addAuthorReference(this);// work done in inverse
    }

    @Override
    public void addCitation(Reference citation) {
	if (citation == null || getCitationList().contains(citation))
	    return;
	citation.addCitation(this);// work done in inverse
    }

    @Override
    public void setOverallRating(double rating) {
	this.overallRating = rating;
    }

    @Override
    public void setReadabilityRating(double rating) {
	readabilityRating = rating;
    }

    @Override
    public void setAccuracyRating(double rating) {
	accuracyRating = rating;
    }

    @Override
    public void setOriginalityRating(double rating) {
	originalityRating = rating;
    }

    public List<Author> getAuthorList() {
	if (authorList == null)
	    authorList = new Vector<Author>();
	return authorList;
    }

    public void setPageEnd(Integer pageEnd) {
	this.pageEnd = pageEnd;
    }

    public void setReadabilityRating(Double readabilityRating) {
	this.readabilityRating = readabilityRating;
    }

    public void setAccuracyRating(Double accuracyRating) {
	this.accuracyRating = accuracyRating;
    }

    public void setOriginalityRating(Double originalityRating) {
	this.originalityRating = originalityRating;
    }

    public Integer getMyId() {
	return myId;
    }

}
