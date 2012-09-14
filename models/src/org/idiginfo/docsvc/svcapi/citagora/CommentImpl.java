package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;

import javax.persistence.Entity;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;

@Entity(name = "comments")
public class CommentImpl extends AnnotationImpl implements Comment {

    RatingType ratingType;

    Person reviewer;

    Integer rating;

    List<Comment> replies;

    public CommentImpl() {
	setType(Comment.TYPE);
	setCollection(Comment.COLLECTION);
	initId();
    }

    public String getType() {
	return Comment.TYPE;
    }

    public RatingType getRatingType() {
	return ratingType;
    }

    public void setRatingType(RatingType ratingType) {
	this.ratingType = ratingType;
    }

    public Person getReviewer() {
	return reviewer;
    }

    public void setReviewer(Person reviewer) {
	this.reviewer = reviewer;
    }

    public Integer getRating() {
	return rating;
    }

    public void setRating(Integer rating) {
	this.rating = rating;
    }

    public List<Comment> getReplies() {
	return replies;
    }

    public void setReplies(List<Comment> replies) {
	this.replies = replies;
    }
}
