package org.idiginfo.docsvc.model.citagoranew;

public class RatingType implements UriObject {
    private String uri = null;

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
