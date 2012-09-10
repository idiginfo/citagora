package org.idiginfo.docsvc.model.citagora;

import java.util.Date;
import java.util.List;

public interface Reference extends CitagoraObject {

	String TYPE = "bibo:Document";
	String COLLECTION = "reference";

	String getSource();

	String getLanguage();

	String getAbstract();

	String getTitle();

	Integer getPageStart();

	Integer getPageEnd();

	String getPages();

	String getVolume();

	Date getIssued();

	String getPmid();

	String getDoi();

	Reference isPartOf();

	List<Author> getAuthorList();

	List<Reference> getCitationList();

	List<String> getSeeAlso();

	List<CitagoraDocument> getCitagoraDocuments();

	List<Annotation> getAnnotations();

	Double getOverallRating();

	Double getReadabilityRating();

	Double getAccuracyRating();

	Double getOriginalityRating();

	String getAbstractText();

	String getSubject();

	String getShortTitle();

	String getPublisher();
}
