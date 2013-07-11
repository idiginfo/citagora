package org.idiginfo.docsvc.model.citagora;

/**
 * Interface to implement the Citagora AnnotationBody object
 * 
 * @author griccardi
 * 
 */

public interface AnnotationBody extends UriObject {

	static final String TYPE = "cnt:ContextAsText";
	static final String COLLECTION = "nbody";

	String getCharacterEncoding();

	String getChars();

	String getUri();

	void setChars(String string);

	void setCharacterEncoding(String string);

}
