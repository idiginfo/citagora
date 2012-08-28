package org.idiginfo.docsvc.model.mas;

import org.idiginfo.docsvc.model.model.BaseApiParams;

public class MasApiParams extends BaseApiParams {
	public static final String API_VERSION = "v1.2";
	public static final String API_URL = "http://academic.research.microsoft.com/";
	public static final String API_KEY = "?";
	public static final String API_DOCS = "http://academic.research.microsoft.com/";
	public static final String SOURCE = "academic.research.microsoft.com";
	public static final String API_HOST = "academic.research.microsoft.com";
	public static final String API_SVC = "json.svc";
	
	String objecttype;
	String[] objecttypeValues = { "Publication", "Author", "Conference", "Journal", "Organization",
			"Domain", "Keyword" };

	String referencerelationship;
	String[] referencerelationshipValues = { "None", "Reference", "Citation" };

	String publicationcontenttype;
	String[] publicationcontenttypeValues = { "MetaOnly", "Title", "Author", "Abstract", "ConferenceAndJournalInfo", 
			"FullVersionURL", "AllInfo", "Keyword" };

	String ordertype;
	String[] ordertypeValues = { "Rank", "Year", "CitationCount", "Citation", "HIndex", "GIndex" };


			
	}