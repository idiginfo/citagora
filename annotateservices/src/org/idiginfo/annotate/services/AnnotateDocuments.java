package org.idiginfo.annotate.services;

import java.util.ArrayList;
import java.util.List;

public class AnnotateDocuments {

	public AnnotateDocuments(AnnotateDocument[][] documentArray) {
		for (int i = 0; i < documentArray.length; i++) {
			for (int j = 0; j < documentArray[i].length; j++) {
				documents.add(documentArray[i][j]);
			}
		}
	}

	private List<AnnotateDocument> documents = new ArrayList<AnnotateDocument>();

	public AnnotateDocument getDocument(int i) {
		if (i < 0 || i > documents.size())
			return null;
		return documents.get(i);
	}

	public int size() {
		return documents.size();
	}
}
