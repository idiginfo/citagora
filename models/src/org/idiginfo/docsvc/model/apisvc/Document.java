package org.idiginfo.docsvc.model.apisvc;

import java.util.Date;
import java.util.List;

/**
 * Interface to implement the Document object
 * 
 * @author griccardi
 * 
 */

public interface Document {

	public String getAbstractText();

	public String getAggregationType();

	public Annotation getAnnotation(int i);

	public Annotation[] getAnnotations();

	public String getarXivId();

	public List<String> getAuthorList();

	public String getAuthorNotes();

	public String getAuthors();

	public String getCopyright();

	public String getCoverDate();

	public String getDate();

	public Date getDateObject();

	public String getDoi();

	public String getEdition();

	public String geteIssn();

	public String getGenre();

	public String getGUID();

	public String getId();

	public String getIsbn();

	public String getIssn();

	public String getIssue();

	public List<String> getKeywords();

	public String getLanguage();

	public List<String> getMeshTerms();

	public String getName();

	public int getNumAnnotations();

	public String getItemNumber();

	public String getOwner();

	public Integer getPageEnd();

	public String getPages();

	public Integer getPageStart();

	public Document getParent();

	public String getPublicationDate();

	public String getPublicationName();

	public String getPublisher();

	public String getPMId();

	public String getRights();

	public String getSeriesTitle();

	public String getSource();

	public String getTitle();

	public String getType();

	public String getUri();

	public String getUrl();

	public String getVolume();

	public String getIssued();

	public Date getIssuedDate();

	public String getSourceId();

}
