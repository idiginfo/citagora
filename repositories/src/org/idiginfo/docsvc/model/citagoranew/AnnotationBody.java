package org.idiginfo.docsvc.model.citagoranew;

public interface AnnotationBody extends UriObject {

	static final String TYPE = "cnt:ContextAsText";
	static final String COLLECTION = "nbody";

	String getCharacterEncoding();

	String getChars();
	
	String getUri();

}
