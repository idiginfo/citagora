package org.idiginfo.docsvc.svcapi.mas;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;

public class MasPublicationObject extends Vector<Document> implements Documents {
	MasPublicationObject(MasResultObject<MasPublication> objects) {
		addAll(objects.result);
	}

	@Override
	public Document getDocument(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
