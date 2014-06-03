package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Embeddable;

import org.idiginfo.docsvc.model.citagora.RatingType;

/**
 * Class to implement the Citagora persistence RatingType object
 * 
 * @author griccardi
 * 
 */

@Embeddable
public class RatingTypeImpl extends RatingType {
	private String uri = null;

	public RatingTypeImpl() {

	}

	public RatingTypeImpl(String type) {
		uri = getUri(type);
	}

	public String getUri() {
		return uri;
	}

	@Override
	public String getType() {
		return "ratingtype";
	}

	@Override
	public Integer getMyId() {
		// TODO Auto-generated method stub
		return null;
	}
}
