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

	String method;
	String format;
	String code;
	String date;
	String apiUser;
	String apiAnnotateUser;

	public static String METHOD_GET_USERS = "getusers";
	public static String METHOD_GET_DOCUMENT = "getdocument";
	public static String METHOD_GET_DOCUMENTS = "getdocuments";
	public static String METHOD_GET_ANNOTATIONS = "getannotations";

	public ServiceParams() {

	}

	/**
	 * Unpack the request parameters
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
	 * Create an ApiServiceParams object to be used in calls to the annotations service
	 * @return
	 */
	public AnnotateApiParams getApiServiceParams() {
		AnnotateApiParams apiParams = new AnnotateApiParams();
		apiParams.setCode(code);
		apiParams.setDate(date);
		apiParams.setApiUser(apiUser);
		apiParams.setApiAnnotateUser(apiAnnotateUser);
		return apiParams;
	}
	// methods to call (interface AnnotationService
	// public Users getUsers(AnnotateApiParams params);
	// public Document getDocument(String code, String date);
	// public Document getDocument(String code, String date,
	// boolean withMeta, boolean withNotes);
	// public Documents getDocuments(String user);
	// public Documents getDocuments(String user, boolean withMeta,
	// boolean withNotes);
	// public Document getAnnotations(Document document);
	// public Document getAnnotations(String code, String date);

}
