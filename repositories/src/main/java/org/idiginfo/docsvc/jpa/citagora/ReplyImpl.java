package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

/**
 * Class to implement the Citagora persistence Reply object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "replies")
@DiscriminatorValue(value = "reply")
public class ReplyImpl extends CommentImpl implements Reply {

	@ManyToOne(targetEntity = CommentImpl.class, cascade = CascadeType.ALL)
	@GsonTransient
	Comment replyTarget;

	public ReplyImpl() {
		setType(Comment.TYPE);
		setCollection(Comment.COLLECTION);
		// initId();
	}

	public String getType() {
		return Comment.TYPE;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field. This method includes type constraint to related
	 * object
	 */
	@Override
	public void setTarget(CitagoraObject replyTarget) {
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

}
