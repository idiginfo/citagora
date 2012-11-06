package org.idiginfo.docsvc.svcapi.msrc;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

public class MsrcApiParams extends BaseApiParams {

	public static final String API_VERSION = "v1";
	public static final String API_URL = "http://api.altmetric.com/";
	// + API_VERSION + "/";;
	public static final String API_KEY = "6b3b89d864f4d7bfc42d5254a00ad5c7";
	public static final String API_DOCS = "http://api.altmetric.com/";
	public static final String SOURCE = "altmetric.com";
	public static final String API_HOST = "api.altmetric.com";
	public static final String DOI_COLLECTION = "doi";
	public static final String CITATION_COLLECTION = "citation";
	public static final String PMID_COLLECTION = "pmid";
	public static final String DETAILS_COLLECTION = "details";
	public static final String ARXID_COLLECTION = "arxid";

	// citations parameters
	// http://api.altmetric.com/{version}/citations/{timeframe}
	String timeframe;
	String[] timeframeValues = { "at", "1d", "2d", "3d", "4d", "5d", "6d",
			"1w", "1m", "3m", "1y" };
	String page;
	Integer numResults;// num_results
	String citedIn; // cited_in
	String[] citedInValues = {
			// cited_in One or more comma delimited options from:
			"facebook", "blogs", "twitter", "reddit", "news", "f1000", "rh",
			"qna", "forum" };
	String doiPrefix; // doi_prefix
	// A DOI prefix (the bit before the first slash, e.g. 10.1038)
	// Include only articles with a DOI that contains the given prefix.
	String nlmid; // Comma delimited list of journal NLM IDs
	String subject; // Comma delimited list of slugified journal subjects
	
	String altmetricId;
	String pmcid;
	String arxid;

	// DOI collection uses path params for doi

	// Include only articles from journals matching any of the supplied NLM
	// subject ontology term(s).

	// Get by altmetric id
	// path: /{version}/id/{altmetric id}
	// returns document as in 'citations'

	// Get by doi
	// path /{version}/doi/{doi}

	// Get by PubMed id
	// path /{version}/pmid/{pmid}

	// Get by arxid
	// path /{version}/arxid/{arxid}

	// get by altmetric id with details
	// path /{version}/details/{altmetric id}

	public MsrcApiParams(ApiParams apiParams) {
		if (apiParams == null)
			return;
		this.apiUser = apiParams.getApiUser();
		setId(apiParams.getId());
		setDate(apiParams.getDate());
		setSearchTerms(apiParams.getSearchTerms());
	}

	public MsrcApiParams() {
	}

	public void setAltmetricId(String altmetricId) {
		this.altmetricId = altmetricId;		
	}
	
	public String getAltMetricId(){
		return altmetricId;
	}

	public String getTimeframe() {
		return timeframe;
	}

	public void setTimeframe(String timeframe) {
		this.timeframe = timeframe;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getNumResults() {
		return numResults;
	}

	public void setNumResults(Integer numResults) {
		this.numResults = numResults;
	}

	public String getCitedIn() {
		return citedIn;
	}

	public void setCitedIn(String citedIn) {
		this.citedIn = citedIn;
	}

	public String[] getCitedInValues() {
		return citedInValues;
	}

	public void setCitedInValues(String[] citedInValues) {
		this.citedInValues = citedInValues;
	}

	public String getDoiPrefix() {
		return doiPrefix;
	}

	public void setDoiPrefix(String doiPrefix) {
		this.doiPrefix = doiPrefix;
	}

	public String getNlmid() {
		return nlmid;
	}

	public void setNlmid(String nlmid) {
		this.nlmid = nlmid;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPmcid() {
		return pmcid;
	}

	public void setPmcid(String pmcid) {
		this.pmcid = pmcid;
	}

	public String getArxid() {
		return arxid;
	}

	public void setArxid(String arxid) {
		this.arxid = arxid;
	}

	public String getAltmetricId() {
		return altmetricId;
	}
}
