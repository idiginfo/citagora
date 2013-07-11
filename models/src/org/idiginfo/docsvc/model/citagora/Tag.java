package org.idiginfo.docsvc.model.citagora;

/**
 * Interface to implement the Citagora Annotation Tag object
 * 
 * @author griccardi
 * 
 */

public interface Tag extends Annotation {

	public static final String TYPE = "oax:tag";

	Container getDocumentTagged(); // same as getTarget

	void setDocumentTagged(Container document);
}
