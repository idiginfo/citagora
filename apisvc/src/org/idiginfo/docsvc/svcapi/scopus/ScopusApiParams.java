package org.idiginfo.docsvc.svcapi.scopus;

import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

/**
 * Class to implement BaseApiParams object for Scopus
 * 
 * @author griccardi
 * 
 */

public class ScopusApiParams extends BaseApiParams {

	// public static final String API_URL = "http://api.elsevier.com/content/";
	public static final String API_URL = "http://searchapi.scopus.com/";
	public static final String API_KEY = "53c813e3f5a58b232219c80be5dbdea1";
	public static final String SOURCE = "scopus.com";

	// Content Categories
	String[] contentCategories = { "Article", "Abstract", "Author",
			"Affiliation" };

	// request field names
	public static final String PREVENT_CACHE_PARAM = "preventCache";
	public static final String API_KEY_PARAM = "apiKey";
	public static final String SEARCH_PARAM = "search";
	public static final String SORT_PARAM = "sort";
	public static final String SORT_DIRECTION_PARAM = "sortDirection";
	public static final String FIELDS_PARAM = "ScopusField";
	public static final String NUM_RESULTS_PARAM = "numResults";
	public static final String CONTENT_PARAM = "content";
	public static final String OFFSET_PARAM = "offset";
	public static final String CALL_BACK_PARAM = "callback";

}
