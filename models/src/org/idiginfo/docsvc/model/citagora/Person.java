package org.idiginfo.docsvc.model.citagora;

public interface Person extends UriObject {

	static final String TYPE = "citagora:person";
	static final String COLLECTION = "person";

	String getType();

	String getGivenName();

	String getFamilyName();

	String getName();

	String getAccountName();

	String getAccount();

	String getHomePage();

}
