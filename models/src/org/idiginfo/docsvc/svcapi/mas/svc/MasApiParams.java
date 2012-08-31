package org.idiginfo.docsvc.svcapi.mas.svc;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.BaseApiParams;

import com.google.api.client.util.Key;

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

	// parameters of MAS API
	// id fields
	public Integer publicationId;
	public Integer authorID;
	public Integer conferenceID;
	public Integer journalID;
	public Integer organizationID;
	public Integer topDomainID;
	public Integer keywordID;

	// query fields
	public String titleQuery;
	public String authorQuery;
	public String conferenceQuery;
	public String journalQuery;
	public String fulltextQuery;

	// result parameter fields
	public String resultObjects = "Publication";
	public String referenceType;
	public String publicationContent = "Keyword";
	public String orderBy;
	public Integer yearStart;
	public Integer yearEnd;
	public Integer startIdx = 1;
	public Integer endIdx = 10;

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

	public Integer getPublicationId() {
		return publicationId;
	}

	public void setPublicationId(Integer publicationId) {
		this.publicationId = publicationId;
	}

	public Integer getAuthorID() {
		return authorID;
	}

	public void setAuthorID(Integer authorID) {
		this.authorID = authorID;
	}

	public Integer getConferenceID() {
		return conferenceID;
	}

	public void setConferenceID(Integer conferenceID) {
		this.conferenceID = conferenceID;
	}

	public Integer getJournalID() {
		return journalID;
	}

	public void setJournalID(Integer journalID) {
		this.journalID = journalID;
	}

	public Integer getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(Integer organizationID) {
		this.organizationID = organizationID;
	}

	public Integer getTopDomainID() {
		return topDomainID;
	}

	public void setTopDomainID(Integer topDomainID) {
		this.topDomainID = topDomainID;
	}

	public Integer getKeywordID() {
		return keywordID;
	}

	public void setKeywordID(Integer keywordID) {
		this.keywordID = keywordID;
	}

	public String getTitleQuery() {
		return titleQuery;
	}

	public void setTitleQuery(String titleQuery) {
		this.titleQuery = titleQuery;
	}

	public String getAuthorQuery() {
		return authorQuery;
	}

	public void setAuthorQuery(String authorQuery) {
		this.authorQuery = authorQuery;
	}

	public String getConferenceQuery() {
		return conferenceQuery;
	}

	public void setConferenceQuery(String conferenceQuery) {
		this.conferenceQuery = conferenceQuery;
	}

	public String getJournalQuery() {
		return journalQuery;
	}

	public void setJournalQuery(String journalQuery) {
		this.journalQuery = journalQuery;
	}

	public String getFulltextQuery() {
		return fulltextQuery;
	}

	public void setFulltextQuery(String fulltextQuery) {
		this.fulltextQuery = fulltextQuery;
	}

	public String getResultObjects() {
		return resultObjects;
	}

	public void setResultObjects(String resultObjects) {
		this.resultObjects = resultObjects;
	}

	public String getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(String referenceType) {
		this.referenceType = referenceType;
	}

	public String getPublicationContent() {
		return publicationContent;
	}

	public void setPublicationContent(String publicationContent) {
		this.publicationContent = publicationContent;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getYearStart() {
		return yearStart;
	}

	public void setYearStart(Integer yearStart) {
		this.yearStart = yearStart;
	}

	public Integer getYearEnd() {
		return yearEnd;
	}

	public void setYearEnd(Integer yearEnd) {
		this.yearEnd = yearEnd;
	}

	public Integer getStartIdx() {
		return startIdx;
	}

	public void setStartIdx(Integer startIdx) {
		this.startIdx = startIdx;
	}

	public Integer getEndIdx() {
		return endIdx;
	}

	public void setEndIdx(Integer endIdx) {
		this.endIdx = endIdx;
	}

}
