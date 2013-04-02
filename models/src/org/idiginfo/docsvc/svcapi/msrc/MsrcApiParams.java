package org.idiginfo.docsvc.svcapi.msrc;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class MsrcApiParams extends BaseApiParams {

    //public static final String API_SERVICE_NAME = "msrc-export";
    public static final String API_HOST = "msrc.idiginfo.org";
    public static final String BIBLIO_COLLECTION = "documents";
	public static final String SOURCE = "msrc.idiginfo.org";

    public MsrcApiParams(ApiParams apiParams) {
	if (apiParams == null)
	    return;
	setId(apiParams.getId());
	setDate(apiParams.getDate());
	setSearchTerms(apiParams.getSearchTerms());
    }

    public MsrcApiParams() {
    }

    public Integer getNumResults() {
	return numResults;
    }

    public void setNumResults(Integer numResults) {
	this.numResults = numResults;
    }

    public String getSubject() {
	return subject;
    }

    public void setSubject(String subject) {
	this.subject = subject;
    }

    public String getPmcid() {
	// TODO Auto-generated method stub
	return null;
    }

}
