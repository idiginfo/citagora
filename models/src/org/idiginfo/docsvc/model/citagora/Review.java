package org.idiginfo.docsvc.model.citagora;

public interface Review extends CitagoraObject {
	static final String TYPE = "http://purl.org/stuff/rev#Review";

	String getRatingType(); // constant citagora:ratingType

	Person getReviewer(); // same as getAnnotator

	Integer getRating();

	CitagoraDocument getDocumentReviewed(); // same as getTarget

	Integer getTotalVotes();
}
