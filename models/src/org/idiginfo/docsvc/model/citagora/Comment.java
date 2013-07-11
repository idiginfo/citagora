package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Interface for Citagora Comment node
 * 
 * @author griccardi
 * 
 */

public interface Comment extends Annotation {
	// type is oaf:annotation

	// inherits TYPE and COLLECTION from Annotation
	// public static final String TYPE = "oaf:annotation";
	// static final String COLLECTION = "annotation";

	public String getCommentType();

	public void setCommentType(String type);

	public Integer getRating();

	public void setRating(Integer rating);

	public List<Reply> getReplies();

	public void setReviewer(CitagoraAgent reviewer);

	public void addReply(Reply reply);

	public void setTags(String tags);

	public String getTags();
}
