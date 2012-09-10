package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraDocument extends CitagoraObject {
	static final String TYPE = "http://citagora.com/rdf#Document";
	static final String COLLECTION = "document";

	List<Annotation> getRatings();
	List<Annotation> getTags();
	List<Annotation> getComments();
	Reference getIsAbout();
}
