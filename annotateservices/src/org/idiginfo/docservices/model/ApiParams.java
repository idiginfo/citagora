package org.idiginfo.docservices.model;

public interface ApiParams {
	public String getApiUser();

	public void setApiUser(String apiUser);

	public String getApiRequestTime();

	public void setApiRequestTime(String apiRequestTime);

	public String getDate();

	public void setDate(String date);

	public String getCode();

	public void setCode(String code);

	public String getSearchTerms();

	public void setOwner(String apiAnnotateUser);

	public String getOwner();
}
