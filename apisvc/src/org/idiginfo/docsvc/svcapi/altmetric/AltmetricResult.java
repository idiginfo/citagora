package org.idiginfo.docsvc.svcapi.altmetric;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map results of Altmetric API calls
 * 
 * @author griccardi
 * 
 */
public class AltmetricResult {

	Query query;
	@SerializedName("results")
	List<AltmetricRecord> results;

	List<? extends Document> getDocuments() {
		return results;
	}

	public class Query {
		public Integer total;
		public Integer page;
		@SerializedName("num_results")
		public Integer numResults;
		public Integer ms;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}
