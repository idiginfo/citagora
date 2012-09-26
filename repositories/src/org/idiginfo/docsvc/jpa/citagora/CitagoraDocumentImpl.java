package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;

@Entity
@Table(schema = "citagora", name = "citagora_documents")
@DiscriminatorValue(value = "document")
public class CitagoraDocumentImpl extends CitagoraObjectImpl implements
	CitagoraDocument {

    @ManyToOne(targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Reference isAbout;

    @OneToMany(mappedBy = "documentReviwed", targetEntity = ReviewImpl.class, cascade = CascadeType.ALL)
    List<Review> reviews;
    @OneToMany(mappedBy = "target", targetEntity = TagImpl.class, cascade = CascadeType.ALL)
    List<Tag> tags;
    @OneToMany(mappedBy = "target", targetEntity = CommentImpl.class, cascade = CascadeType.ALL)
    List<Comment> comments;

    public CitagoraDocumentImpl() {
	setType(CitagoraDocument.TYPE);
	setCollection(CitagoraDocument.COLLECTION);
	// initId();
    }

    @Override
    public List<Tag> getTags() {
	if (tags == null)
	    tags = new Vector<Tag>();
	return tags;
    }

    @Override
    public List<Comment> getComments() {
	if (comments == null)
	    comments = new Vector<Comment>();
	return comments;
    }

    public Reference getIsAbout() {
	return isAbout;
    }

    public void setIsAbout(Reference isAbout) {
	this.isAbout = (ReferenceImpl) isAbout;
    }

    public List<Review> getReviews() {
	if (reviews == null)
	    reviews = new Vector<Review>();
	return reviews;
    }

    public void addReview(Review review) {
	if (review == null)
	    return;
	getReviews().add(review);
	review.setDocumentReviewed(this);
    }

    @Override
    public void addTag(Tag tag) {
	if (tag == null)
	    return;
	getTags().add(tag);
	tag.setTarget(this);
    }

    @Override
    public void addComment(Comment comment) {
	if (comment == null)
	    return;
	getComments().add(comment);
	comment.setTarget(this);
    }
}
