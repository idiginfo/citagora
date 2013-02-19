package org.idiginfo.docsvc.model.crossref;

import java.util.List;

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
    Result[] results;

    public class Result {
	String text;
	Boolean match;
	String doi;
	Double score;
	String reason;

	public String getText() {
	    return text;
	}

	public Boolean getMatch() {
	    return match;
	}

	public String getDoi() {
	    return doi;
	}

	public Double getScore() {
	    return score;
	}

	public String getReason() {
	    return reason;
	}
    }

    public Boolean getQueryOk() {
	return queryOk;
    }

    public Result[] getResults() {
	return results;
    }

}
