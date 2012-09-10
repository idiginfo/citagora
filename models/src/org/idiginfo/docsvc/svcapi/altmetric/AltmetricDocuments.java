package org.idiginfo.docsvc.svcapi.altmetric;

import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

public class AltmetricDocuments extends Vector<Document> implements Documents {
	private static final long serialVersionUID = 1L;

	AltmetricDocuments() {

	}

	/**
	 * Convert the List<AltmetricRecord> into List<Document>
	 * 
	 * @param documents
	 */
	public AltmetricDocuments(List<AltmetricRecord> results) {
		if (results == null)
			return;
		addAll(results);
	}

	public Document getDocument(int i) {
		return get(i);
	}

	public Documents toDocument() {
		// TODO Auto-generated method stub
		return null;
	}

}
