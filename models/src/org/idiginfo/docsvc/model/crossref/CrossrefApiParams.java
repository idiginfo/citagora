package org.idiginfo.docsvc.model.crossref;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class CrossrefApiParams extends BaseApiParams {

    public static final String API_URL = "http://search.labs.crossref.org/";
    public static final String SOURCE = "crossref";

    // ?q=joiner&header=true

    public CrossrefApiParams(ApiParams apiParams) {
	if (apiParams == null)
	    return;
	setSearchTerms(apiParams.getSearchTerms());
    }

    public CrossrefApiParams() {
    }
    
}
