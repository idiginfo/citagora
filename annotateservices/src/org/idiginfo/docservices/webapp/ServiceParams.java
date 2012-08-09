package org.idiginfo.docservices.webapp;

import javax.servlet.http.HttpServletRequest;

import org.idiginfo.docservices.AnnotationFactory;
import org.idiginfo.docservices.model.ApiParams;

public class ServiceParams {
	// request params
	public static String METHOD_PARAM = "method";
	public static String FORMAT_PARAM = "format";
	// params for annotation service
	public static String CODE_PARAM = "code";
	public static String DATA_PARAM = "date";
	public static String API_USER_PARAM = "apiUser";
	public static String KEYWORD_PARAM;

	public static String API_ANNOTATE_USER_PARAM = "apiAnnotateUser";

	protected String method;
	protected String collection; // the source collection
	protected String format;
	protected String code;
	protected String date;
	protected String apiUser;
	protected String owner;
	protected String keyword;

	public static String METHOD_GET_USERS = "getusers";
	public static String METHOD_GET_DOCUMENT = "getdocument";
	public static String METHOD_GET_DOCUMENTS = "getdocuments";
	public static String METHOD_GET_ANNOTATIONS = "getannotations";

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
		owner = request.getParameter(API_ANNOTATE_USER_PARAM);
	}

	/**
	 * Create an ApiServiceParams object to be used in calls to the annotations
	 * service
	 * 
	 * @return
	 */
	public ApiParams getApiServiceParams() {
		ApiParams apiParams = AnnotationFactory.createApiParams(collection);
		if (code != null)
			apiParams.setCode(code);
		if (date != null)
			apiParams.setDate(date);
		if (apiUser != null)
			apiParams.setApiUser(apiUser);
		if (owner != null)
			apiParams.setOwner(owner);
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
