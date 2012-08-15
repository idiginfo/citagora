package org.idiginfo.docsvc.model.scopus;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class ScopusUrl extends GenericUrl {

	public ScopusUrl(String function) {
		super(ScopusApiParams.API_URL + function);
	}

	public ScopusUrl(String function, String cluster) {
		super(ScopusApiParams.API_URL + function + cluster);
	}

	// Clusters: HUB SCIDIR SCIDIR-OBJECT SCOPUS AUTHOR AFFILIATION

	@Key
	String apiKey = ScopusApiParams.API_KEY;
	@Key
	String preventCache;
	@Key
	String search;
	@Key
	String sort;
	@Key
	String sortDirection;
	@Key
	String sciverseField;
	@Key
	String numResults;
	@Key
	String content;
	@Key
	String offset;
	@Key
	String callback;


	// ={searchTerms}&[{param}=[paramValue]&{param]=[paramValue]...]

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {

	}

	public static boolean isError(String content) {
		if (content.startsWith("XXX")) {
			return true;
		}
		return false;
	}

	protected void mapParams(ScopusApiParams params) {
		if (params == null)
			return;
		// TODO finish method
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getPreventCache() {
		return preventCache;
	}

	public void setPreventCache(String preventCache) {
		this.preventCache = preventCache;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSciverseField() {
		return sciverseField;
	}

	public void setSciverseField(String sciverseField) {
		this.sciverseField = sciverseField;
	}

	public String getNumResults() {
		return numResults;
	}

	public void setNumResults(String numResults) {
		this.numResults = numResults;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

}
