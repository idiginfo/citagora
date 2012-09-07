package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

public interface Annotation extends CitagoraObject {
	CitagoraObject getTarget();

	String getCharacterEncoding();

	String getChars();

	Person getAnnotator(); // same as wasAttributedTo?

	Date getAnnotated();
}
