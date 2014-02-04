package org.idiginfo.docsvc.model.apisvc;

import java.util.List;
import java.util.Vector;

public class ApiSvcUtilities {
	
	/**
	 * Create a List<Document> object
	 * @param documents
	 * @return
	 */
	public static List<Document> createDocuments(
			List<? extends Document> documents) {
		List<Document> list = new Vector<Document>();
		if (documents == null)
			return list;
		for (Document doc : documents) {
			list.add(doc);
		}
		return list;
	}

}
