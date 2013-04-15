package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.HarvestResult;
import org.idiginfo.docsvc.model.citagora.Reference;

@Entity
@Table(name = "harvest_results")
public class HarvestResultImpl implements HarvestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer myId;
    String source;
    @Temporal(TemporalType.TIMESTAMP)
    Date created;
    @Temporal(TemporalType.TIMESTAMP)
    Date updated;

    String identifier;
    @ManyToOne(targetEntity = ReferenceImpl.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    Reference reference;
    String description;
    Boolean success;
    String uri;

    @PrePersist
    protected void onCreate() {
	created = updated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
	updated = new Date();
    }

    public static HarvestResult createHarvestResult(String source,
	    String identifier, Reference ref, String description,
	    Boolean success) {
	CitagoraFactory factory = CitagoraFactory.getFactory();
	HarvestResult harvestResult = new HarvestResultImpl();
	harvestResult.setSource(source);
	harvestResult.setDescription(description);
	harvestResult.setIdentifier(identifier);
	harvestResult.setReference(ref);
	harvestResult.setSuccess(success);
	factory.merge(harvestResult);
	return harvestResult;
    }

    @Override
    public Integer getMyId() {
	return myId;
    }

    @Override
    public void setMyId(Integer myId) {
	this.myId = myId;
    }

    @Override
    public String getSource() {
	return source;
    }

    @Override
    public void setSource(String source) {
	this.source = source;
    }

    @Override
    public String getIdentifier() {
	return identifier;
    }

    @Override
    public void setIdentifier(String doi) {
	this.identifier = doi;
    }

    @Override
    public Reference getReference() {
	return reference;
    }

    @Override
    public void setReference(Reference reference) {
	this.reference = reference;
    }

    @Override
    public String getDescription() {
	return description;
    }

    @Override
    public void setDescription(String description) {
	this.description = description;
    }

    @Override
    public Boolean getSuccess() {
	return success;
    }

    @Override
    public void setSuccess(Boolean success) {
	this.success = success;
    }

    @Override
    public Date getCreated() {
	return created;
    }

    @Override
    public String getUri() {
	if (uri == null && myId != null) {
	    uri = CitagoraObjectImpl.makeId(HarvestResult.TYPE, myId);
	}
	return uri;
    }

    @Override
    public String getType() {
	// TODO Auto-generated method stub
	return HarvestResult.TYPE;
    }

}
