package org.idiginfo.docsvc.svcapi.mas.model;

import java.util.Vector;

import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;

public class MasPublicationObject extends Vector<Document> implements Documents {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MasPublicationObject(MasResultObject<MasPublication> objects) {
		addAll(objects.result);
	}

	@Override
	public Document getDocument(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
