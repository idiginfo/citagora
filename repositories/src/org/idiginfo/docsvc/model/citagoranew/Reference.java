package org.idiginfo.docsvc.model.citagoranew;

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
}
