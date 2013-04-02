package org.idiginfo.docsvc.svcapi.sciverse;

import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class SciVerseApiParams extends BaseApiParams {

	public static final String API_URL = "http://api.elsevier.com/content/";
	public static final String AUTH_URL = "http://api.elsevier.com/authenticate?platform=SCOPUS";
	public static final String API_KEY = "32044be7be3a652a32654afeae5bf4d1"; // Riccardi's key
	public static final String SOURCE = "elsevier.com";
	// Content Categories
	String[] contentCategories = { "Article", "Abstract", "Author",
			"Affiliation" };

	//request field names
	public static final String PREVENT_CACHE_PARAM = "preventCache";
	public static final String API_KEY_PARAM = "apiKey";
	public static final String SEARCH_PARAM = "search";
	public static final String SORT_PARAM = "sort";
	public static final String SORT_DIRECTION_PARAM = "sortDirection";
	public static final String FIELDS_PARAM = "sciverseField";
	public static final String NUM_RESULTS_PARAM = "numResults";
	public static final String CONTENT_PARAM = "content";
	public static final String OFFSET_PARAM = "offset";
	public static final String CALL_BACK_PARAM = "callback";



}
