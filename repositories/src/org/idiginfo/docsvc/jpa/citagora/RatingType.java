package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Embeddable;

import org.idiginfo.docsvc.model.citagora.UriObject;

@Embeddable
public class RatingType implements UriObject {
    private String uri = null;

    public RatingType(){
	
    }
    public RatingType(String type) {
	uri = getUri(type);
    }

    public String getUri() {
	return uri;
    }

    public static String getUri(String type) {
	return "http://citagora.com/RatingTypes/" + type;
    }

    @Override
    public String getType() {
	return "ratingtype";
    }
}
