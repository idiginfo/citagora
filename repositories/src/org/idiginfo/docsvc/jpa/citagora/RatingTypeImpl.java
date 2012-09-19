package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Embeddable;

import org.idiginfo.docsvc.model.citagora.RatingType;

@Embeddable
public class RatingTypeImpl implements RatingType {
    private String uri = null;

    public RatingTypeImpl(String type) {
	uri = getUri(type);
    }

    public String getUri() {
	return uri;
    }

    public void setType(String type) {
	uri = RatingType.TYPE_PREFIX + type;
    }

    @Override
    public String getType() {
	return "ratingtype";
    }

    public static String getUri(String type) {
	return RatingType.TYPE_PREFIX + type;
    }
}
