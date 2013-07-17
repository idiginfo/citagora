package org.idiginfo.docsvc.svcapi.sciverse;

import org.idiginfo.docsvc.model.apisvc.ApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;

/**
 * Class to implement GenericUrl object for SciVerse
 * 
 * @author griccardi
 * 
 */

public class SciVerseUrl extends GenericUrl {

	public SciVerseUrl(ApiParams params) {
		this("search", "SCOPUS");
		searchTerms = params.getKeyword();
		count = params.getNumResults();
		start = params.getFirstResult();
		addSearchField("abs", params.getSubject());
		// params.get
	}

	public SciVerseUrl(String function) {
		super(SciVerseApiParams.API_URL + function);
	}

	public SciVerseUrl(String function, String cluster) {
		super(SciVerseApiParams.API_URL + function + "/index:" + cluster);
	}

	public SciVerseUrl(String function, String field, String value) {
		super(SciVerseApiParams.API_URL + function + "/" + field + ":" + value);
	}

	// http://api.elsevier.com/content/
	// search/index:SCOPUS?query=DOI(DOI:10.1016%2Fj.jpsychires.2008.05.001)
	// "http://api.elsevier.com/content/"
	// Clusters: HUB SCIDIR SCIDIR-OBJECT SCOPUS AUTHOR AFFILIATION

	public SciVerseUrl(String function, String cluster, ApiParams params) {
		this(function, cluster);
		addSearchTerm(params.getKeyword());
		count = params.getNumResults();
		start = params.getFirstResult();
		// TODO Auto-generated constructor stub
	}

	@Key
	String query;
	@Key
	String date;
	@Key
	public String view;
	@Key
	String field;
	@Key
	String content;
	@Key
	Integer count;
	@Key
	Integer start;
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
	@Key("scopus_id")
	String scopusId;

	String searchTerms;
	String searchFields;

	// ={searchTerms}&[{param}=[paramValue]&{param]=[paramValue]...]

	public void addSearchTerm(String term) {
		if (searchTerms == null)
			searchTerms = term;
		else
			searchTerms += " " + term;
	}

	public void addSearchField(String name, String value) {
		String term = name + "(" + value + ")";
		if (searchFields == null || searchFields.length() == 0) {
			searchFields = term;
		} else {
			searchFields += " " + term;
		}
	}

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {
		if (searchTerms != null && searchFields == null)
			query = searchTerms;
		else if (searchTerms != null && searchFields != null)
			query = searchTerms + " " + searchFields;
		else
			query = searchFields;
	}

	public static boolean isError(String content) {
		if (content.startsWith("XXX")) {
			return true;
		}
		return false;
	}

	protected void mapParams(SciVerseApiParams params) {
		if (params == null)
			return;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(int i) {
		this.count = i;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(int start) {
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

	public static boolean isError(JsonElement json) {
		// TODO Auto-generated method stub
		return false;
	}

	public void setScopusId(String string) {
		scopusId = string;

	}

}
