package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;

@Entity(name = "comments")
@DiscriminatorValue(value = "comment")
public class CommentImpl extends AnnotationImpl implements Comment {

    @Column(name = "ratingType")
    String commentType;

    @ManyToOne(targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.ALL)
    CitagoraDocument target;

    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
    Person reviewer;

    Integer rating;

    @OneToMany(mappedBy = "replyTarget", targetEntity = ReplyImpl.class, cascade = CascadeType.ALL)
    List<Reply> replies;

    public CommentImpl() {
	setType(Comment.TYPE);
	setCollection(Comment.COLLECTION);
	// initId();
    }

    public String getType() {
	return Comment.TYPE;
    }

    public String getCommentType() {
	return commentType;
    }

    public void setCommentType(String commentType) {
	this.commentType = commentType;
    }

    public Person getReviewer() {
	return reviewer;
    }

    /**
     * Set both sides of the relationship, carefully. This code is repeated for
     * every ManyToOne field.
     */
    @Override
    public void setReviewer(CitagoraAgent reviewer) {
	if (this.reviewer == reviewer)
	    return; // no change
	if (this.reviewer != null) {
	    // remove from inverse relationship
	    this.reviewer.getAgentReviews().remove(this);
	}
	// set forward relationship
	this.reviewer = reviewer;
	if (reviewer == null)
	    return;
	// set inverse relationship
	reviewer.getAgentComments().add(this);
    }

    public Integer getRating() {
	return rating;
    }

    public void setRating(Integer rating) {
	this.rating = rating;
    }

    public List<Reply> getReplies() {
	if (replies == null)
	    replies = new Vector<Reply>();
	return replies;
    }

    @Override
    public CitagoraObject getTarget() {
	return target;
    }

    /**
     * Set both sides of the relationship, carefully. This code is repeated for
     * every ManyToOne field.
     */
    @Override
    public void setTarget(CitagoraObject target) {
	if (this.target == target)
	    return; // no change
	if (target != null && !(target instanceof CitagoraDocument))
	    throw new ClassCastException();
	if (this.target != null) {
	    // remove from inverse relationship
	    this.target.getComments().remove(this);
	}
	// set forward relationship
	this.target = (CitagoraDocument) target;
	if (target == null)
	    return;
	((CitagoraDocument) target).getComments().add(this);
    }

    @Override
    public void addReply(Reply reply) {
	if (reply != null)
	    reply.setTarget(this);
    }
}
