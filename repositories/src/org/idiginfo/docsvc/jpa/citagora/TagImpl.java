package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Tag;

@Entity(name = "tags")
@DiscriminatorValue(value = "tag")
public class TagImpl extends AnnotationImpl implements Tag {

    public static final String TYPE = "oax:tag";

    @ManyToOne(targetEntity = CitagoraDocumentImpl.class)
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
	initId();
    }

    @Override
    public CitagoraDocument getDocumentTagged() {
	return (CitagoraDocument) getTarget();
    }
}
