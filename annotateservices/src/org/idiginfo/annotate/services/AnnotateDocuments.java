package org.idiginfo.annotate.services;

import java.util.List;
import java.util.Vector;

import org.idiginfo.annotationmodel.Documents;

public class AnnotateDocuments extends Vector<AnnotateDocument> implements
		List<AnnotateDocument>, Documents {

	private static final long serialVersionUID = 1L;

	public AnnotateDocuments(AnnotateDocument[][] documentArray) {
		for (int i = 0; i < documentArray.length; i++) {
			for (int j = 0; j < documentArray[i].length; j++) {
				this.add(documentArray[i][j]);
			}
		}
	}

	public AnnotateDocument getDocument(int i) {
		if (i < 0 || i > size())
			return null;
		return get(i);
	}

}
