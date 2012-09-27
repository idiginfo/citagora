package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Tag;

@Entity(name = "tags")
@DiscriminatorValue(value = "tag")
public class TagImpl extends AnnotationImpl implements Tag {

    @ManyToOne(targetEntity = CitagoraDocumentImpl.class, cascade = CascadeType.ALL)
    CitagoraDocument target;

    public CitagoraDocument getTarget() {
	return target;
    }

    public void setTarget(CitagoraDocument target) {
	this.target = target;
    }

    public TagImpl() {
	type = Tag.TYPE;
	setCollection(Tag.COLLECTION);
	//initId();
    }

    @Override
    public CitagoraDocument getDocumentTagged() {
	return (CitagoraDocument) getTarget();
    }

    @Override
    public void setTarget(CitagoraObject target) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void setDocumentTagged(CitagoraDocument document) {
	// TODO Auto-generated method stub
	
    }
}
