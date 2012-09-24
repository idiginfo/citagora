package org.idiginfo.docsvc.model.citagora;

/**
 * Factory to create citagora objects
 * 
 * @author griccardi
 * 
 */
public abstract class CitagoraFactory {

    protected static CitagoraFactory factory = null;

    public static CitagoraFactory getFactory() {
	return factory;
    }

    public abstract Author createAuthor();

    public abstract Author createAuthor(int key);

    public abstract CitagoraAgent createCitagoraAgent();

    public abstract CitagoraAgent createCitagoraAgent(int key);

    public abstract CitagoraDocument createCitagoraDocument();

    public abstract CitagoraDocument createCitagoraDocument(int key);

    public abstract Comment createComment();

    public abstract Comment createComment(int key);

    public abstract Person createPerson();

    public abstract Person createPerson(int key);

    public abstract RatingType createRatingType(String type);

    public abstract RatingType createRatingType(int key);

    public abstract Reference createReference();

    public abstract Reference createReference(int key);

    public abstract Reply createReply();

    public abstract Reply createReply(int key);

    public abstract Review createReview();

    public abstract Review createReview(int key);

    public abstract Tag createTag();

    public abstract Tag createTag(int key);


}
