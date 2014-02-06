package org.idiginfo.docsvc.svcapi.crossref;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.MatchResult;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

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
public class CrossrefMatch {
	// {
	// "query_ok": true,
	// "results": [
	// {
	// "text": "...",
	// "match": true,
	// "doi": "10.1111/j.1365-2966.2010.18055.x",
	// "score": 5.6733
	// },
	// {
	// "text": "...",
	// "match": false,
	// "reason": "Too few terms"
	// }
	// ]
	// }

	// result from Match
	@SerializedName("query_ok")
	Boolean queryOk;
	List<CrossRefMatchResult> results;

	public class CrossRefMatchResult implements MatchResult {
		String text;
		Boolean match;
		String doi;
		Double score;
		String reason;

		@Override
		public String getText() {
			return text;
		}

		@Override
		public Boolean getMatch() {
			return match;
		}

		@Override
		public String getDoi() {
			return doi;
		}

		@Override
		public Double getScore() {
			return score;
		}

		@Override
		public String getReason() {
			return reason;
		}
	}

	public Boolean getQueryOk() {
		return queryOk;
	}

	public List<? extends MatchResult> getResults() {
		return results;
	}

}
