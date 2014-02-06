package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

/**
 * Class to implement the Citagora persistence Tag object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "tags")
@DiscriminatorValue(value = "tag")
public class TagImpl extends AnnotationImpl implements Tag {

	@ManyToOne(targetEntity = ContainerImpl.class, cascade = CascadeType.ALL)
	@GsonTransient
	Container target;

	public TagImpl() {
		type = Tag.TYPE;
		setCollection(Tag.COLLECTION);
		// initId();
	}

	public Container getTarget() {
		return target;
	}

	@Override
	public Container getDocumentTagged() {
		return (Container) getTarget();
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
		if (target != null && !(target instanceof Container))
			throw new ClassCastException();
		// remove from inverse relationship
		if (this.target != null) {
			this.target.getTags().remove(this);
		}
		// set forward relationship
		this.target = (Container) target;
		if (target == null)
			return;
		// set inverse relationship
		((Container) target).getTags().add(this);
	}

	@Override
	public void setDocumentTagged(Container document) {
		setTarget(document);
	}

	@Override
	public String getSpecifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpecifier(String specifier) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTags(String tags) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getTags() {
		// TODO Auto-generated method stub
		return null;
	}
}
