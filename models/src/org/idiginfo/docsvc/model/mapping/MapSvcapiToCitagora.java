package org.idiginfo.docsvc.model.mapping;

import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Container;
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

	/**
	 * Create Container for the fromDocument
	 * 
	 * @param
	 * @return toContainer
	 */
	public Container createContainer(Container containerFields,
			Document fromDocument) {
		Container toContainer = factory.createContainer(containerFields);
		factory.merge(toContainer);
		toContainer.setGenerator(null);
		Reference toReference = map(fromDocument);
		toContainer.setIsAbout(toReference);

		return toContainer;
	}

	/**
	 * Create the toReference from the fromDocument
	 * 
	 * @param
	 * @return toReference
	 */
	public Reference map(Document fromDocument) {

		if (factory == null || fromDocument == null)
			return null;

		// List<Reference> references = factory.findReferences(fromDocument
		// .getDoi());
		Reference toReference;
		// if (references.size() > 0) {
		// toReference = references.get(0);
		// return toReference;
		// }
		toReference = factory.createReference();
		factory.merge(toReference);
		toReference.setSource(fromDocument.getSource());
		toReference.setSourceId(fromDocument.getSourceId());
		toReference.setBiboType(Reference.ARTICLE_TYPE);
		toReference.setUri(fromDocument.getUri());
		toReference.setTitle(fromDocument.getTitle());
		toReference.setPageStart(fromDocument.getPageStart());
		toReference.setVolume(fromDocument.getVolume());
		toReference.setIssue(fromDocument.getIssue());
		toReference.setIssued(fromDocument.getDateObject());
		toReference.setDoi(fromDocument.getDoi());
		// TODO authors
		String authors = fromDocument.getAuthors();
		toReference.setAuthorString(authors);
		toReference.setUrl(fromDocument.getUrl());
		toReference.setRights(fromDocument.getCopyright());
		toReference.setKeywords(fromDocument.getKeywords());
		toReference.setMeshTerms(fromDocument.getMeshTerms());
		toReference.setPublisher(fromDocument.getPublisher());
		toReference.setAbstract(fromDocument.getAbstractText());
		toReference.setAggregationType(fromDocument.getAggregationType());
		toReference.setArXivId(fromDocument.getarXivId());
		toReference.setAuthorNotes(fromDocument.getAuthorNotes());
		toReference.setCoverDate(fromDocument.getCoverDate());
		toReference.setEdition(fromDocument.getEdition());
		toReference.seteIssn(fromDocument.geteIssn());
		toReference.setGenre(fromDocument.getGenre());
		toReference.setItemNumber(fromDocument.getItemNumber());
		toReference.setPublicationDate(fromDocument.getPublicationDate());
		toReference.setPmid(fromDocument.getPMId());
		toReference.setRights(fromDocument.getRights());
		toReference.setSeriesTitle(fromDocument.getSeriesTitle());

		if (fromDocument.getPublicationName() != null) {
			// map journal fields
			// part of a larger publication, id is from issn
			Reference toJournal = mapJournal(fromDocument);
			toReference.setIsPartOf(toJournal);
		}
		return toReference;
	}

	/**
	 * Map the toJournal from the fromDocument
	 * 
	 * @param
	 * @return toJournal
	 */
	public Reference mapJournal(Document fromDocument) {
		// part of a larger publication, id is from issn
		Reference toJournal = null;
		// check for existing journal
		String uri;
		if (fromDocument.getIssn() != null) {
			uri = "urn:issn:" + fromDocument.getIssn();
		} else if (fromDocument.getIsbn() != null) {
			uri = "urn:isbn:" + fromDocument.getIsbn();
		} else if (fromDocument.getPublicationName() != null) {
			// make up a consistent URI!
			uri = "urn:" + fromDocument.getPublicationName();
		} else {
			uri = "urn:citagora:" + fromDocument.getId();
		}
		CitagoraObject journal = factory.findCitagoraObjectByURI(uri);
		if (journal instanceof Reference) {
			// journal already present
			return (Reference) journal;
		} else if (journal != null) {
			// object in the system by URI but is not a reference
			// TODO handle this error!
			return null;
		} else { // add new journal
			toJournal = factory.createReference();
			factory.merge(toJournal);
			toJournal.setUri(uri);
			toJournal.setBiboType(Reference.JOURNAL_TYPE);
			toJournal.setIssn(fromDocument.getIssn());
			toJournal.setPublisher(fromDocument.getPublisher());
			toJournal.setTitle(fromDocument.getPublicationName());
			return toJournal;
		}
	}

	/**
	 * Update the toReference from the fromDocument
	 * 
	 * @param
	 * @return toReference
	 */
	public Reference update(Reference toReference, Document fromDocument) {

		if (factory == null || fromDocument == null)
			return null;

		// List<Reference> references = factory.findReferences(fromDocument
		// .getDoi());
		// Reference toReference;
		// if (references.size() > 0) {
		// toReference = references.get(0);
		// return toReference;
		// }
		if (toReference == null) {
			toReference = factory.createReference();
		}
		factory.merge(toReference);
		toReference.setSource(fromDocument.getSource());
		toReference.setBiboType(Reference.ARTICLE_TYPE);
		toReference.setUri(fromDocument.getUri());
		toReference.setTitle(fromDocument.getTitle());
		toReference.setPageStart(fromDocument.getPageStart());
		toReference.setVolume(fromDocument.getVolume());
		toReference.setIssue(fromDocument.getIssue());
		toReference.setIssued(fromDocument.getDateObject());
		toReference.setDoi(fromDocument.getDoi());
		// TODO authors
		String authors = fromDocument.getAuthors();
		toReference.setAuthorString(authors);
		toReference.setUrl(fromDocument.getUrl());
		toReference.setRights(fromDocument.getCopyright());
		toReference.setKeywords(fromDocument.getKeywords());
		toReference.setMeshTerms(fromDocument.getMeshTerms());

		toReference.setPublisher(fromDocument.getPublisher());

		if (fromDocument.getPublicationName() != null) {
			// part of a larger publication, id is from issn

			// check for existing journal
			String uri;
			if (fromDocument.getIssn() != null) {
				uri = "urn:issn:" + fromDocument.getIssn();
			} else if (fromDocument.getIsbn() != null) {
				uri = "urn:isbn:" + fromDocument.getIsbn();
			} else if (fromDocument.getPublicationName() != null) {
				// make up a consistent URI!
				uri = "urn:" + fromDocument.getPublicationName();
			} else {
				uri = "urn:citagora:" + fromDocument.getId();
			}
			CitagoraObject journal = factory.findCitagoraObjectByURI(uri);
			if (journal instanceof Reference) {
				toReference.setIsPartOf((Reference) journal);
				// journal already present
			} else if (journal != null) {
				// object in the system by URI but is not a reference
				// TODO handle this error!
			} else { // add new journal
				Reference toJournal = factory.createReference();
				toReference.setIsPartOf(toJournal);
				factory.merge(toJournal);
				toJournal.setUri(uri);
				toJournal.setBiboType(Reference.JOURNAL_TYPE);
				toJournal.setIssn(fromDocument.getIssn());
				toJournal.setPublisher(fromDocument.getPublisher());
				// TODO number?

				toJournal.setTitle(fromDocument.getPublicationName());
				toJournal.setIssued(fromDocument.getDateObject());
			}
		}
		return toReference;
	}

	/**
	 * Update the toComment from the fromAnnotation
	 * 
	 * @param
	 * @return toComment
	 */
	public Comment map(Container container, Annotation fromAnnotation) {
		if (container == null)
			return null;
		// Look for existing comment
		CitagoraObject object = factory.findCitagoraObjectByURI(fromAnnotation
				.getId());
		if (object != null) {
			if (object instanceof Comment) {
				return (Comment) object;
			}
			return null;
		}
		Comment toComment = factory.createComment();
		if (fromAnnotation.getDate() != null) {
			toComment.setCreated(fromAnnotation.getDate());
		}
		toComment.setAnnotated(fromAnnotation.getDate());
		toComment.setSource(fromAnnotation.getFullPageUrl());
		toComment.setChars(fromAnnotation.getNotetext());
		toComment.setUri(fromAnnotation.getFullPageUrl());
		toComment.setSpecifier(fromAnnotation.getMatch());
		toComment.setTags(fromAnnotation.getTags());
		toComment.setContext(fromAnnotation.getContext());
		container.addComment(toComment);
		return toComment;
	}
}
