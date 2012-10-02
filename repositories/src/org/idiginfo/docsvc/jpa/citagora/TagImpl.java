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

    public TagImpl() {
	type = Tag.TYPE;
	setCollection(Tag.COLLECTION);
	// initId();
    }

    public CitagoraDocument getTarget() {
	return target;
    }

    @Override
    public CitagoraDocument getDocumentTagged() {
	return (CitagoraDocument) getTarget();
    }

    /**
     * Set both sides of the relationship, carefully. This code is repeated for
     * every ManyToOne field.
     */
    @Override
    public void setTarget(CitagoraObject target) {
	// do nothing if relationship not changed
	if (this.target == target)
	    return;
	// check type of target: must be Comment
	if (target != null && !(target instanceof CitagoraDocument))
	    throw new ClassCastException();
	// remove from inverse relationship
	if (this.target != null) {
	    this.target.getTags().remove(this);
	}
	// set forward relationship
	this.target = (CitagoraDocument) target;
	if (target == null)
	    return;
	// set inverse relationship
	((CitagoraDocument) target).getTags().add(this);
    }

    @Override
    public void setDocumentTagged(CitagoraDocument document) {
	setTarget(document);
    }
}
