package org.idiginfo.docsvc.model.citagora;

import java.util.List;

/**
 * Factory to create citagora objects
 * 
 * @author griccardi
 * @param <K>
 * 
 */
public abstract class CitagoraFactory {

    protected static CitagoraFactory factory = null;

    public static CitagoraFactory getFactory() {
	return factory;
    }

    public abstract Author createAuthor();

    public abstract Author findAuthor(int key);

    public abstract CitagoraAgent createCitagoraAgent();

    public abstract CitagoraAgent findCitagoraAgent(int key);

    public abstract CitagoraDocument createCitagoraDocument();

    public abstract CitagoraDocument findCitagoraDocument(int key);

    public abstract Comment createComment();

    public abstract Comment findComment(int key);

    public abstract Person createPerson();

    public abstract RatingType createRatingType(String type);

    public abstract RatingType findRatingType(int key);

    public abstract Reference createReference();

    public abstract Reference findReference(int key);

    public abstract Reply createReply();

    public abstract Reply findReply(int key);

    public abstract Review createReview();

    public abstract Review findReview(int key);

    public abstract Tag createTag();

    public abstract Tag findTag(int key);

    public abstract Person createPerson(Class<?> subclass);

    public abstract List<Reference> findReferences(String doi);

    public abstract void init();

    public abstract boolean merge(Object obj);

    public abstract boolean openTransaction();

    public abstract boolean commitTransaction();

    public abstract boolean rollbackTransaction();

    public abstract Person findPerson(int key);

    public abstract CitagoraObject findCitagoraObject(int key);

    public abstract void refresh(Object obj);

    public abstract void flush() ;
}
