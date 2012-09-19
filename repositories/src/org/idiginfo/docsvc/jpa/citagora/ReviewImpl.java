package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraDocumentImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraObjectImpl;
import org.idiginfo.docsvc.svcapi.citagora.PersonImpl;

@Entity(name="reviews")
public class ReviewImpl extends CitagoraObjectImpl implements Review {
    String ratingType; // constant citagora:ratingType
    Integer rating;
    Integer totalVotes;
    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.PERSIST)
    Person reviewer;
    @ManyToOne(targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.PERSIST)
    transient CitagoraDocument documentReviwed;

    public ReviewImpl() {
	setType(Review.TYPE);
	setCollection(Review.COLLECTION);
	initId();
    }

    // CitagoraDocument documentReviewed; // same as getTarget
    public Integer getRating() {
	return rating;
    }

    public void setRating(Integer rating) {
	this.rating = rating;
    }

    public String getRatingType() {
	return ratingType;
    }

    @Override
    public Person getReviewer() {
	return reviewer;
    }

    public void setReviewer(Person reviewer) {
	this.reviewer = reviewer;
    }

    @Override
    public CitagoraDocument getDocumentReviewed() {
	return documentReviwed;
    }

    public void setDocumentReviewed(CitagoraDocument document) {
	documentReviwed = document;
    }

    public void setRatingType(String string) {
	this.ratingType = string;
    }

    @Override
    public Integer getTotalVotes() {
	return totalVotes;
    }

    public void setTotalVotes(int i) {
	this.totalVotes = i;

    }
}
