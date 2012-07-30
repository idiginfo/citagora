package org.idiginfo.annotationmodel;

import org.idiginfo.annotate.services.AnnotateDocument;

public interface Documents {
	public AnnotateDocument getDocument(int i);

	public int size();

}
