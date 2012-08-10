package org.idiginfo.docservices.webapp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.idiginfo.docservices.AnnotationFactory;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.model.BaseApiParams;

public class DocServicesParams extends BaseApiParams {
	// request params for use in service requests to the
	// org.idiginfo.docservices API

	public static String METHOD_PARAM = "method";
	public static String COLLECTION_PARAM = "collection";
	public static String FORMAT_PARAM = "format";
	public static String ID_PARAM = "id";
	public static String DATE_PARAM = "date";
	public static String API_USER_PARAM = "apiUser";
	public static String OWNER_PARAM = "owner";
	public static String KEYWORD_PARAM = "keyword";
	// From Springer API
	public static String CONSTRAINT_PARAM = "constraint";
	public static String DOI_PARAM = "doi";
	public static String SUBJECT_PARAM = "subject";
	public static String PUB_PARAM = "pub";
	public static String YEAR_PARAM = "year";
	public static String COUNTRY_PARAM = "country";
	public static String ISBND_PARAM = "isbn";
	public static String ISSN_PARAM = "issn";
	public static String OPENACCESS_PARAM = "openaccess";
	public static String TYPE_PARAM = "type";
	public static String IMAGE_TYPE_PARAM = "imageType";
	public static String TITLE_PARAM = "title";
	public static String ORGNAME_PARAM = "orgname";
	public static String JOURNAL_PARAM = "journal";
	public static String BOOK_PARAM = "book";
	public static String NAME_PARAM = "name";
	public static String SORT_PARAM = "sort";

	public static String[] PARAM_NAMES = { METHOD_PARAM, COLLECTION_PARAM,
			FORMAT_PARAM, ID_PARAM, DATE_PARAM, API_USER_PARAM, OWNER_PARAM,
			KEYWORD_PARAM, CONSTRAINT_PARAM, DOI_PARAM, SUBJECT_PARAM,
			PUB_PARAM, YEAR_PARAM, COUNTRY_PARAM, ISBND_PARAM, ISSN_PARAM,
			OPENACCESS_PARAM, TYPE_PARAM, IMAGE_TYPE_PARAM, TITLE_PARAM,
			ORGNAME_PARAM, JOURNAL_PARAM, BOOK_PARAM, NAME_PARAM, SORT_PARAM };

	public static String METHOD_GET_USERS = "getusers";
	public static String METHOD_GET_DOCUMENT = "getdocument";
	public static String METHOD_GET_DOCUMENTS = "getdocuments";
	public static String METHOD_GET_ANNOTATIONS = "getannotations";

	public DocServicesParams() {

	}

	/**
	 * Unpack the request parameters
	 * 
	 * @param request
	 */
	public DocServicesParams(HttpServletRequest request) {
		method = request.getParameter(METHOD_PARAM);
		id = request.getParameter(ID_PARAM);
		date = request.getParameter(DATE_PARAM);
		apiUser = request.getParameter(API_USER_PARAM);
		owner = request.getParameter(OWNER_PARAM);
	}

	/**
	 * Create an ApiServiceParams object to be used in calls to the annotations
	 * service
	 * 
	 * @return
	 */
	public ApiParams getApiServiceParams() {
		ApiParams apiParams = AnnotationFactory.createApiParams(collection);
		if (id != null)
			apiParams.setId(id);
		if (date != null)
			apiParams.setDate(date);
		if (apiUser != null)
			apiParams.setApiUser(apiUser);
		if (owner != null)
			apiParams.setOwner(owner);
		if (keyword != null)
			apiParams.setSearchTerms(keyword);
		return apiParams;
	}

	public static ApiParams getApiServiceParams(
			Map<String, List<String>> queryParams) {
		String collection = null;
		List<String> collections = queryParams.get(COLLECTION_PARAM);
		if (collections != null && collections.size() > 0) {
			collection = collections.get(0);
		}
		return getApiServiceParams(collection, queryParams);
	}

	public static ApiParams getApiServiceParams(String collection,
			Map<String, List<String>> queryParams) {
		ApiParams apiParams = AnnotationFactory.createApiParams(collection);
		mapParams(apiParams, queryParams);
		apiParams.setCollection(collection);
		return apiParams;
	}

	private static void mapParams(ApiParams apiParams,
			Map<String, List<String>> queryParams) {
		// iterate through all parameters and set fields as possible
		// Field[] fields = BaseApiParams.class..getDeclaredFields();
		Set<String> keySet = queryParams.keySet();
		Iterator<String> keyIterator = keySet.iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			List<String> values = queryParams.get(key);
			if (values != null) {
				String firstValue = null;
				if (values.size() > 0)
					firstValue = values.get(0);
				try {
					// use reflection to set field
					Field field = BaseApiParams.class.getDeclaredField(key);
					field.setAccessible(true);
					field.set(apiParams, firstValue);
				} catch (NoSuchFieldException | SecurityException
						| IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();// no such field or not allowed
				}

			}

		}
	}

	public void setParam(String paramName, String paramValue) {
		if (METHOD_PARAM.equals(paramName)) {
			setMethod(paramValue);
		}
		if (COLLECTION_PARAM.equals(paramName)) {
		}
		if (FORMAT_PARAM.equals(paramName)) {
		}
		if (ID_PARAM.equals(paramName)) {
		}
		if (DATE_PARAM.equals(paramName)) {
		}
		if (API_USER_PARAM.equals(paramName)) {
		}
		if (OWNER_PARAM.equals(paramName)) {
		}
		if (KEYWORD_PARAM.equals(paramName)) {
		}
		if (CONSTRAINT_PARAM.equals(paramName)) {
		}
		if (DOI_PARAM.equals(paramName)) {
		}
		if (SUBJECT_PARAM.equals(paramName)) {
		}
		if (PUB_PARAM.equals(paramName)) {
		}
		if (YEAR_PARAM.equals(paramName)) {
		}
		if (COUNTRY_PARAM.equals(paramName)) {
		}
		if (ISBND_PARAM.equals(paramName)) {
		}
		if (ISSN_PARAM.equals(paramName)) {
		}
		if (OPENACCESS_PARAM.equals(paramName)) {
		}
		if (TYPE_PARAM.equals(paramName)) {
		}
		if (IMAGE_TYPE_PARAM.equals(paramName)) {
		}
		if (TITLE_PARAM.equals(paramName)) {
		}
		if (ORGNAME_PARAM.equals(paramName)) {
		}
		if (JOURNAL_PARAM.equals(paramName)) {
		}
		if (BOOK_PARAM.equals(paramName)) {
		}
		if (NAME_PARAM.equals(paramName)) {
		}
		if (SORT_PARAM.equals(paramName)) {
		}
	}
}
