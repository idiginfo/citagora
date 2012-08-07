package org.idiginfo.rest;

import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.idiginfo.annotate.webapp.ServiceParams;

public class RestParams extends ServiceParams {

	public RestParams(MultivaluedMap<String, String> queryParams) {
		List<String> values;
		values = queryParams.get(METHOD_PARAM);
		if (values != null && values.size() > 0)
			method = values.get(0);
		values = queryParams.get(CODE_PARAM);
		if (values != null && values.size() > 0)
			code = values.get(0);
		values = queryParams.get(DATA_PARAM);
		if (values != null && values.size() > 0)
			date = values.get(0);
		values = queryParams.get(API_USER_PARAM);
		if (values != null && values.size() > 0)
			apiUser = values.get(0);
		values = queryParams.get(API_ANNOTATE_USER_PARAM);
		if (values != null && values.size() > 0)
			apiAnnotateUser = values.get(0);
	}
}
