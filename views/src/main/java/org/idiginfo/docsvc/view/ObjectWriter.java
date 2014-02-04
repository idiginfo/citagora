package org.idiginfo.docsvc.view;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.*;

/**
 * Class defines objects for the ObjectWriter interface
 * 
 * @author griccardi
 * 
 */

public interface ObjectWriter {

	public String writeDocument(Document document);

	public String writeDocuments(List<Document> documents);

	public String write(Object objects);
}
