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
    protected static String persistence = "local";

    public static CitagoraFactory getFactory() {
	return factory;
    }
    
    public abstract void renewPersistence();

    public abstract Author createAuthor();

    public abstract Author findAuthor(int key);

    public abstract CitagoraAgent createCitagoraAgent();

    public abstract CitagoraAgent findCitagoraAgent(int key);

    public abstract Container createContainer();

    public abstract Container findContainer(int key);

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

    public abstract Reference findReferenceByDoi(String doi);

    public abstract void init();

    public abstract boolean merge(UriObject obj);

    public abstract boolean isTransactionActive();

    public abstract boolean openTransaction();

    public abstract boolean commitTransaction();

    public abstract boolean rollbackTransaction();

    public abstract Person findPerson(int key);

    public abstract CitagoraObject findCitagoraObject(int key);

    public abstract void refresh(Object obj);

    public abstract void flush();

    public abstract Container createContainer(Container containerFields);

    public abstract CitagoraAgent getServiceAgent(String serviceName);

    public abstract Person getPerson(String name);

    public List<Reference> findReferencesById(String doi) {
	// TODO Auto-generated method stub
	return null;
    }

    public CitagoraObject findCitagoraObjectByURI(String uri) {
	// TODO Auto-generated method stub
	return null;
    }

    public static void setPersistence(String persistence){
	CitagoraFactory.persistence = persistence;
    }

    public static String getPersistence() {
	return persistence;
    }

}
