package org.idiginfo.docsvc.svcapi.mas.svc;

import java.util.Arrays;
import java.util.List;

import org.idiginfo.docsvc.model.model.BaseApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class MasUrl extends GenericUrl {
	// http://academic.research.microsoft.com/json.svc/search
	// ?AppId=Your_AppID&FullTextQuery=data+mining&ResultObjects=publication&PublicationContent=title
	// http://academic.research.microsoft.com/json.svc/search
	// ?AppId=Your_AppID&FullTextQuery=data+mining&ResultObjects=publication
	// &PublicationContent=title,author&StartIdx=1&EndIdx=1
	public String version;
	@Key("AppId")
	public String apiKey = MasApiParams.API_KEY;

	// id fields
	@Key("PublicationID")
	public Integer publicationId;
	@Key("AuthorID")
	public Integer authorID;
	@Key("ConferenceID")
	public Integer conferenceID;
	@Key("JournalID")
	public Integer journalID;
	@Key("OrganizationID")
	public Integer organizationID;
	@Key("TopDomainID")
	public Integer topDomainID;
	@Key("KeywordID")
	public Integer keywordID;

	// query fields
	@Key("TitleQuery")
	public String titleQuery;
	@Key("AuthorQuery")
	public String authorQuery;
	@Key("ConferenceQuery")
	public String conferenceQuery;
	@Key("JournalQuery")
	public String journalQuery;
	@Key("FulltextQuery")
	public String fulltextQuery;

	// result parameter fields
	@Key("ResultObjects")
	public String resultObjects = "Publication";
	@Key("ReferenceType")
	public String referenceType;
	@Key("PublicationContent")
	public String publicationContent="Keyword";
	@Key("OrderBy")
	public String orderBy;
	@Key("YearStart")
	public Integer yearStart;
	@Key("YearEnd")
	public Integer yearEnd;
	@Key("StartIdx")
	public Integer startIdx = 1;
	@Key("EndIdx")
	public Integer endIdx = 10;

	public MasUrl() {
		super();
		apiKey = MasApiParams.API_KEY;
		setHost(MasApiParams.API_HOST);
		setScheme("http");
		List<String> pathParts = Arrays.asList("", MasApiParams.API_SERVICE,
				MasApiParams.API_SEARCH);
		setPathParts(pathParts);
	}

	public MasUrl(BaseApiParams params) {
		this();
		params.mapFields(this);
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


	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}
