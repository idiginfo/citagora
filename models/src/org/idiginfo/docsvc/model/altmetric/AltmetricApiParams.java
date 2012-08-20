package org.idiginfo.docsvc.model.altmetric;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.BaseApiParams;

public class AltmetricApiParams extends BaseApiParams {

	public static final String API_VERSION = "v1";
	public static final String API_URL = "http://api.altmetric.com/";
	// + API_VERSION + "/";;
	public static final String API_KEY = "6b3b89d864f4d7bfc42d5254a00ad5c7";
	public static final String API_DOCS = "http://api.altmetric.com/";
	public static final String SOURCE = "altmetric.com";
	public static final String API_HOST = "api.altmetric.com";

	// citations parameters
	// http://api.altmetric.com/{version}/citations/{timeframe}
	String timeframe;
	String[] timeframeValues = { "at", "1d", "2d", "3d", "4d", "5d", "6d",
			"1w", "1m", "3m", "1y" };
	String page;
	String numResults;// num_results
	String citedIn; // cited_in
	String[] citedInValues = {
			// cited_in One or more comma delimited options from:
			"facebook", "blogs", "twitter", "reddit", "news", "f1000", "rh",
			"qna", "forum" };
	String doiPrefix; // doi_prefix
	// A DOI prefix (the bit before the first slash, e.g. 10.1038)
	// Include only articles with a DOI that contains the given prefix.
	String nlmid; // Comma delimited list of journal NLM IDs
	String subject; // Comma delimited list of slugified journal subjects
	// Include only articles from journals matching any of the supplied NLM
	// subject ontology term(s).
	
	// Get by altmetric id
	// path: /{version}/id/{altmetric id}
	// returns document as in 'citations'
	
	// Get by doi
	// path /{version}/doi/{doi}

	// Get by PubMed id
	// path /{version}/pmid/{pmid}

	// Get by arxid
	// path /{version}/arxid/{arxid}
	
	// get by altmetric id with details
	// path /{version}/details/{altmetric id}


	public AltmetricApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		this.apiUser = apiParams.getApiUser();
		setId(apiParams.getId());
		setDate(apiParams.getDate());
		setSearchTerms(apiParams.getSearchTerms());
	}

	public AltmetricApiParams() {
	}
}
