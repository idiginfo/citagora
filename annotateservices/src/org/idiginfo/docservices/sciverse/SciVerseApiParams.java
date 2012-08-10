package org.idiginfo.docservices.sciverse;

import org.idiginfo.docservices.model.BaseApiParams;

public class SciVerseApiParams extends BaseApiParams {

	public static final String API_URL = "http://api.elsevier.com/content/";

	// Content Categories
	String[] contentCategories = { "Article", "Abstract", "Author",
			"Affiliation" };


}
