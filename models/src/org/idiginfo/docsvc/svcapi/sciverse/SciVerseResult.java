package org.idiginfo.docsvc.svcapi.sciverse;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Documents;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map Spring result json document
 * 
 * Note that although this class has a set of SciVerseRecord (implements
 * Documents) it must create a more generic List<Document> to use for the
 * Documents interface. See method SciVerseDocuments(records)
 * 
 * @author griccardi
 * 
 */
public class SciVerseResult {

    @SerializedName("search-results")
    SearchResults searchResults;

    public class SearchResults {
	@SerializedName("opensearch:totalResults")
	Integer totalResults;
	@SerializedName("opensearch:startIndex")
	Integer startIndex;

	@SerializedName("opensearch:itemsPerPage")
	Integer itemsPerPage;
	@SerializedName("opensearch:Query")
	Query query;
	List<SciVerseLink> link;
	@SerializedName("entry")
	List<SciVerseDocument> records;

    }

    public class Query {
	@SerializedName("@role")
	String role;
	@SerializedName("@searchTerms")
	String searchTerms;
	@SerializedName("@startPage")
	Integer startPage;
    }

    public class Entry {
	@SerializedName("link")
	List<SciVerseLink> links;

	public List<SciVerseLink> getLinks() {
	    return links;
	}
    }

    transient SciVerseDocuments documents;

    // @Override
    SciVerseDocuments getDocuments() {
	if (documents == null)
	    documents = new SciVerseDocuments(searchResults.records);
	return documents;
    }

     public SciVerseDocument getResult() {
	if (searchResults.records != null && searchResults.records.size() > 0)
	    return searchResults.records.get(0);
	return null;
    }

    public Integer getTotalResults() {
	return searchResults.totalResults;
    }

    public void setTotalResults(Integer totalResults) {
	this.searchResults.totalResults = totalResults;
    }

    public Integer getStartIndex() {
	return searchResults.startIndex;
    }

    public void setStartIndex(Integer startIndex) {
	this.searchResults.startIndex = startIndex;
    }

    public Integer getItemsPerPage() {
	return searchResults.itemsPerPage;
    }

    public void setItemsPerPage(Integer itemsPerPage) {
	this.searchResults.itemsPerPage = itemsPerPage;
    }

    public Query getQuery() {
	return searchResults.query;
    }

    public void setQuery(Query query) {
	this.searchResults.query = query;
    }

    public List<SciVerseLink> getLink() {
	return searchResults.link;
    }

    public void setLink(List<SciVerseLink> link) {
	this.searchResults.link = link;
    }

    public List<SciVerseDocument> getRecords() {
	return searchResults.records;
    }

    public String getRole() {
	return searchResults.query.role;
    }

    public void setRole(String role) {
	this.searchResults.query.role = role;
    }

    public String getSearchTerms() {
	return searchResults.query.searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
	this.searchResults.query.searchTerms = searchTerms;
    }

    public Integer getStartPage() {
	return searchResults.query.startPage;
    }

    public void setStartPage(Integer startPage) {
	this.searchResults.query.startPage = startPage;
    }

    
}
