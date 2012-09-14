package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.events.Comment;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;

public class CitagoraDocumentImpl extends CitagoraObjectImpl implements
	CitagoraDocument {

    Reference isAbout;
    List<Review> reviews;
    List<Tag> tags;
    List<Comment> comments;

    public CitagoraDocumentImpl() {
	setType(CitagoraDocument.TYPE);
	setCollection(CitagoraDocument.COLLECTION);
	initId();
    }

    @Override
    public List<Review> getRatings() {
	// TODO Auto-generated method stub
	return reviews;
    }

    @Override
    public List<Tag> getTags() {
	// TODO Auto-generated method stub
	return tags;
    }

    @Override
    public List<Comment> getComments() {
	// TODO Auto-generated method stub
	return comments;
    }

    public Reference getIsAbout() {
	return isAbout;
    }

    public void setIsAbout(Reference isAbout) {
	this.isAbout = isAbout;
    }

    public List<Review> getReviews() {
	if (reviews == null)
	    reviews = new Vector<Review>();
	return reviews;
    }

    public void addReview(Review review) {
	if (reviews == null) {
	    reviews = new Vector<Review>();
	}
	reviews.add(review);
    }

    @Override
    public void addTag(Tag tag) {
	if (tags == null)
	    tags = new Vector<Tag>();
	tags.add(tag);
    }

    @Override
    public void addComment(Comment comment) {
	if (comments == null)
	    comments = new Vector<Comment>();
	comments.add(comment);
    }
}