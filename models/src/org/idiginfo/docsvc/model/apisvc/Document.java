package org.idiginfo.docsvc.model.apisvc;

import java.util.Date;
import java.util.List;

public interface Document {

    public Annotation getAnnotation(int i);

    public Annotation[] getAnnotations();

    public List<String> getAuthorList();

    public String getAuthors();

    public String getCopyright();

    public String getDate();

    public Date getDateObject();

    public String getDoi();

    public String getGUID();

    public String getId();

    public String getIsbn();

    public String getIssn();

    public String getIssue();

    public List<String> getKeywords();

    public List<String> getMeshTerms();

    public String getName();

    public int getNumAnnotations();

    public String getOwner();

    public Integer getPageEnd();

    public String getPages();

    public Integer getPageStart();

    public Document getParent();

    public String getPublicationName();

    public String getPublisher();

    public String getSource();

    public String getTitle();

    public String getType();

    public String getUri();

    public String getUrl();

    public String getVolume();

}
