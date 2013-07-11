package org.idiginfo.docsvc.model.citagora;

/**
 * Interface to implement the Citagora Review object
 * 
 * @author griccardi
 * 
 */

public interface Review extends CitagoraObject {
	static final String TYPE = "http://purl.org/stuff/rev#Review";
	static final String COLLECTION = "review";

	String getRatingType();

	void setRatingType(String uri);

	Person getReviewer();

	void setReviewer(Person person);

	Integer getRating();

	void setRating(int i);

	Container getDocumentReviewed();

	void setDocumentReviewed(Container document);

	Integer getTotalVotes();

	void setTotalVotes(int i);

}
