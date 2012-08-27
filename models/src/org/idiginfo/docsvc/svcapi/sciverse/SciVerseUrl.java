package org.idiginfo.docsvc.svcapi.sciverse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class SciVerseUrl extends GenericUrl {

	public SciVerseUrl(String function) {
		super(SciVerseApiParams.API_URL + function);
	}

	public SciVerseUrl(String function, String cluster) {
		super(SciVerseApiParams.API_URL + function + cluster);
	}

	// Clusters: HUB SCIDIR SCIDIR-OBJECT SCOPUS AUTHOR AFFILIATION

	@Key
	String query;
	@Key
	String date;
	@Key
	String view;
	@Key
	String field;
	@Key
	String content;
	@Key
	String count;
	@Key
	String start;
	@Key
	String sort;
	@Key
	String subj;
	@Key
	String subscribed;
	@Key
	String facet;
	@Key
	String httpAccept;
	@Key
	String collapse;
	@Key("co-author")
	String coAuthor;

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

	protected void mapParams(SciVerseApiParams params) {
		if (params == null) return;
		// TODO finish method
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSubj() {
		return subj;
	}

	public void setSubj(String subj) {
		this.subj = subj;
	}

	public String getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(String subscribed) {
		this.subscribed = subscribed;
	}

	public String getFacet() {
		return facet;
	}

	public void setFacet(String facet) {
		this.facet = facet;
	}

	public String getHttpAccept() {
		return httpAccept;
	}

	public void setHttpAccept(String httpAccept) {
		this.httpAccept = httpAccept;
	}

	public String getCollapse() {
		return collapse;
	}

	public void setCollapse(String collapse) {
		this.collapse = collapse;
	}

	public String getCoAuthor() {
		return coAuthor;
	}

	public void setCoAuthor(String coAuthor) {
		this.coAuthor = coAuthor;
	}

}
