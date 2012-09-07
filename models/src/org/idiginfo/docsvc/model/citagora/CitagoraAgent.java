package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraAgent extends Person {
	List<CitagoraDocument> getDocuments();
	List<Annotation> getComments();
	List<Annotation> getTags();
	List<Reference> getReferences();
	List<Annotation> getAnnotations();
}
