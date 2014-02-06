package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence Review object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "reviews")
@DiscriminatorValue(value = "review")
public class ReviewImpl extends CitagoraObjectImpl implements Review {
	@SerializedName("ratingType")
	String ratingType; // constant citagora:ratingType
	@SerializedName("rating")
	Integer rating;
	@SerializedName("totalVotes")
	Integer totalVotes;
	@ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
	@SerializedName("reviewer")
	Person reviewer;
	@ManyToOne(targetEntity = ContainerImpl.class, cascade = CascadeType.ALL)
	@GsonTransient
	Container documentReviewed;

	public ReviewImpl() {
		setType(Review.TYPE);
		setCollection(Review.COLLECTION);
		// initId();
	}

	// Container documentReviewed; // same as getTarget
	public Integer getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getRatingType() {
		return ratingType;
	}

	@Override
	public Person getReviewer() {
		return reviewer;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	@Override
	public void setReviewer(Person reviewer) {
		// do nothing if relationship not changed
		if (this.reviewer == reviewer)
			return;
		// remove from inverse relationship
		if (this.reviewer != null) {
			this.reviewer.getAgentReviews().remove(this);
		}
		// set forward relationship
		this.reviewer = reviewer;
		if (reviewer == null)
			return;
		// set inverse relationship
		reviewer.getAgentReviews().add(this);
	}

	@Override
	public Container getDocumentReviewed() {
		return documentReviewed;
	}

	/**
	 * Set both sides of the relationship, carefully. This code is repeated for
	 * every ManyToOne field.
	 */
	@Override
	public void setDocumentReviewed(Container documentReviewed) {
		// do nothing if relationship not changed
		if (this.documentReviewed == documentReviewed)
			return;
		// remove from inverse relationship
		if (this.documentReviewed != null) {
			this.documentReviewed.getReviews().remove(this);
		}
		// set forward relationship
		this.documentReviewed = documentReviewed;
		if (documentReviewed == null)
			return;
		// set inverse relationship
		documentReviewed.getReviews().add(this);
	}

	public void setRatingType(String string) {
		this.ratingType = string;
	}

	@Override
	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(int i) {
		this.totalVotes = i;

	}
}
