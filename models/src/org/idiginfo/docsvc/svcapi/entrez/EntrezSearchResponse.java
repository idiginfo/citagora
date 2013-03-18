package org.idiginfo.docsvc.svcapi.entrez;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.idiginfo.esearch.Count;
import org.idiginfo.esearch.ERROR;
import org.idiginfo.esearch.ESearchResult;
import org.idiginfo.esearch.Id;
import org.idiginfo.esearch.IdList;
import org.idiginfo.esearch.QueryKey;
import org.idiginfo.esearch.QueryTranslation;
import org.idiginfo.esearch.RetMax;
import org.idiginfo.esearch.RetStart;
import org.idiginfo.esearch.TranslationSet;
import org.idiginfo.esearch.TranslationStack;
import org.idiginfo.esearch.WebEnv;
import org.idiginfo.medline.MedlineCitation;
import org.idiginfo.medline.MedlineCitationSet;

public class EntrezSearchResponse {

    // Lists of fields of ESearchResult (simplified from full description)
    // (name = "Count", type = Count.class),
    // (name = "RetMax", type = RetMax.class),
    // (name = "RetStart", type = RetStart.class),
    // (name = "QueryKey", type = QueryKey.class),
    // (name = "WebEnv", type = WebEnv.class),
    // (name = "IdList", type = IdList.class),
    // (name = "TranslationSet", type = TranslationSet.class),
    // (name = "TranslationStack", type = TranslationStack.class),
    // (name = "QueryTranslation", type = QueryTranslation.class),
    // (name = "ERROR", type = ERROR.class)

    int count = 0;
    int retMax = 0;
    int retStart = 0;
    String queryKey = null;
    String webEnv = null;
    List<Id> idList = null;
    TranslationSet translationSet = null;
    TranslationStack translationStack = null;
    String queryTranslation = null;
    String error = null;

    /**
     * Create a response
     * 
     * @param searchResult
     */
    public EntrezSearchResponse(ESearchResult searchResult) {
	List<Object> objects = searchResult.getFieldObjects();
	processObjects(objects);
    }

    /**
     * Extract fields from response and store in local variables
     * 
     * @param objects
     */
    private void processObjects(List<Object> objects) {
	for (Object object : objects) {
	    if (object instanceof Count) {
		count = getInt(((Count) object).getvalue());
	    } else if (object instanceof RetMax) {
		retMax = getInt(((RetMax) object).getvalue());
	    } else if (object instanceof RetStart) {
		retStart = getInt(((RetStart) object).getvalue());
	    } else if (object instanceof QueryKey) {
		queryKey = ((QueryKey) object).getvalue();
	    } else if (object instanceof WebEnv) {
		webEnv = ((WebEnv) object).getvalue();
	    } else if (object instanceof IdList) {
		idList = ((IdList) object).getId();
	    } else if (object instanceof TranslationSet) {
		translationSet = (TranslationSet) object;
	    } else if (object instanceof TranslationStack) {
		translationStack = (TranslationStack) object;
	    } else if (object instanceof QueryTranslation) {
		queryTranslation = ((QueryTranslation) object).getvalue();
	    } else if (object instanceof ERROR) {
		error = ((ERROR) object).getvalue();
	    }
	}
    }

    int getInt(String value) {
	try {
	    return Integer.parseInt(value);
	} catch (NumberFormatException e) {
	    return 0;
	}
    }

    public int getCount() {
	return count;
    }

    public int getRetMax() {
	return retMax;
    }

    public int getRetStart() {
	return retStart;
    }

    public String getQueryKey() {
	return queryKey;
    }

    public String getWebEnv() {
	return webEnv;
    }

    public List<Id> getIdList() {
	return idList;
    }

    public TranslationSet getTranslationSet() {
	return translationSet;
    }

    public TranslationStack getTranslationStack() {
	return translationStack;
    }

    public String getQueryTranslation() {
	return queryTranslation;
    }

    public String getError() {
	return error;
    }
}
