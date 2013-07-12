package org.idiginfo.docsvc.svcapi.crossref;

import com.google.api.client.http.GenericUrl;
import com.google.gson.JsonElement;

/**
 * Class to implement GenericUrl object for Crossref
 * 
 * @author griccardi
 * 
 */

public class CrossrefRdfUrl extends GenericUrl {

	public CrossrefRdfUrl(String doi) {
		super(CrossrefApiParams.RDF_URL + doi);
		// apiKey = CrossrefApiParams.API_KEY;
	}

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {

	}

	public static boolean isError(String content) {
		if (content.startsWith("XXX")) {
			return true;
		}
		return false;
	}

	public static boolean isError(JsonElement json) {
		if (json == null)
			return true;
		// TODO Auto-generated method stub
		return false;
	}

}
