package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Interface to implement the Citagora Person object
 * 
 * @author griccardi
 * 
 */

public interface Person extends UriObject {

	static final String TYPE = "citagora:person";
	static final String COLLECTION = "person";

	String getAccount();

	String getAccountName();

	String getFamilyName();

	String getGivenName();

	String getHomePage();

	Boolean getIsAgent();

	Boolean getIsAuthor();

	Boolean getIsPerson();

	String getName();

	String getType();

	void setAccount(String string);

	void setAccountName(String name);

	void setFamilyName(String string);

	void setGivenName(String string);

	void setHomePage(String string);

	void setIsAgent(boolean isAgent);

	void setIsAuthor(boolean isAuthor);

	void setIsPerson(boolean isPerson);

	void setName(String string);

	void setPersonType(String string);

	List<Review> getAgentReviews();

	void addAgentTag(Tag tag);

	void addAgentComment(Comment comment);

	void addAgentReview(Review review1);

}
