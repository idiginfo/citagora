package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence Container object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "containers")
@DiscriminatorValue(value = Container.COLLECTION)
public class ContainerImpl extends CitagoraObjectImpl implements Container {

	@ManyToOne(targetEntity = ReferenceImpl.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@SerializedName("reference")
	Reference isAbout;

	@OneToMany(mappedBy = "documentReviewed", targetEntity = ReviewImpl.class, cascade = CascadeType.ALL)
	@SerializedName("reviews")
	List<Review> reviews;
	@OneToMany(mappedBy = "target", targetEntity = TagImpl.class, cascade = CascadeType.ALL)
	@SerializedName("tags")
	List<Tag> tags;
	@OneToMany(mappedBy = "target", targetEntity = CommentImpl.class, cascade = CascadeType.ALL)
	@SerializedName("comments")
	List<Comment> comments;

	public ContainerImpl() {
		setType(Container.TYPE);
		setCollection(Container.COLLECTION);
		// initId();
	}

	public ContainerImpl(CitagoraObject containerFields) {
		this();
		setGenerated(containerFields.getGenerated());
		setCreated(containerFields.getCreated());
		setGenerator(containerFields.getGenerator());
		setRights(containerFields.getRights());
		setSource(containerFields.getSource());
		setWasAttributedTo(containerFields.getWasAttributedTo());
	}

	@Override
	public List<Tag> getTags() {
		if (tags == null)
			tags = new Vector<Tag>();
		return tags;
	}

	@Override
	public List<Comment> getComments() {
		if (comments == null)
			comments = new Vector<Comment>();
		return comments;
	}

	public Reference getIsAbout() {
		return isAbout;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	public void setIsAbout(Reference isAbout) {
		if (this.isAbout == isAbout)
			return; // no change
		if (this.isAbout != null) {
			// remove from inverse relationship
			this.isAbout.getContainers().remove(this);
		}
		// set forward relationship
		this.isAbout = isAbout;
		if (isAbout == null)
			return;
		isAbout.getContainers().add(this);
	}

	@Override
	public List<Review> getReviews() {
		if (reviews == null)
			reviews = new Vector<Review>();
		return reviews;
	}

	@Override
	public void addReview(Review review) {
		if (review != null)
			review.setDocumentReviewed(this);

	}

	@Override
	public void addTag(Tag tag) {
		if (tag != null)
			tag.setTarget(this);
	}

	@Override
	public void addComment(Comment comment) {
		if (comment != null)
			comment.setTarget(this);
	}

}
