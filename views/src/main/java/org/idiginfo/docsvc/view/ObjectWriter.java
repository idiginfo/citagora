package org.idiginfo.docsvc.view;

import org.idiginfo.docsvc.model.apisvc.*;

public interface ObjectWriter {

	public String writeDocument(Document document);
	public String writeDocuments(Documents documents);
	public String write(Object objects);
}