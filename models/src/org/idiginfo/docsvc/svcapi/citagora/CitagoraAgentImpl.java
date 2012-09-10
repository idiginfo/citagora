package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;

import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.*;

public class CitagoraAgentImpl extends PersonImpl implements CitagoraAgent {
	transient List<CitagoraDocument> documents;
	// List<Annotation> comments;
	// List<Annotation> tags;
	transient List<Reference> references;

	public CitagoraAgentImpl() {
		setType(CitagoraAgent.TYPE);
		//initId();
	}

	// List<Annotation> annotations;
	public List<CitagoraDocument> getDocuments() {
		return documents;
	}

	public void setDocuments(List<CitagoraDocument> documents) {
		this.documents = documents;
	}

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

	@Override
	public List<Annotation> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

}
