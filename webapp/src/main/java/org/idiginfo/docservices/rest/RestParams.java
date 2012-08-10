package org.idiginfo.docservices.rest;

import java.util.List;
import java.util.Map;

import org.idiginfo.docservices.webapp.DocServicesParams;

public class RestParams extends DocServicesParams {

	public RestParams(Map<String, List<String>> queryParams) {
		method = getParam(queryParams, METHOD_PARAM);
		id = getParam(queryParams, ID_PARAM);
		date = getParam(queryParams, DATE_PARAM);
		apiUser = getParam(queryParams, API_USER_PARAM);
		owner = getParam(queryParams, OWNER_PARAM);
		keyword = getParam(queryParams, KEYWORD_PARAM);
	}

	private static String getParam(Map<String, List<String>> queryParams,
			String paramName) {
		List<String> values;
		values = queryParams.get(paramName);
		if (values == null || values.size() <= 0)
			return null;
		return values.get(0);
	}

	public RestParams() {
		// TODO Auto-generated constructor stub
	}
}
