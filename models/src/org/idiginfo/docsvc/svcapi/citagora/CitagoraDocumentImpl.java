package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Review;

public class CitagoraDocumentImpl extends CitagoraObjectImpl implements
		CitagoraDocument {

	Reference isAbout;
	List<Review> reviews;

	public CitagoraDocumentImpl() {
		setType(CitagoraDocument.TYPE);
		setCollection(CitagoraDocument.COLLECTION);
		initId();
	}

	@Override
	public List<Annotation> getRatings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reference getIsAbout() {
		return isAbout;
	}

	public void setIsAbout(Reference isAbout) {
		this.isAbout = isAbout;
	}

	public List<Review> getReviews() {
		if (reviews == null)
			reviews = new Vector<Review>();
		return reviews;
	}

	public void addReview(Review review) {
		if (reviews==null){
			reviews = new Vector<Review>();
		}
		reviews.add(review);
	}

}
