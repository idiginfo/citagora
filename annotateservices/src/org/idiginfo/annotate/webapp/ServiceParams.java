package org.idiginfo.annotate.webapp;

import javax.servlet.http.HttpServletRequest;

import org.idiginfo.annotate.services.AnnotateApiParams;

public class ServiceParams {
	// request params
	public static String METHOD_PARAM = "method";
	public static String FORMAT_PARAM = "format";
	// params for annotation service
	public static String CODE_PARAM = "code";
	public static String DATA_PARAM = "date";
	public static String API_USER_PARAM = "apiUser";

	public static String API_ANNOTATE_USER_PARAM = "apiAnnotateUser";

	protected String method;
	protected String collection; // the source collection
	protected String format;
	protected String code;
	protected String date;
	protected String apiUser;
	protected String apiAnnotateUser;

	public static String METHOD_GET_USERS = "getusers";
	public static String METHOD_GET_DOCUMENT = "getdocument";
	public static String METHOD_GET_DOCUMENTS = "getdocuments";
	public static String METHOD_GET_ANNOTATIONS = "getannotations";

	public static String COLLECTION_ANNOTATE = "annotate";
	public static String COLLECTION_SPRINGER = "springer";
	public static String COLLECTION_ELSEVIER = "elsevier";
	public static String[] COLLECTIONS = { COLLECTION_ANNOTATE,
			COLLECTION_SPRINGER, COLLECTION_ELSEVIER };

	public ServiceParams() {

	}

	/**
	 * Unpack the request parameters
	 * 
	 * @param request
	 */
	public ServiceParams(HttpServletRequest request) {
		method = request.getParameter(METHOD_PARAM);
		code = request.getParameter(CODE_PARAM);
		date = request.getParameter(DATA_PARAM);
		apiUser = request.getParameter(API_USER_PARAM);
		apiAnnotateUser = request.getParameter(API_ANNOTATE_USER_PARAM);
	}

	/**
	 * Create an ApiServiceParams object to be used in calls to the annotations
	 * service
	 * 
	 * @return
	 */
	public AnnotateApiParams getApiServiceParams() {
		AnnotateApiParams apiParams = new AnnotateApiParams();
		if (code != null)
			apiParams.setCode(code);
		if (date != null)
			apiParams.setDate(date);
		if (apiUser != null)
			apiParams.setApiUser(apiUser);
		if (apiAnnotateUser != null)
			apiParams.setApiAnnotateUser(apiAnnotateUser);
		return apiParams;
	}

	public String getCollection() {
		// TODO Auto-generated method stub
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getApiUser() {
		return apiUser;
	}

	public void setApiUser(String apiUser) {
		this.apiUser = apiUser;
	}

	public String getApiAnnotateUser() {
		return apiAnnotateUser;
	}

	public void setApiAnnotateUser(String apiAnnotateUser) {
		this.apiAnnotateUser = apiAnnotateUser;
	}

}
