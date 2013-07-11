package org.idiginfo.docsvc.model.apisvc;

/**
 * Interface to implement the ApiParams object 
 * 
 * @author griccardi
 * 
 */

public interface ApiParams {
    public String getApiUser();

    public void setApiUser(String apiUser);

    public String getApiRequestTime();

    public void setApiRequestTime(String apiRequestTime);

    public String getDate();

    public void setDate(String date);

    public String getId();

    public void setId(String code);

    public String getSearchTerms();

    public void setSearchTerms(String keyword);

    public void setOwner(String apiAnnotateUser);

    public String getOwner();

    public void setSort(String sort);

    public String getSort();

    public void setName(String name);

    public String getName();

    public String getCollection();

    public void setCollection(String collection);

    public String getMethod();

    public void setMethod(String method);

    public String getFormat();

    public void setFormat(String format);

    public String getKeyword();

    public void setKeyword(String keyword);

    public String getConstraint();

    public void setConstraint(String constraint);

    public String getDoi();

    public void setDoi(String doi);

    public String getSubject();

    public void setSubject(String subject);

    public String getPub();

    public void setPub(String pub);

    public String getYear();

    public void setYear(String year);

    public String getCountry();

    public void setCountry(String country);

    public String getIsbn();

    public void setIsbn(String isbn);

    public String getIssn();

    public void setIssn(String issn);

    public String getOpenaccess();

    public void setOpenaccess(String openaccess);

    public String getType();

    public void setType(String type);

    public String getImageType();

    public void setImageType(String imageType);

    public String getTitle();

    public void setTitle(String title);

    public String getOrgname();

    public void setOrgname(String orgname);

    public String getJournal();

    public void setJournal(String journal);

    public String getBook();

    public void setBook(String book);

    public String getWithMeta();

    public String getWithNotes();

    public boolean getPersist();

    public void setPersist(boolean persist);

    Integer getNumResults();

    void setNumResults(Integer numResults);

    Integer getFirstResult();

    void setFirstResult(Integer firstResult);

}
