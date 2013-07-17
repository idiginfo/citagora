package org.idiginfo.docsvc.svcapi.nature;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

/**
 * Class to implement Documents object for Nature
 * 
 * @author griccardi
 * 
 */

public class NatureDocuments extends Vector<Document> implements Documents {

	private static final long serialVersionUID = 1L;

	/**
	 * Convert the List<SpringerRecord> into List<Document>
	 * 
	 * @param records
	 */
	public NatureDocuments(List<NatureRecord> records) {
		if (records == null)
			return;
		Iterator<NatureRecord> documents = records.iterator();
		while (documents.hasNext()) {
			add(documents.next());
		}
	}

	@Override
	public Document getDocument(int i) {
		return get(i);
	}

}
