package org.idiginfo.docsvc.svcapi.mas;

import com.google.gson.annotations.SerializedName;

public class MasResponseObject {
	@SerializedName("__type")
	String responseType;
	Integer resultCode;
	String version;

	public MasResultObject<MasPublication> publication;
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

}
