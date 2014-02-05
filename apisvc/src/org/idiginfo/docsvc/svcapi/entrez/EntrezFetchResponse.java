package org.idiginfo.docsvc.svcapi.entrez;

import java.util.List;

import org.idiginfo.esearch.ESearchResult;
import org.idiginfo.medline.MedlineCitation;
import org.idiginfo.medline.MedlineCitationSet;

/**
 * Class to process Entrez (NCBI) response content
 * 
 * @author griccardi
 * 
 */

public class EntrezFetchResponse {
	List<MedlineCitation> citations = null;
	EntrezDocuments documents = null;

	/**
	 * Create a response object for an efetch result
	 * 
	 * @param citationSet
	 */
	public EntrezFetchResponse(MedlineCitationSet citationSet) {
		if (citationSet != null) {
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

	public List<MedlineCitation> getCitations() {
		return citations;
	}

}
