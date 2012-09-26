package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Review;


@Entity(name="reviews")
@DiscriminatorValue(value = "review")
public class ReviewImpl extends CitagoraObjectImpl implements Review {
    String ratingType; // constant citagora:ratingType
    Integer rating;
    Integer totalVotes;
    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
    Person reviewer;
    @ManyToOne(targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.ALL)
    CitagoraDocument documentReviwed;

    public ReviewImpl() {
	setType(Review.TYPE);
	setCollection(Review.COLLECTION);
	//initId();
    }

    // CitagoraDocument documentReviewed; // same as getTarget
    public Integer getRating() {
	return rating;
    }

    public void setRating(int rating) {
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
