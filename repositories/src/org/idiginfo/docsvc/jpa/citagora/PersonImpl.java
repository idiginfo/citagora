package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraObjectImpl;

@Entity(name="people")
public class PersonImpl implements Person {
    static int objectId = 0;
    String id;
    String type;
    transient int myId = objectId++;
    String givenName;
    String familyName;
    String name;
    String accountName;
    String account;
    String homePage;
    Date created;
    Date updated;

    /**
     * Perform operations required before persisting an object
     */
    @PrePersist
    protected void onCreate() {
	updated = new Date();
	// created may not be the database timestamp. It may be set by the creator
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

    public PersonImpl() {
	setType(Person.TYPE);
	id = CitagoraObjectImpl.makeId(type, myId);
    }

    public String getId() {
	return id;
    }

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
}
