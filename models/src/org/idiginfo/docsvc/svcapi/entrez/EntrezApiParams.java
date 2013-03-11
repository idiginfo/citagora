package org.idiginfo.docsvc.svcapi.entrez;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class EntrezApiParams extends BaseApiParams {
    public static final String API_URL = "http://eutils.ncbi.nlm.nih.gov/entrez/eutils/";

    public static final String SOURCE = "crossref";

    // ?q=joiner&header=true

    public EntrezApiParams(ApiParams apiParams) {
	if (apiParams == null)
	    return;
	setSearchTerms(apiParams.getSearchTerms());
    }

    public EntrezApiParams() {
    }

}
