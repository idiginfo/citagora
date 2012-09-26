package org.idiginfo.docsvc.model.citagora;

public interface Person extends UriObject {

    static final String TYPE = "citagora:person";
    static final String COLLECTION = "person";

    String getType();

    String getGivenName();

    void setGivenName(String string);

    String getFamilyName();

    void setFamilyName(String string);

    String getName();

    void setName(String string);

    String getAccountName();

    void setAccountName(String name);

    String getAccount();

    void setAccount(String string);

    String getHomePage();

    void setHomePage(String string);

    Boolean getIsAuthor();

    void setIsAuthor(boolean isAuthor);

    Boolean getIsAgent();

    void setIsAgent(boolean isAgent);

    Boolean getIsPerson();

    void setIsPerson(boolean isPerson);

    void setPersonType(String string);

}
