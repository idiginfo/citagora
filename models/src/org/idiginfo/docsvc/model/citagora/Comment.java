package org.idiginfo.docsvc.model.citagora;

import java.util.List;
/**
 * Interface for Citagora Comment node
 * @author griccardi
 *
 */
public interface Comment extends Annotation {
	// type is oaf:annotation
	
	public static final String TYPE = "oaf:annotation";
	
	RatingType getRatingType();

	Person getReviewer();

	Integer getRating();

	CitagoraDocument getReviews();

	List<Comment> getReplies();
}
