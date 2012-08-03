package org.idiginfo.sciverse.services;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class SciVerseUrl extends GenericUrl {

	public SciVerseUrl(String function) {
		super(SciVerseApiParams.API_URL + function);
	}

	public SciVerseUrl(String function, String cluster) {
		super(SciVerseApiParams.API_URL + function + cluster);
	}

	// Clusters: HUB SCIDIR SCIDIR-OBJECT SCOPUS AUTHOR AFFILIATION

	@Key
	String query;
	@Key
	String date;
	@Key
	String view;
	@Key
	String field;
	@Key
	String content;
	@Key
	String count;
	@Key
	String start;
	@Key
	String sort;
	@Key
	String subj;
	@Key
	String subscribed;
	@Key
	String facet;
	@Key
	String httpAccept;
	@Key
	String collapse;
	@Key("co-author")
	String coAuthor;

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

	protected void mapParams(SciVerseApiParams params) {
		if (params == null) return;
		// TODO finish method
	}

}
