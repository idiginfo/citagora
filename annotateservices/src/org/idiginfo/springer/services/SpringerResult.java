package org.idiginfo.springer.services;

import java.util.List;

import org.idiginfo.annotationmodel.Documents;

import com.google.gson.annotations.SerializedName;

public class SpringerResult {

	String query;
	String apiKey;
	@SerializedName("result")
	Result[] results;
	List<SpringerRecord> records;
	List<Facet> facets;
	Documents documents;

	Documents getDocuments() {
		if (documents != null)
			return documents;
		return new SpringerDocuments(records);
	}

	class Result {
		String total;
		String start;
		String pageLength;
	}

	class Value {
		String value;
		String count;
	}

	class Facet {
		String name;
		List<Value> values;
	}

	public Result getResult() {
		if (results != null && results.length > 0)
			return results[0];
		return null;
	}

	public Facet getFacet(int i) {
		if (facets != null && facets.size() > i)
			return facets.get(i);
		return null;
	}
}
