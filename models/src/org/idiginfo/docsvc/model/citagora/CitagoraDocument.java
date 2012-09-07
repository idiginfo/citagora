package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraDocument extends CitagoraObject {
	List<Annotation> getRatings();
	List<Annotation> getTags();
	List<Annotation> getComments();
	Reference getIsAbout();
}
