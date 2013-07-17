package org.idiginfo.docsvc.svcapi.nature;

import java.util.Date;
import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Class to process the API responce from Nature
 * 
 * @author griccardi
 * 
 */

public class NatureFeed {

	String title; // "nature.com OpenSearch: darwin lamarck",
	String subtitle; // "The nature.com OpenSearch service provides a structured resource discovery facility for content hosted on nature.com.",
	String id; // "urn:uuid:b8c9c71b-2faa-47b5-9606-507b6d112335",
	NatureAuthor author; //

	Date updated; // "2013-02-07T21:51:07+00:00",
	// String rights; // "© 2013 Nature Publication Group",
	String icon; // "http://www.nature.com/opensearch/common/imgs/npg_icon.jpg",
	NatureLink[] link;

	@SerializedName("dc:publisher")
	String publisher;
	@SerializedName("dc:language")
	String language;
	@SerializedName("dc:rights")
	String rights;
	@SerializedName("prism:copyright")
	String copyright;
	@SerializedName(" prism:rightsAgent")
	String rightsAgent;
	@SerializedName("opensearch:totalResults")
	String totalResults;
	@SerializedName("opensearch:startIndex")
	String startIndex;
	@SerializedName("opensearch:itemsPerPage")
	String itemsPerPage;
	@SerializedName("opensearch:Query")
	NatureQuery[] query;

	@SerializedName("sru:numberOfRecords")
	String numberOfRecords;
	@SerializedName("sru:resultSetId")
	String resultSetId; // "4026f89c-f8fa-4ce6-9426-460fd8d2a973",
	@SerializedName("sru:resultSetTTL")
	String resultSetTTL;
	String extRecordPosition;
	@SerializedName("sru:echoedSearchRetrieveRequest")
	SearchRetrieveRequest echoedSearchRetrieveRequest;
	@SerializedName("sru:extraResponseData")
	ExtraResponseData extraResponseData;
	List<NatureEntry> entry;

	public class NatureAuthor {
		String name;
		String uri;
		String email;
	}

	public class NatureQuery {
		@SerializedName("opensearch:role")
		String role;
		@SerializedName("opensearch:searchTerms")
		String searchTerms;
	}

	public class NatureLink {
		String rel;
		String href;
		String type;
	}

	public class SearchRetrieveRequest {
		@SerializedName("sru:query")
		String query;
		@SerializedName("sru:startRecord")
		Integer startRecord;
		@SerializedName("sru:maximumRecords")
		Integer maximumRecords;
		@SerializedName("sru:recordPacking")
		String recordPacking;
		@SerializedName("sru:recordSchema")
		String recordSchema;
		@SerializedName("sru:sortKeys")
		String sortKeys;
	}

	public class ExtraResponseData {
		@SerializedName("npg:collection")
		String collection; //
		@SerializedName("npg:context")
		String context; // "false",
		@SerializedName("npg:copyright")
		String copyright; // "© 2013 Nature Publication Group",
		@SerializedName("npg:entry")
		String entry; // "",
		@SerializedName("npg:databaseTitle")
		String databaseTitle; // "nature.com OpenSearch",
		@SerializedName("npg:datetime")
		String datetime; // "2013-02-07T21:51:07+00:00",
		@SerializedName("npg:httpAccept")
		String httpAccept; // "application/json",
		@SerializedName("npg:responseId")
		String responseId; // "b8c9c71b-2faa-47b5-9606-507b6d112335"
	}
}