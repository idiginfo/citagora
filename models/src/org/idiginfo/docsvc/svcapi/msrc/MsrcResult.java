package org.idiginfo.docsvc.svcapi.msrc;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Documents;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map results of Altmetric API calls
 * @author griccardi
 *
 */
public class MsrcResult {

	Query query;
	@SerializedName("results")
	List<MsrcRecord> results;
	
	transient Documents documents;

	Documents getDocuments() {
		if (documents != null)
			return documents;
		return  new MsrcDocuments(results);
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

	public void setDocuments(MsrcDocuments documents) {
		this.documents = documents;
	}
}
