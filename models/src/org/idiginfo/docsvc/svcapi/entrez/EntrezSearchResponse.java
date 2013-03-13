package org.idiginfo.docsvc.svcapi.entrez;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

import org.idiginfo.esearch.Count;
import org.idiginfo.esearch.ERROR;
import org.idiginfo.esearch.ESearchResult;
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
    List<MedlineCitation> citations = null;
    EntrezDocuments documents = null;

    // Possible values for field (simplified from full description)
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

    /**
     * Create a response
     * 
     * @param searchResult
     */
    public EntrezSearchResponse(ESearchResult searchResult) {
	List<Object> objects = searchResult.getFieldObjects();
	processObjects(objects);
    }

    private void processObjects(List<Object> objects) {
	for (Object object : objects) {
	    if (object instanceof Count) {
		Count count = (Count) object;
	    } else if (object instanceof Count) {
		RetMax count = (RetMax) object;
	    } else if (object instanceof Count) {
		RetStart retStart = (RetStart) object;
	    } else if (object instanceof Count) {
		QueryKey queryKey = (QueryKey) object;
	    } else if (object instanceof Count) {
		WebEnv webEnv = (WebEnv) object;
	    } else if (object instanceof Count) {
		IdList idList = (IdList) object;
	    } else if (object instanceof Count) {
		TranslationSet translationSet = (TranslationSet) object;
	    } else if (object instanceof Count) {
		WebEnv webEnv = (WebEnv) object;
	    } else if (object instanceof Count) {
		TranslationStack translationStack = (TranslationStack) object;
	    } else if (object instanceof Count) {
		QueryTranslation queryTranslation = (QueryTranslation) object;
	    } else if (object instanceof Count) {
		ERROR error = (ERROR) object;
	    }
	}
    }
}
