package org.idiginfo.docsvc.model.apisvc;

import java.util.Date;
import java.util.List;

public interface Document {

    public Annotation getAnnotation(int i);

    public Annotation[] getAnnotations();

    public String getAuthors();

    public List<String> getAuthorList();

    public String getCopyright();

    public String getDate();

    public Date getDateObject();

    public String getDoi();

    public String getGUID();

    public String getId();

    public String getIsbn();

    public String getIssn();

    public String getName();

    public int getNumAnnotations();

    public String getOwner();

    public Document getParent();

    public String getPublicationName();

    public String getPublisher();

    public String getSource();

    public String getTitle();

    public String getType();

    public String getUrl();

    public String getVolume();

    public Integer getPageStart();

    public Integer getPageEnd();

    public String getPages();

    public void setAuthors(String authors);

    public void setCopyright(String copyright);

    public void setDate(Date date);

    public void setId(String id);

    public void setIsbn(String isbn);

    public void setIssn(String issn);

    public void setName(String name);

    public void setOwner(String owner);

    public void setParent(Document parent);

    public void setPublicationName(String name);

    public void setPublisher(String publisher);

    public void setTitle(String title);

    public void setType(String type);

    public void setUrl(String url);

    public void setVolume(String volume);

    public String getIssue();

    public void setIssue(String issue);

    public String getUri();
}
