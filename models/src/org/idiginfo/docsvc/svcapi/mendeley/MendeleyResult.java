package org.idiginfo.docsvc.svcapi.mendeley;

import java.util.List;

//import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeader;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeaders;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map Mendeley result json document
 * 
 * Note that although this class has a set of MendeleyRecord (implements
 * Documents) it must create a more generic List<Document> to use for the
 * Documents interface. See method MendeleyDocuments(records)
 * 
 * @author sflager
 * 
 */
public class MendeleyResult {

    @SerializedName("documents")
    List<MendeleyHeader> records;
    @SerializedName("total_results")
	String total_results;
    @SerializedName("total_pages")
    String total_pages;
    @SerializedName("current_page")
	String current_page;
    @SerializedName("items_per_page")
	String items_per_page;

    transient MendeleyHeaders headers;

    public List<MendeleyHeader> getHeaders() {
	if (records != null)
	    return records;
	return null;
    }

    public int getTotalResults() {
	if (total_results == null || total_results.length() == 0)
		return 0;
	try{
	    return Integer.parseInt(total_results);
	} catch (NumberFormatException e) {
	    return 0;
	}
    }

    public int getTotalPages() {
	if (total_pages != null && total_pages.length() > 0)
		return 0;
	try{
	    return Integer.parseInt(total_pages);
	} catch (NumberFormatException e) {
	    return 0;
	}
    }

    public int getCurrentPages() {
    	if (current_page != null && current_page.length() > 0)
	    return 0;
	try {
	    return Integer.parseInt(current_page);
	} catch (NumberFormatException e) {
	    return 0;
	}
    }

    public int getItemsPerPage() {
    	if (items_per_page != null && items_per_page.length() > 0)
	    return 0;
	try {
	    return Integer.parseInt(items_per_page);
	} catch (NumberFormatException e) {
	    return 0;
	}
    }

}
