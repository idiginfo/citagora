package org.idiginfo.docsvc.model.citagoranew;

import java.util.List;

public interface Author extends Person  {
	List<Reference> getAuthorReferences();

}
