package org.idiginfo.docservices.scopus;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class ScopusUrl extends GenericUrl {

	public ScopusUrl(String function) {
		super(ScopusApiParams.API_URL + function);
	}

	public ScopusUrl(String function, String cluster) {
		super(ScopusApiParams.API_URL + function + cluster);
	}

	// Clusters: HUB SCIDIR SCIDIR-OBJECT SCOPUS AUTHOR AFFILIATION

	@Key
	String apiKey = ScopusApiParams.API_KEY;
	@Key
	String preventCache;
	@Key
	String search;
	@Key
	String sort;
	@Key
	String sortDirection;
	@Key
	String sciverseField;
	@Key
	String numResults;
	@Key
	String content;
	@Key
	String offset;
	@Key
	String callback;


	// ={searchTerms}&[{param}=[paramValue]&{param]=[paramValue]...]

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

	protected void mapParams(ScopusApiParams params) {
		if (params == null)
			return;
		// TODO finish method
	}

}
