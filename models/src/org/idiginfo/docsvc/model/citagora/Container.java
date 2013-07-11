package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Interface to implement the Citagora Container object
 * 
 * @author griccardi
 * 
 */

public interface Container extends CitagoraObject {
	static final String TYPE = "http://citagora.com/rdf#Container";
	static final String COLLECTION = "container";

	List<Review> getReviews();

	List<Tag> getTags();

	List<Comment> getComments();

	Reference getIsAbout();

	void setIsAbout(Reference reference);

	void addReview(Review review1);

	void addTag(Tag tag);

	void addComment(Comment comment);

}
