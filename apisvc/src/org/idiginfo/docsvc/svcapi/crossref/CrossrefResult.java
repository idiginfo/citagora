package org.idiginfo.docsvc.svcapi.crossref;

import java.util.List;

import com.google.gson.JsonElement;

/**
 * Class to map Spring result json document
 * 
 * Note that although this class has a set of SpringerRecord (implements
 * Documents) it must create a more generic List<Document> to use for the
 * Documents interface. See method SpringerDocuments(records)
 * 
 * @author griccardi
 * 
 */
public class CrossrefResult {
	// {
	// "totalResults": 1752,
	// "startIndex": 0,
	// "itemsPerPage": 20,
	// "query": {
	// "searchTerms": "joiner",
	// "startPage": 1
	// },
	// result from search
	Integer totalResults;
	Integer startIndex;
	Integer itemsPerPage;
	Query query;

	// result from Match

	class Query {
		String searchTerms;
		Integer startPage;
	}

	List<CrossrefDocument> items;

	public static int getResultCode(JsonElement json) {
		// TODO Auto-generated method stub
		return 0;
	}

	public static String getMessage(JsonElement json) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer getTotalResults() {
		return totalResults;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public Query getQuery() {
		return query;
	}

	public List<CrossrefDocument> getItems() {
		return items;
	}
}
