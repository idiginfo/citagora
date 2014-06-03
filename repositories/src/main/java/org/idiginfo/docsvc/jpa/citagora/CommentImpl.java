package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence Comment object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "comments")
@DiscriminatorValue(value = "comment")
public class CommentImpl extends AnnotationImpl implements Comment {

	@Column(name = "ratingType")
	@SerializedName("commentType")
	String commentType;

	@ManyToOne(targetEntity = ContainerImpl.class, cascade = CascadeType.ALL)
	@GsonTransient
	Container target;

	@ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
	@SerializedName("reviewer")
	Person reviewer;

	@SerializedName("rating")
	Integer rating;

	@OneToMany(mappedBy = "replyTarget", targetEntity = ReplyImpl.class, cascade = CascadeType.ALL)
	@SerializedName("replies")
	List<Reply> replies;

	public CommentImpl() {
		setType(Comment.TYPE);
		setCollection(Comment.COLLECTION);
		// initId();
	}

	public String getType() {
		return Comment.TYPE;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public Person getReviewer() {
		return reviewer;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	@Override
	public void setReviewer(CitagoraAgent reviewer) {
		if (this.reviewer == reviewer)
			return; // no change
		if (this.reviewer != null) {
			// remove from inverse relationship
			this.reviewer.getAgentReviews().remove(this);
		}
		// set forward relationship
		this.reviewer = reviewer;
		if (reviewer == null)
			return;
		// set inverse relationship
		reviewer.getAgentComments().add(this);
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public List<Reply> getReplies() {
		if (replies == null)
			replies = new Vector<Reply>();
		return replies;
	}

	@Override
	public CitagoraObject getTarget() {
		return target;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	@Override
	public void setTarget(CitagoraObject target) {
		if (this.target == target)
			return; // no change
		if (target != null && !(target instanceof Container))
			throw new ClassCastException();
		if (this.target != null) {
			// remove from inverse relationship
			this.target.getComments().remove(this);
		}
		// set forward relationship
		this.target = (Container) target;
		if (target == null)
			return;
		((Container) target).getComments().add(this);
	}

	@Override
	public void addReply(Reply reply) {
		if (reply != null)
			reply.setTarget(this);
	}
}
