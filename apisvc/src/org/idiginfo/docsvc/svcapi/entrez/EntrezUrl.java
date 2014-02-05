package org.idiginfo.docsvc.svcapi.entrez;

import org.apache.commons.lang.StringUtils;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

/**
 * Class to implement GenericUrl object for Entrez (NCBI)
 * 
 * @author griccardi
 * 
 */

public class EntrezUrl extends GenericUrl {

	public static final String FETCH = "efetch.fcgi";
	public static final String FETCH_URL = EntrezApiParams.API_URL + FETCH;
	public static final String SEARCH = "esearch.fcgi";
	public static final String SEARCH_URL = EntrezApiParams.API_URL + SEARCH;

	@Key
	String db = "pubmed";
	@Key
	String id;
	@Key
	String retstart;
	@Key
	String retmax;

	// eFetch optional parameters
	// Record Type/ &rettype/ &retmode
	// text ASN.1/ null/ asn.1, default
	// XML/ null/ xml
	// MEDLINE/ medline/ text
	// PMID list uilist text
	// Abstract abstract text

	@Key
	String retmode = "xml";
	@Key
	String rettype; // "xml"

	// eSearch parameters
	@Key
	String term;
	// TODO add these fields to the constructor
	@Key
	String usehistory = "y";
	@Key("WebEnv")
	String webenv;
	@Key("query_key")
	String queryKey;

	// eSearch optional parameters
	@Key
	String field;

	/**
	 * Type of date used to limit a search. The allowed values vary between
	 * Entrez databases, but common values are 'mdat' (modification date),
	 * 'pdat' (publication date) and 'edat' (Entrez date). Generally an Entrez
	 * database will have only two allowed values for datetype.
	 */
	@Key
	String datetype;

	/**
	 * When reldate is set to an integer n, the search returns only those items
	 * that have a date specified by datetype within the last n days.
	 */
	@Key
	Integer reldate;

	/**
	 * Date range used to limit a search result by the date specified by
	 * datetype. These two parameters (mindate, maxdate) must be used together
	 * to specify an arbitrary date range. The general date format is
	 * YYYY/MM/DD, and these variants are also allowed: YYYY, YYYY/MM.
	 */
	@Key
	String mindate;
	@Key
	String maxdate;

	public EntrezUrl(String function, EntrezApiParams params) {
		super(EntrezApiParams.API_URL + function);
		// extract api params
		if (function.equals(FETCH)) {
			// use ids from params
			id = StringUtils.join(params.ids, ",");
		} else if (function.equals(SEARCH)) {
			term = params.getSearchTerms();
			if (params.getFirstResult() != null) {
				retstart = Integer.toString(params.getFirstResult());
			}
			if (params.getNumResults() != null) {
				retmax = Integer.toString(params.getNumResults());
			}
			if (params.getFirstResult() != null) {
				retstart = Integer.toString(params.getFirstResult());
			}
		}
	}

	public void prepare() {
		// TODO Auto-generated method stub

	}

}
