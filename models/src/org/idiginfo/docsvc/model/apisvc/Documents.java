package org.idiginfo.docsvc.model.apisvc;

import java.util.List;

/**
 * Interface to implement the Documents object
 * 
 * @author griccardi
 * 
 */

public interface Documents extends List<Document> {
	public Document getDocument(int i);
	// List<? extends Documents> getDocuments();
}
