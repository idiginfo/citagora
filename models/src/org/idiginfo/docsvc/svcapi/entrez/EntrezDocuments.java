package org.idiginfo.docsvc.svcapi.entrez;

import java.util.ArrayList;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.medline.MedlineCitation;
import org.idiginfo.medline.MedlineCitationSet;

/**
 * Class to implement Documents object for Entrez (NCBI)
 * 
 * @author griccardi
 * 
 */

public class EntrezDocuments extends ArrayList<Document> {
	private static final long serialVersionUID = 1;

	public EntrezDocuments() {

	}

	public EntrezDocuments(MedlineCitationSet citations) {
		if (citations == null || citations.getMedlineCitation() == null)
			return;
		for (MedlineCitation citation : citations.getMedlineCitation()) {
			add(new EntrezDocument(citation));
		}
	}

	public void add(EntrezFetchResponse response) {
		if (response == null || response.getCitations() == null)
			return;
		for (MedlineCitation citation : response.getCitations()) {
			add(new EntrezDocument(citation));
		}
	}

}
