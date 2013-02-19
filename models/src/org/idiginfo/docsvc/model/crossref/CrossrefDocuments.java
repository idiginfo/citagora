package org.idiginfo.docsvc.model.crossref;

import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;

public class CrossrefDocuments extends Vector<Document> implements Documents {

    private static final long serialVersionUID = 1L;

    /**
     * Convert the List<SpringerRecord> into List<Document>
     * 
     * @param documents
     */
    public CrossrefDocuments(List<CrossrefDocument> documents) {
	if (documents == null)
	    return;
	for (CrossrefDocument doc : documents) {
	    add(doc);
	}
    }

    @Override
    public Document getDocument(int i) {
	return get(i);
    }

}
