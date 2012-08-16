package org.idiginfo.docsvc.controller.request;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.BaseApiParams;

public class DocServicesParams extends BaseApiParams {
	// request params for use in service requests to the
	// org.idiginfo.docservices API

	// Parameter name strings for use in HTTP requests
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

	public static String FORMAT_RDF = "rdf";
	public static String FORMAT_HTML = "html";
	public static String FORMAT_XLS = "xls";
	public static String FORMAT_JSON = "json";

	public DocServicesParams() {

	}

	/**
	 * Create an ApiParams object, of proper type from an HTTP parameter map
	 * 
	 * @param queryParams
	 * @return
	 */
	public static ApiParams getApiServiceParams(
			Map<String, List<String>> queryParams) {
		String collection = null;
		List<String> collections = queryParams.get(COLLECTION_PARAM);
		if (collections != null && collections.size() > 0) {
			collection = collections.get(0);
		}
		return getApiServiceParams(collection, queryParams);
	}

	/**
	 * Create an ApiParams object, of proper type from an HTTP parameter map
	 * 
	 * @param collection
	 * @param queryParams
	 * @return
	 */
	public static ApiParams getApiServiceParams(String collection,
			Map<String, List<String>> queryParams) {
		ApiParams apiParams = ServiceFactory.createApiParams(collection);
		mapParams(apiParams, queryParams);
		apiParams.setCollection(collection);
		return apiParams;
	}

	/**
	 * Use reflection methods to set fields of an ApiParam object from an HTTP
	 * parameter map
	 * 
	 * @param apiParams
	 * @param queryParams
	 */
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

	Map<String, List<String>> createMap() {
		// TODO add method to create a query parameter block from a params
		// object
		return null;
	}

}
