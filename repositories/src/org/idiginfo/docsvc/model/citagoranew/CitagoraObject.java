package org.idiginfo.docsvc.model.citagoranew;

import java.util.Date;
import java.util.List;

public interface CitagoraObject extends UriObject {

	public static final String NAMESPACE = "http://citagora.org/";
	String getType();

	String getId();

	String getUri();

	String getWasAttributedTo();

	Date getCreated();

	String getSource();

	String getRights();

	CitagoraAgent getGenerator(); // same as wasAttributedTo?

	Date getGenerated();

	List<Annotation> getAnnotations();
}
