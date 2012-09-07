package org.idiginfo.docsvc.model.citagora;

public interface Review extends Annotation {
	RatingType getRatingType(); // constant citagora:ratingType
	Person getReviewer(); // same as getAnnotator
	Integer getRating();
	CitagoraDocument getDocumentReviewed(); // same as getTarget
}
