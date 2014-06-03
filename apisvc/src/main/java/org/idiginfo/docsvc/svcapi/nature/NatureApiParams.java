package org.idiginfo.docsvc.svcapi.nature;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

/**
 * Class to implement BaseApiParams object for Nature
 * 
 * @author griccardi
 * 
 */

public class NatureApiParams extends BaseApiParams {

	public static final String API_URL = "http://api.nature.com/content/opensearch/request";
	public static final String API_KEY = "t22df88zg2fzy726wr4cmuue";
	public static final String SOURCE = "nature.com";

	// http://api.nature.com/content/opensearch/request?query=darwin1&api_key=<API
	// key string here>

	// Content Categories

	public NatureApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		setSearchTerms(apiParams.getSearchTerms());
	}

	public NatureApiParams() {
	}
}
