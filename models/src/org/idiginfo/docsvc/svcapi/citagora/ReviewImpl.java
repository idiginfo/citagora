package org.idiginfo.docsvc.svcapi.citagora;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Review;

public class ReviewImpl extends AnnotationImpl implements Review {
	RatingType ratingType; // constant citagora:ratingType
	// Person reviewer; // same as getAnnotator
	Integer rating;

	// CitagoraDocument documentReviewed; // same as getTarget
	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public RatingType getRatingType() {
		return ratingType;
	}

	@Override
	public Person getReviewer() {
		return getAnnotator();
	}

	public void setReviewer(Person person) {
		setAnnotator(person);
	}

	@Override
	public CitagoraDocument getDocumentReviewed() {
		return (CitagoraDocument) getTarget();
	}

	public void setDocumentReviewed(CitagoraDocument document) {
		setTarget(document);
	}
}
