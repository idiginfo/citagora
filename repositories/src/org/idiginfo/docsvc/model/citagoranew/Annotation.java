package org.idiginfo.docsvc.model.citagoranew;

import java.util.Date;

public interface Annotation extends CitagoraObject {
	static final String TYPE = "oa:annotation";
	static final String COLLECTION = "annotation";

	CitagoraObject getTarget(); 

	AnnotationBody getBody();

	Person getAnnotator(); // same as wasAttributedTo?

	Date getAnnotated();

	String getModelVersion();
}
