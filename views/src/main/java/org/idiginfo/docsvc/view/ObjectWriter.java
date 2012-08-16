package org.idiginfo.docsvc.view;

import org.idiginfo.docsvc.model.model.*;

public interface ObjectWriter {

	public String write(Document document);
	public String write(Documents documents);
}
