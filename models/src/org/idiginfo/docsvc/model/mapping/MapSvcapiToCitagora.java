package org.idiginfo.docsvc.model.mapping;

import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.formula.functions.Today;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Reference;

/**
 * Class to map objects between model.apisvc.Document and
 * model.citagora.Reference
 * 
 * @author griccardi
 * 
 */
public class MapSvcapiToCitagora {

    CitagoraFactory factory = CitagoraFactory.getFactory();

    public CitagoraDocument createCitagoraDocument(Document fromDocument) {
	CitagoraDocument toDocument = factory.createCitagoraDocument();
	factory.merge(toDocument);
	toDocument.setGenerator(null);
	Reference toReference = map(fromDocument);
	toDocument.setIsAbout(toReference);

	return toDocument;
    }

    public Reference map(Document fromDocument) {

	if (factory == null || fromDocument == null)
	    return null;

	List<Reference> references = factory.findReferences(fromDocument
		.getDoi());
	Reference toReference;
//	if (references.size() > 0) {
//	    toReference = references.get(0);
//	    return toReference;
//	}
	toReference = factory.createReference();
	factory.merge(toReference);
	toReference.setSource(fromDocument.getSource());
	toReference.setUri(fromDocument.getId());
	toReference.setTitle(fromDocument.getTitle());
	toReference.setPageStart(fromDocument.getPageStart());
	toReference.setVolume(fromDocument.getVolume());
	toReference.setIssued(fromDocument.getDateObject());
	toReference.setDoi(fromDocument.getDoi());
	// TODO authors
	String authors = fromDocument.getAuthors();
	toReference.setAuthorString(authors);
	// TODO number
	toReference.setRights(fromDocument.getCopyright());
	// TODO isbn
	// TODO publisher
	// TODO publicationname

	toReference.setPublisher(fromDocument.getPublisher());

	if (fromDocument.getPublicationName() != null) {
	    // part of a larger publication
	    Reference toJournal = factory.createReference();
	    toReference.setIsPartOf(toJournal);
	    factory.merge(toJournal);
	    toJournal.setBiboType(Reference.JOURNAL_TYPE);
	    toJournal.setIssn(fromDocument.getIssn());
	    toJournal.setPublisher(fromDocument.getPublisher());
	    toJournal.setVolume(fromDocument.getVolume());
	    toJournal.setIssue(fromDocument.getIssue());
	    toJournal.setTitle(fromDocument.getPublicationName());
	    toJournal.setIssued(fromDocument.getDateObject());
	}

	return toReference;
    }

    public Comment map(Annotation fromAnnotation) {
	Comment toComment = factory.createComment();
	toComment.setAnnotated(fromAnnotation.getDate());
	toComment.setSource(fromAnnotation.getFullPageUrl());
	toComment.setChars(fromAnnotation.getContext());
	return toComment;
    }
}
