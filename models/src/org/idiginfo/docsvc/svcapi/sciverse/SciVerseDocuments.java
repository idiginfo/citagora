package org.idiginfo.docsvc.svcapi.sciverse;

import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

/**
 * Class to implement Documents object for Sciverse
 * 
 * @author griccardi
 * 
 */

public class SciVerseDocuments extends Vector<Document> implements Documents {

	private static final long serialVersionUID = 1L;

	public SciVerseDocuments(List<SciVerseDocument> records) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Document getDocument(int i) {
		// TODO Auto-generated method stub
		return get(i);
	}

}
