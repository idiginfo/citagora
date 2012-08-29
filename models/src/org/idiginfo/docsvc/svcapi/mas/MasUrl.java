package org.idiginfo.docsvc.svcapi.mas;

import java.util.Arrays;
import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MasUrl extends GenericUrl {
	// http://academic.research.microsoft.com/json.svc/search
	// ?AppId=Your_AppID&FullTextQuery=data+mining&ResultObjects=publication&PublicationContent=title
	// http://academic.research.microsoft.com/json.svc/search
	// ?AppId=Your_AppID&FullTextQuery=data+mining&ResultObjects=publication
	// &PublicationContent=title,author&StartIdx=1&EndIdx=1
	String version;
	@Key("AppId")
	String apiKey = MasApiParams.API_KEY;

	// id fields
	@Key("PublicationID")
	Integer publicationId;
	@Key("AuthorID")
	Integer authorID;
	@Key("ConferenceID")
	Integer conferenceID;
	@Key("JournalID")
	Integer journalID;
	@Key("OrganizationID")
	Integer organizationID;
	@Key("TopDomainID")
	Integer topDomainID;
	@Key("KeywordID")
	Integer keywordID;

	// query fields
	@Key("TitleQuery")
	String titleQuery;
	@Key("AuthorQuery")
	String authorQuery;
	@Key("ConferenceQuery")
	String conferenceQuery;
	@Key("JournalQuery")
	String journalQuery;
	@Key("FulltextQuery")
	String fulltextQuery;

	// result parameter fields
	@Key("ResultObjects")
	String resultObjects = "publication";
	@Key("ReferenceType")
	String referenceType;
	@Key("PublicationContent")
	String publicationContent;
	@Key("OrderBy")
	String orderBy;
	@Key("YearStart")
	Integer yearStart;
	@Key("YearEnd")
	Integer yearEnd;
	@Key("StartIdx")
	Integer startIdx = 1;
	@Key("EndIdx")
	Integer endIdx = 10;

	public MasUrl() {
		super();
		apiKey = MasApiParams.API_KEY;
		setHost(MasApiParams.API_HOST);
		setScheme("http");
		List<String> pathParts = Arrays.asList("", MasApiParams.API_SERVICE,
				MasApiParams.API_SEARCH);
		setPathParts(pathParts);
	}

	public MasUrl(MasApiParams params) {
		this();
		if (params.getId() != null) {
			publicationId = 15038153;// params.getId();
		}
		// TODO Auto-generated constructor stub
	}

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {

	}

	public static boolean isError(String content) {
		if (content.startsWith("<html")) {
			return true;
		}
		return false;
	}

	protected void mapParams(MasApiParams params) {
		if (params == null)
			return;
		// TODO finish method
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
