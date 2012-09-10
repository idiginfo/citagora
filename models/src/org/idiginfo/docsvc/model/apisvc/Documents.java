package org.idiginfo.docsvc.model.apisvc;

import java.util.List;

public interface Documents extends List<Document> {
	public Document getDocument(int i);
	//List<? extends Documents> getDocuments();
}
