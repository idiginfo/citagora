package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

public interface Annotation extends CitagoraObject {
	String TYPE = "oa:annotation";

	CitagoraObject getTarget();

	AnnotationBody getBody();

	Person getAnnotator(); // same as wasAttributedTo?

	Date getAnnotated();

	String getModelVersion();
}
