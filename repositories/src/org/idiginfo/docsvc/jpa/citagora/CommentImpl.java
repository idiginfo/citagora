package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Reply;

@Entity(name = "comments")
@DiscriminatorValue(value = "citagoraDocument")
public class CommentImpl extends AnnotationImpl implements Comment {

    String ratingType;

    @ManyToOne(targetEntity = CitagoraDocumentImpl.class)
    CitagoraDocument target;

    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.PERSIST)
    Person reviewer;

    Integer rating;

    @OneToMany(mappedBy = "target", targetEntity = ReplyImpl.class, cascade = CascadeType.PERSIST)
    List<Reply> replies;

    public CommentImpl() {
	setType(Comment.TYPE);
	setCollection(Comment.COLLECTION);
	initId();
    }

    public String getType() {
	return Comment.TYPE;
    }

    public String getRatingType() {
	return ratingType;
    }

    public void setRatingType(String ratingType) {
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

    public List<Reply> getReplies() {
	return replies;
    }

    @Override
    public CitagoraObject getTarget() {
	return target;
    }

}
