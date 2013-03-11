package org.idiginfo.docsvc.svcapi.entrez;

import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

public class EntrezDocuments extends Vector<Document> implements Documents {
    @Override
    public Document getDocument(int i) {
	return get(i);
    }

}
