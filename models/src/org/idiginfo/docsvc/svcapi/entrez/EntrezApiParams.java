package org.idiginfo.docsvc.svcapi.entrez;

import java.util.ArrayList;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class EntrezApiParams extends BaseApiParams {
    public static final String API_URL = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/";

    public static final String SOURCE = "crossref";

    // ?q=joiner&header=true

    List<String> ids = new ArrayList<>();

    public EntrezApiParams(ApiParams apiParams) {
	if (apiParams == null)
	    return;
	setSearchTerms(apiParams.getSearchTerms());
    }

    public void addId(String id){
	ids.add(id);
    }

    public EntrezApiParams() {
    }

    public void setIds(List<String> ids) {
	this.ids = ids;
    }

}
