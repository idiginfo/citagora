package org.idiginfo.docsvc.model.altmetric;

import java.util.List;

import org.idiginfo.docsvc.model.model.Documents;
import org.idiginfo.docsvc.model.springer.SpringerDocuments;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map results of Altmetric API calls
 * @author griccardi
 *
 */
public class AltmetricResult {

	Query query;
	@SerializedName("results")
	List<AltmetricRecord> results;
	
	transient Documents documents;

	Documents getDocuments() {
		if (documents != null)
			return documents;
		return  new AltmetricDocuments(results);
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

	public void setDocuments(AltmetricDocuments documents) {
		this.documents = documents;
	}
}
