/**
 * Package for implementation of the Citagora data model, 
 * using the interfaces of package org.idiginfo.docsvc.model.citagora
 */
/**
 * @author griccardi
 * 
 * This package provides implementations of the Citagora data model objects that are used for
 * persistence in the database using JPA. Annotations form javax.persistence are provided to control
 * 	 the persistence
 * 
 * The methods used to create and remove relationships among values for many-to-one relationships is 
 * contained in the setter methods on the to-one side (foreign key) of the relationship
 * 
 * We have 2 situations: one with a dynamic type constraint and one without
 * Without a dynamic type constraint the code follows this form from class ReferenceImpl:
    @Override
    public void setIsPartOf(Reference isPartOf) {
	// do nothing if relationship not changed
	if (this.isPartOf == isPartOf)
	    return;
	// remove from inverse relationship
	if (this.isPartOf != null) {
	    this.isPartOf.getContains().remove(this);
	}
	// set forward relationship
	this.isPartOf = isPartOf;
	if (isPartOf == null)
	    return;
	// set inverse relationship
	isPartOf.getContains().add(this);
    }
 *
 * With a dynamic type constraint the code follows this form from class ReplyImpl:
 * 
    @Override
    public void setTarget(CitagoraObject replyTarget)  {
	// do nothing if relationship not changed
	if (this.replyTarget == replyTarget)
	    return;
	// check type of target: must be Comment
	if (target != null && !(target instanceof Comment))
	    throw new ClassCastException();
	// remove from inverse relationship
	if (this.replyTarget != null) {
	    this.replyTarget.getReplies().remove(this);
	}
	// set forward relationship
	this.replyTarget = (Comment) replyTarget;
	if (replyTarget == null)
	    return;
	// set inverse relationship
	((Comment) replyTarget).getReplies().add(this);
    }
*
* ManyToMany relationshipe from the owner (no mappedBy) side follow this form:
* 
    //many-to-many owner side (no mappedBy parameter)
    @Override
    public void addIsCitedBy(Reference reference) {
	List<Reference> isCitedBy = getIsCitedBy();
	// check for existing relationship
	if (reference == null || isCitedBy.contains(reference))
	    return;
	// add both sides of relationship
	reference.getCitationList().add(this);
	isCitedBy.add(reference);
    }
    // owner side of many-to-many
    @Override
    public void removeIsCitedBy(Reference reference) {
	List<Reference> isCitedBy = getIsCitedBy();
	// check for no existing relationship
	if (reference == null || !isCitedBy.contains(reference))
	    return;
	// remove both sides of relationship
	reference.getCitationList().remove(this);
	isCitedBy.remove(reference);

    }

 */
 
package org.idiginfo.docsvc.jpa.citagora;