package org.idiginfo.docsvc.model.citagora;

/**
 * Interface to implement the Citagora RatingType object
 * 
 * @author griccardi
 * 
 */

public abstract class RatingType implements UriObject {

	public static String getUri(String type) {
		return "http://citagora.com/RatingTypes/" + type;
	}

}
