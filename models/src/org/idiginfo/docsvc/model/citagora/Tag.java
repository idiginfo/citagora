package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

public interface Tag extends Annotation {

	CitagoraDocument getDocumentTagged(); // same as getTarget

	void setTarget(CitagoraDocument document);

	void setAnnotator(Person annotator);

	void setAnnotated(Date time);

	void setGenerator(CitagoraAgent generator);

	void setGenerated(Date time);

	void setModelVersion(String string);

}
