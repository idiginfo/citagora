package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.svcapi.citagora.AnnotationImpl;
import org.idiginfo.docsvc.svcapi.citagora.PersonImpl;

@Entity(name = "reply")
public class ReplyImpl extends AnnotationImpl implements Reply {

    @Embedded
    RatingTypeImpl ratingType;

    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.PERSIST)
    Person reviewer;

    Integer rating;

    @OneToMany(mappedBy = "", targetEntity = ReplyImpl.class, cascade = CascadeType.PERSIST)
    List<Reply> replies;

    public ReplyImpl() {
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
	this.ratingType = (RatingTypeImpl) ratingType;
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
}
