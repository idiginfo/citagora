package org.idiginfo.docsvc.svcapi.mas;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.BaseApiParams;

public class MasApiParams extends BaseApiParams {

	// public static final String API_URL =
	// "http://academic.research.microsoft.com";
	// + API_VERSION + "/";;
	public static final String API_KEY = "349bdedd-f3c6-4939-97a2-032141aeb565";
	public static final String SOURCE = "academic.research.microsoft.com";
	public static final String API_HOST = "academic.research.microsoft.com";
	public static final String API_SERVICE = "json.svc";
	public static final String API_SEARCH = "search";
	public static final String API_VERSION = "1.2";

	public static final String[] REFERENCE_VALUES = { "None", "Reference",
			"Citation" };
	public static final String[] PUBLICATION_CONTENT_TYPE_VALUES = {
			"MetaOnly", "Title", "Author", "Abstract",
			"ConferenceAndJournalInfo", "FullVersionURL", "AllInfo", "Keyword" };
	public static final String[] ORDER_TYPE_VALUES = { "Rank", "Year",
			"CitationCount", "HIndex", "GIndex" };

	public static final String[] RESPONSE_CODE_MESSAGES = {
			"The request succeeded",// 0
			"The AppID has no access right to the MAS API",// 1
			"Some parameters are invalid. For example, none of the search condition is specified",// 2
			"The MAS service is temporarily unavailable. Please try it later.",// 3
			"The search condition is not supported yet.",// 4
			"Unknown problem with service" // 5
	};
	public static final int UNKNOWN_ERROR_CODE = 5;

	public MasApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		this.apiUser = apiParams.getApiUser();
		setId(apiParams.getId());
		setDate(apiParams.getDate());
		setSearchTerms(apiParams.getSearchTerms());
	}

	public MasApiParams() {
	}

}
