package org.idiginfo.docsvc.model.mapping;

import java.util.Iterator;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
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
	
	if (factory==null || fromDocument==null) return null;
	
	CitagoraDocument toDocument = factory.createCitagoraDocument();
	toDocument.setGenerator(null);
	Reference toReference = factory.createReference();
	// TODO change to Reference toReference
	toReference.setSource(fromDocument.getSource());
	toReference.setUri(fromDocument.getId());
	toReference.setTitle(fromDocument.getTitle());
	toReference.setPageStart(fromDocument.getPageStart());
	toReference.setVolume(fromDocument.getVolume());
	toReference.setIssued(fromDocument.getDateObject());
	toReference.setDoi(fromDocument.getDoi());
	//TODO authors
	String authors = fromDocument.getAuthors();
	toReference.setAuthors(authors);
	//TODO number
	toReference.setRights(fromDocument.getCopyright());
	//TODO isbn
	//TODO publisher
	//TODO publicationname
	
	toReference.setPublisher(fromDocument.getPublisher());
	
	return toReference;
    }
}
