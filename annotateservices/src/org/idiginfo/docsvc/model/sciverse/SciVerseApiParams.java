package org.idiginfo.docsvc.model.sciverse;

import org.idiginfo.docsvc.model.model.BaseApiParams;

public class SciVerseApiParams extends BaseApiParams {

	public static final String API_URL = "http://api.elsevier.com/content/";

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
