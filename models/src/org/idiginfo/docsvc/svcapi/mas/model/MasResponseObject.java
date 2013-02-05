package org.idiginfo.docsvc.svcapi.mas.model;

import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

public class MasResponseObject {
    @SerializedName("__type")
    String responseType;
    Integer resultCode;
    String version;

    MasResultObject<MasPublication> publication;
    MasResultObject<MasAuthor> author;
    MasResultObject<MasConference> conference;
    MasResultObject<MasJournal> journal;
    MasResultObject<MasOrganization> organization;
    MasResultObject<MasDomain> domain;
    MasResultObject<MasKeyword> keyword;

    public int getResultCode() {
	if (resultCode == null)
	    return MasApiParams.UNKNOWN_ERROR_CODE; // problem with response
	return resultCode;
    }

    public String getResponseType() {
	return responseType;
    }

    public String getVersion() {
	return version;
    }

    public MasResultObject<MasPublication> getPublication() {
	return publication;
    }

    public MasResultObject<MasAuthor> getAuthor() {
	return author;
    }

    public MasResultObject<MasConference> getConference() {
	return conference;
    }

    public MasResultObject<MasJournal> getJournal() {
	return journal;
    }

    public MasResultObject<MasOrganization> getOrganization() {
	return organization;
    }

    public MasResultObject<MasDomain> getDomain() {
	return domain;
    }

    public MasResultObject<MasKeyword> getKeyword() {
	return keyword;
    }

}
