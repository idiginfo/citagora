package org.idiginfo.docsvc.svcapi.entrez;

import java.util.List;

import org.idiginfo.esearch.ESearchResult;
import org.idiginfo.medline.MedlineCitation;
import org.idiginfo.medline.MedlineCitationSet;

public class EntrezFindResponse {
    List<MedlineCitation> citations = null;
    EntrezDocuments documents = null;

    /**
     * Create a response object for an efetch result
     * 
     * @param citationSet
     */
    public EntrezFindResponse(MedlineCitationSet citationSet) {
	if (citations != null) {
	    this.citations = citationSet.getMedlineCitation();
	}
    }

    public EntrezDocuments getDocuments() {
	if (documents != null)
	    return documents;
	documents = new EntrezDocuments();
	for (MedlineCitation citation : citations) {
	    documents.add(new EntrezDocument(citation));
	}
	return documents;
    }

}
