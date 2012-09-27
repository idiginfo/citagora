package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface CitagoraAgent extends Person {
	String TYPE = "citagora:agent";
	List<CitagoraDocument> getAgentDocuments();
	void addAgentDocument(CitagoraDocument document);
	List<Comment> getAgentComments();
	void addAgentComment(Comment document);
	List<Tag> getAgentTags();
	void addAgentTag(Tag document);
	List<Reference> getAgentReferences();
	void addAgentReference(Reference document);
}
