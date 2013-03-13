package org.idiginfo.docsvc.svcapi.entrez;

import java.util.ArrayList;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

public class EntrezDocuments extends ArrayList<Document> implements Documents {
    private static final long serialVersionUID = 1;

    @Override
    public Document getDocument(int i) {
	return get(i);
    }

}
