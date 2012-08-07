package org.idiginfo.rest;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.idiginfo.annotate.webapp.ServiceParams;

public class RestParams extends ServiceParams {

	public RestParams(Map<String, List<String>> queryParams) {
		method = getParam(queryParams, METHOD_PARAM);
		code = getParam(queryParams, CODE_PARAM);
		date = getParam(queryParams, DATA_PARAM);
		apiUser = getParam(queryParams, API_USER_PARAM);
		apiAnnotateUser = getParam(queryParams, API_ANNOTATE_USER_PARAM);
	}

	private String getParam(Map<String, List<String>> queryParams,
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
