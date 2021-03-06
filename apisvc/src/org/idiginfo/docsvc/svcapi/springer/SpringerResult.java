package org.idiginfo.docsvc.svcapi.springer;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiSvcUtilities;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map Springer result json document
 * 
 * Note that although this class has a set of SpringerRecord (implements
 * Documents) it must create a more generic List<Document> to use for the
 * Documents interface. See method SpringerDocuments(records)
 * 
 * @author griccardi
 * 
 */
public class SpringerResult {

	String query;
	String apiKey;
	@SerializedName("result")
	Result[] results;
	List<SpringerRecord> records;
	List<Facet> facets;

	transient List<Document> documents;

	List<Document> getDocuments() {
		if (documents == null) {
			documents = ApiSvcUtilities.createDocuments(records);
		}
		return documents;
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

	public int getTotalResults() {
		if (results == null || results.length == 0 || results[0].total == null)
			return 0;
		try {
			return Integer.parseInt(results[0].total);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
