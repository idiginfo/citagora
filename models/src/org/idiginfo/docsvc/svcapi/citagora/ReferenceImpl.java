package org.idiginfo.docsvc.svcapi.citagora;

import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Reference;

public class ReferenceImpl extends CitagoraObjectImpl implements Reference {
	String source;

	String abstractText; // 'abstract' is a Java keyword

	String title;

	Integer pageStart;

	Integer pageEnd;

	String volume;

	Date issued;

	String pmid;

	String doi;

	CitagoraAgent wasAttributedTo;

	Date generatedAtTime;

	Reference isPartOf;

	List<Author> authorList;

	List<Reference> citationList;

	List<String> seeAlso;

	List<CitagoraDocument> citagoraDocuments;

	List<Annotation> annotations;

	Double overallRating;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
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
		return generatedAtTime;
	}

	public void setGeneratedAtTime(Date generatedAtTime) {
		this.generatedAtTime = generatedAtTime;
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

	public void setSeeAlso(List<String> seeAlso) {
		this.seeAlso = seeAlso;
	}

	public List<CitagoraDocument> getCitagoraDocuments() {
		return citagoraDocuments;
	}

	public void setCitagoraDocuments(List<CitagoraDocument> citagoraDocuments) {
		this.citagoraDocuments = citagoraDocuments;
	}

	public List<Annotation> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<Annotation> annotations) {
		this.annotations = annotations;
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

}
