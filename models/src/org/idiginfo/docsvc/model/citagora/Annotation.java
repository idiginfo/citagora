package org.idiginfo.docsvc.model.citagora;

import java.util.Date;

/**
 * Interface to implement the Citagora Annotation object
 * 
 * @author griccardi
 * 
 */

public interface Annotation extends CitagoraObject {
	static final String TYPE = "oa:annotation";
	static final String COLLECTION = "annotation";

	CitagoraObject getTarget();

	void setTarget(CitagoraObject target);

	// AnnotationBody getBody();

	Person getAnnotator(); // same as wasAttributedTo?

	Date getAnnotated();

	void setAnnotated(Date time);

	String getModelVersion();

	void setModelVersion(String version);

	void setAnnotator(Person commentor);

	String getCharacterEncoding();

	void setCharacterEncoding(String string);

	String getChars();

	void setChars(String string);

	AnnotationBody getBody();

	// Note that OA has a more complex structure to represent selectors and
	// specifiers
	// see http://www.openannotation.org/spec/core/#Specifier
	String getSpecifier();

	void setSpecifier(String specifier);

	public void setTags(String tags);

	public String getTags();

	public void setContext(String mark);

	public String getContext();

}
