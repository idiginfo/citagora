package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.Reference;

public class AuthorImpl extends PersonImpl implements Author {

	transient List<Reference> references;

	public List<Reference> getReferences() {
		return references;
	}

	public void setReferences(List<Reference> references) {
		this.references = references;
	}

}
