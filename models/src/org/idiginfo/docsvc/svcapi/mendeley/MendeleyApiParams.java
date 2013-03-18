package org.idiginfo.docsvc.svcapi.mendeley;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

/**
 * Class that implements the Mendeley API 
 * 
 * @author sflager
 * 
 */

public class MendeleyApiParams extends BaseApiParams {

	// public static final String API_URL =
	// "http://api.mendeley.com/oapi/documents/search/<terms>/API_KEY";
	// "http://api.mendeley.com/oapi/documents/details/<recordkey>/API_KEY";
	public static final String API_KEY = "3b3e09b191cad40ad84e2ea3d65a9c8b05102a378";
	public static final String API_URL = "http://api.mendeley.com";
	public static final String API_HOST = "api.mendeley.com";
	public static final String API_SERVICE = "oapi/documents";
	public static final String API_SEARCH = "search";
	public static final String API_DETAILS = "details";

	public static final String[] PUBLICATION_CONTENT_TYPE_VALUES = {
			"title:", "author:", "year:", "published_in:" };

	public static final String[] RESPONSE_CODE_MESSAGES = {
			"OK",// 200
			"Created",// 201
			"No Content",// 204
			"Bad Request",// 400
			"Unauthorized",// 401
			"Forbidden",// 403
			"Not Found",// 404
			"Service Unavailable" // 503
	};
	public static final int UNKNOWN_ERROR_CODE = 503;

	public MendeleyApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		this.apiUser = apiParams.getApiUser();
		setId(apiParams.getId());
		setDate(apiParams.getDate());
		setSearchTerms(apiParams.getSearchTerms());
	}

	public MendeleyApiParams() {
	}

}
