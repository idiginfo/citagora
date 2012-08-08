package org.idiginfo.springer.services;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.annotationmodel.Document;
import org.idiginfo.annotationmodel.Documents;

public class SpringerDocuments extends Vector<Document> implements Documents {

	private static final long serialVersionUID = 1L;

	/**
	 * Convert the List<SpringerRecord> into List<Document>
	 * @param records
	 */
	public SpringerDocuments(List<SpringerRecord> records) {
		if (records == null)
			return;
		Iterator<SpringerRecord> documents = records.iterator();
		while (documents.hasNext()) {
			add(documents.next());
		}
	}

	@Override
	public Document getDocument(int i) {
		return get(i);
	}

}
