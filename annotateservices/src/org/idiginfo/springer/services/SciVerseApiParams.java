package org.idiginfo.springer.services;

import org.idiginfo.annotationmodel.ApiParams;

public class SciVerseApiParams implements ApiParams {

	public static final String API_URL = "http://api.elsevier.com/content/";

	// Content Categories
	String[] contentCategories = {"Article", "Abstract", "Author", "Affiliation" };

	@Override
	public String getApiUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApiUser(String apiUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getApiRequestTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setApiRequestTime(String apiRequestTime) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDate(String date) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCode(String code) {
		// TODO Auto-generated method stub

	}

}
