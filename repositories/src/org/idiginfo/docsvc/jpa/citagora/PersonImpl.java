package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Reference;

@Entity(name = "people")
public class PersonImpl implements Person, CitagoraAgent, Author {
	@Id
	Integer myId;
	String type;
	String uri;

	String givenName;
	String familyName;
	String name;
	String accountName;
	String account;
	String homePage;
	@Temporal(TemporalType.TIMESTAMP)
	Date created;
	@Temporal(TemporalType.TIMESTAMP)
	Date updated;
	Boolean isAuthor;
	Boolean isAgent;
	Boolean isPerson;

	// Author fields
	@ManyToMany(mappedBy = "authorList", targetEntity = ReferenceImpl.class)
	List<Reference> authorReferences;

	// Agent fields
	@OneToMany(mappedBy = "generator", targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.PERSIST)
	List<CitagoraDocument> agentDocuments;
	// List<Annotation> comments;
	// List<Annotation> tags;
	@OneToMany(mappedBy = "contributedBy", targetEntity = ReferenceImpl.class, cascade = CascadeType.PERSIST)
	List<Reference> agentReferences;

	// Non-persistent members
	@Transient
	transient static int objectId = 0;
	@Transient
	transient String myCollection;
	@Transient
	String id = null;

	public PersonImpl() {
		setType(Person.TYPE);
		// id = CitagoraObjectImpl.makeId(type, myId);
	}

	public PersonImpl(String type) {
		setType(type);
	}

	public static Person createCitagoraAgent() {
		return new PersonImpl(CitagoraAgent.TYPE);
	}

	public static Person createAuthor() {
		return new PersonImpl(Author.TYPE);
	}

	/**
	 * Perform operations required before persisting an object
	 */
	@PrePersist
	protected void onCreate() {
		updated = new Date();
		// created may not be the database timestamp. It may be set by the
		// creator
		if (created == null)
			created = updated;
	}

	/**
	 * Perform operations required before updating an object
	 */
	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

	public String getUri() {
		return id;
	}

	public String getId() {
		return id;
	}

	// private void setMyId(Integer id) {
	// this.myId = id;
	// }

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Boolean getIsAuthor() {
		return isAuthor;
	}

	public void setIsAuthor(Boolean isAuthor) {
		this.isAuthor = isAuthor;
	}

	public Boolean getIsAgent() {
		return isAgent;
	}

	public void setIsAgent(Boolean isAgent) {
		this.isAgent = isAgent;
	}

	public Boolean getIsPerson() {
		return isPerson;
	}

	public void setIsPerson(Boolean isPerson) {
		this.isPerson = isPerson;
	}

	public String getMyCollection() {
		return myCollection;
	}

	public void setMyCollection(String myCollection) {
		this.myCollection = myCollection;
	}

	public Integer getMyId() {
		return myId;
	}

	public Date getUpdated() {
		return updated;
	}

	public List<Reference> getAuthorReferences() {
		return authorReferences;
	}

	public List<Reference> getAgentReferences() {
		return agentReferences;
	}

	@Override
	public List<Annotation> getAgentComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getAgentTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getAgentAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CitagoraDocument> getAgentDocuments() {
		return agentDocuments;
	}

}
