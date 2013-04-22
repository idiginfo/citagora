package org.idiginfo.docsvc.svcapi.mas.svc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.idiginfo.docsvc.model.apisvc.BaseApiParams;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;
import com.google.api.client.util.escape.CharEscapers;

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
    public String publicationId;
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
    public String keyword;

    // result parameter fields
    @Key("ResultObjects")
    public String resultObjects = "Publication";
    @Key("ReferenceType")
    public String referenceType;
    @Key("PublicationContent")
    private String publicationContent = null;
    @Key("OrderBy")
    public String orderBy;
    @Key("YearStart")
    public Integer yearStart;
    @Key("YearEnd")
    public Integer yearEnd;
    @Key("StartIdx")
    public Integer firstResult = 1;
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
	if (params.getId() != null) {
	    keyword = "doi:(" + params.getId() + ")";
	}
	if (params.getTitle() != null) {
	    titleQuery = params.getTitle();
	}
	if (firstResult != null && params.getNumResults() != null) {
	    endIdx = firstResult + params.getNumResults() - 1;
	}
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

    // methods to fix problem with MAS server

//    public final String buildRelativeUrl() {
//	    StringBuilder buf = new StringBuilder();
//	    if (pathParts != null) {
//	      appendRawPathFromParts(buf);
//	    }
//	    addQueryParams(entrySet(), buf);
//
//	    // URL fragment
//	    String fragment = this.fragment;
//	    if (fragment != null) {
//	      buf.append('#').append(URI_FRAGMENT_ESCAPER.escape(fragment));
//	    }
//	    return buf.toString();
//	  }
//
    
    static void addQueryParams(Set<Entry<String, Object>> entrySet,
	    StringBuilder buf) {
	// (similar to UrlEncodedContent)
	boolean first = true;
	for (Map.Entry<String, Object> nameValueEntry : entrySet) {
	    Object value = nameValueEntry.getValue();
	    if (value != null) {
		String name = CharEscapers.escapeUriQuery(nameValueEntry
			.getKey());
		if (value instanceof Collection<?>) {
		    Collection<?> collectionValue = (Collection<?>) value;
		    for (Object repeatedValue : collectionValue) {
			first = appendParam(first, buf, name, repeatedValue);
		    }
		} else {
		    first = appendParam(first, buf, name, value);
		}
	    }
	}
    }

    private static boolean appendParam(boolean first, StringBuilder buf,
	    String name, Object value) {
	if (first) {
	    first = false;
	    buf.append('?');
	} else {
	    buf.append('&');
	}
	buf.append(name);
	String stringValue;
	try {
	    stringValue = URLEncoder.encode(value.toString(), "UTF-8");
	    stringValue = stringValue.replaceAll(":", "&3a");
	    if (stringValue.length() != 0) {
		buf.append('=').append(stringValue);
	    }
	} catch (UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return first;
    }

}
