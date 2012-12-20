package org.idiginfo.docsvc.svcapi.msrc;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

/**
 * Class to map results of Altmetric API calls
 * 
 * @author griccardi
 * 
 */
public class MsrcResult {

    @SerializedName("request_time")
    Long requestTime;
    String uri;
    @SerializedName("query_string")
    String queryString;
    MsrcRecord record;
    @SerializedName("document_ids")
    List<Integer> documentIds;

    public Long getRequestTime() {
	return requestTime;
    }

    public String getUri() {
	return uri;
    }

    public String getQueryString() {
	return queryString;
    }

    public static int getResultCode(JsonElement json) {
	// TODO Auto-generated method stub
	return 0;
    }

    public int getTotalResults() {
	if (documentIds == null)
	    return 0;
	return documentIds.size();
    }

    public int getDocumentId(int i) {
	if (documentIds == null || documentIds.size() <= i)
	    return 0;
	return documentIds.get(i);
    }

    public MsrcRecord getRecord() {
        return record;
    }
}
