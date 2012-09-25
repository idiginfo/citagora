package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraAgent extends Person {
	String TYPE = "citagora:agent";
	List<CitagoraDocument> getAgentDocuments();
	List<Annotation> getAgentComments();
	List<Annotation> getAgentTags();
	List<Reference> getAgentReferences();
	List<Annotation> getAgentAnnotations();
}
