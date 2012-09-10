package org.idiginfo.docsvc.svcapi.annotate.model;

import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

public class AnnotateDocuments extends Vector<Document> implements
		List<Document>, Documents {

	private static final long serialVersionUID = 1L;

	public AnnotateDocuments(AnnotateDocument[][] documentArray) {
		for (int i = 0; i < documentArray.length; i++) {
			for (int j = 0; j < documentArray[i].length; j++) {
				this.add(documentArray[i][j]);
			}
		}
	}

	public Document getDocument(int i) {
		if (i < 0 || i > size())
			return null;
		return get(i);
	}

}
