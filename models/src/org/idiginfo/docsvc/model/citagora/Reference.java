package org.idiginfo.docsvc.model.citagora;

import java.util.Date;
import java.util.List;

public interface Reference extends CitagoraObject {

    public String TYPE = "bibo:Document";
    public String COLLECTION = "reference";

    public String getUri();

    public void setUri(String string);

    public String getSource();

    public void setSource(String source);

    public String getLanguage();

    public void setLanguage(String string);

    public String getAbstract();

    public void setAbstract(String string);

    public String getTitle();

    public void setTitle(String title);

    public Integer getPageStart();

    public void setPageStart(Integer pageStart);

    public Integer getPageEnd();

    void setPageEnd(int end);

    public String getPages();

    void setPages(String pages);

    public String getVolume();

    public void setVolume(String volume);

    public Date getIssued();

    public void setIssued(Date dateObject);

    public String getPmid();

    void setPmid(String pmid);

    public String getDoi();

    public void setDoi(String doi);

    public Reference isPartOf();

    void setIsPartOf(Reference reference);

    public List<Author> getAuthors();

    void addAuthor(Author author);

    public List<Reference> getCitationList();

    void addCitation(Reference citation);

    public List<String> getSeeAlso();

    void addSeeAlso(String seeAlso);

    public List<CitagoraDocument> getCitagoraDocuments();

    public void addCitagoraDocument(CitagoraDocument document);

    public Double getOverallRating();

    void setOverallRating(double rating);

    public Double getReadabilityRating();

    void setReadabilityRating(double rating);

    public Double getAccuracyRating();

    void setAccuracyRating(double rating);

    public Double getOriginalityRating();

    void setOriginalityRating(double rating);

    public String getAbstractText();

    public void setAbstractText(String string);

    public String getSubject();

    public void setSubject(String string);

    public String getShortTitle();

    public void setShortTitle(String string);

    public String getPublisher();

    public void setPublisher(String string);

    public void setBiboType(String string);

    public String getBiboType();

    List<Reference> getContains();

    CitagoraAgent getContributedBy();

    void setContributedBy(CitagoraAgent contributedBy);

    List<Reference> getIsCitedBy();

}
