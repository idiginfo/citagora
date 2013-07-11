package org.idiginfo.docsvc.model.citagora;

import java.util.Date;
import java.util.List;

/**
 * Interface to implement the CitagoraObject
 * 
 * @author griccardi
 * 
 */

public interface CitagoraObject extends UriObject {

	public static final String NAMESPACE = "http://citagora.org/";

	String getType();

	String getUri();

	void setUri(String uri);

	String getWasAttributedTo();

	void setWasAttributedTo(String actor);

	Date getCreated();

	void setCreated(Date created);

	String getSource();

	void setSource(String source);

	String getSourceId();

	void setSourceId(String source);

	String getRights();

	void setRights(String rights);

	CitagoraAgent getGenerator(); // same as wasAttributedTo?

	void setGenerator(CitagoraAgent generator);

	Date getGenerated();

	void setGenerated(Date generated);

	List<Annotation> getAnnotations();

	String getId();

}
