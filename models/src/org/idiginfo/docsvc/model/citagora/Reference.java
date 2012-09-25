package org.idiginfo.docsvc.model.citagora;

import java.util.Date;
import java.util.List;

public interface Reference extends CitagoraObject {

	public String TYPE = "bibo:Document";
	public String COLLECTION = "reference";

	public String getSource();

	public String getLanguage();

	public String getAbstract();

	public String getTitle();

	public Integer getPageStart();

	public Integer getPageEnd();

	public String getPages();

	public String getVolume();

	public Date getIssued();

	public String getPmid();

	public String getDoi();

	public Reference isPartOf();

	public List<Author> getAuthorList();

	public List<Reference> getCitationList();

	public List<String> getSeeAlso();

	public List<CitagoraDocument> getCitagoraDocuments();

	public List<Annotation> getAnnotations();

	public Double getOverallRating();

	public Double getReadabilityRating();

	public Double getAccuracyRating();

	public Double getOriginalityRating();

	public String getAbstractText();

	public String getSubject();

	public String getShortTitle();

	public String getPublisher();

	public void setId(String id);

	public void setTitle(String title);

	public void setSource(String source);

	public void setPageStart(Integer pageStart);

	public void setVolume(String volume);

	public void setIssued(Date dateObject);

	public void setDoi(String doi);

	public void addCitagoraDocument(CitagoraDocument document);

	public void setLanguage(String string);

	public void addSeeAlso(String string);

	public void setUri(String string);

	public void setSubject(String string);

	public void setShortTitle(String string);

	public void setAbstract(String string);

	public void setPmid(String string);

	public void setPublisher(String string);

	public void setPageEnd(int i);

	public void setPages(String string);
}
