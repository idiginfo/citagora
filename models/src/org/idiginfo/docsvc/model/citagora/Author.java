package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Interface to implement the Citagora Author object
 * 
 * @author griccardi
 * 
 */

public interface Author extends Person {
	List<Reference> getAuthorReferences();

	void removeAuthorReference(Reference reference);

	void addAuthorReference(Reference reference);

}
