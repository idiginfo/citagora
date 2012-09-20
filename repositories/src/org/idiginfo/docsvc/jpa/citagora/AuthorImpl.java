package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.Reference;

@Entity(name = "authors")
public class AuthorImpl extends PersonImpl implements Author {

    @ManyToMany(mappedBy = "authorList", targetEntity = ReferenceImpl.class)
    List<Reference> references;

    public List<Reference> getReferences() {
	return references;
    }

    public void setReferences(List<Reference> references) {
	this.references = references;
    }

}
