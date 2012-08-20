package org.idiginfo.docsvc.model.altmetric;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;

public class AltmetricDocuments extends Vector<Document> implements Documents {
	private static final long serialVersionUID = 1L;

	AltmetricDocuments() {

	}

	/**
	 * Convert the List<AltmetricRecord> into List<Document>
	 * 
	 * @param documents
	 */
	public AltmetricDocuments(List<AltmetricDocument> results) {
		if (results == null)
			return;
		Iterator<AltmetricDocument> documents = results.iterator();
		while (documents.hasNext()) {
			add(documents.next());
		}
	}

	public Document getDocument(int i) {
		return get(i);
	}

	public Documents toDocument() {
		// TODO Auto-generated method stub
		return null;
	}

}
