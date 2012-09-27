package org.idiginfo.docsvc.model.citagora;

public abstract class RatingType implements UriObject {

    public static String getUri(String type) {
	return "http://citagora.com/RatingTypes/" + type;
    }

}
