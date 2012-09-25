package org.idiginfo.docsvc.model.citagoranew;

public interface Review extends CitagoraObject {
	static final String TYPE = "http://purl.org/stuff/rev#Review";
	static final String COLLECTION = "review";

	String getRatingType(); // constant citagora:ratingType

	Person getReviewer(); // same as getAnnotator

	Integer getRating();

	CitagoraDocument getDocumentReviewed(); // same as getTarget

	Integer getTotalVotes();
}
