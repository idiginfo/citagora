package org.idiginfo.docsvc.jpa.test;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.jpa.citagora.ReferenceImpl;

/**
 * Class to map objects between model.apisvc.Document and
 * model.citagora.Reference
 * 
 * @author griccardi
 * 
 */
public class DocumentReference {

	public Reference map(Document fromDocument) {
		ReferenceImpl toReference = new ReferenceImpl();
		// TODO change to Reference toReference
		toReference.setSource(fromDocument.getSource());
		toReference.setId(fromDocument.getId());
		toReference.setTitle(fromDocument.getTitle());
		toReference.setPageStart(fromDocument.getPageStart());
		toReference.setVolume(fromDocument.getVolume());
		toReference.setIssued(fromDocument.getDateObject());
		toReference.setDoi(fromDocument.getDoi());

		return toReference;
	}
}
