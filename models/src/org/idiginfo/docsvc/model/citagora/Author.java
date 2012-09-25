package org.idiginfo.docsvc.model.citagora;

import java.util.List;

public interface Author extends Person  {
	List<Reference> getAuthorReferences();

}
