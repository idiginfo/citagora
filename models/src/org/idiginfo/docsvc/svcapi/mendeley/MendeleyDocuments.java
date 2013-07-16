package org.idiginfo.docsvc.svcapi.mendeley;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

/**
 * Class that implements the Mendeley Documents object
 * 
 * @author sflager
 * 
 */

public class MendeleyDocuments extends Vector<Document> implements Documents {

	private static final long serialVersionUID = 1L;

	/**
	 * Convert the List<MendeleyRecord> into List<Document>
	 * 
	 * @param records
	 */
	public MendeleyDocuments(List<MendeleyRecord> records) {
		if (records == null)
			return;
		Iterator<MendeleyRecord> documents = records.iterator();
		while (documents.hasNext()) {
			add(documents.next());
		}
	}

	@Override
	public Document getDocument(int i) {
		return get(i);
	}

}
