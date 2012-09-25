package org.idiginfo.docsvc.model.mapping;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Reference;

/**
 * Class to map objects between model.apisvc.Document and
 * model.citagora.Reference
 * 
 * @author griccardi
 * 
 */
public class MapDocumentToReference {

    public Reference map(Document fromDocument) {
	CitagoraFactory factory = CitagoraFactory.getFactory();
	Reference toReference = factory.createReference();
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
