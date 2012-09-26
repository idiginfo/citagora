package org.idiginfo.docsvc.jpa.citagora;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.RollbackException;

import org.idiginfo.docsvc.model.citagora.Author;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Comment;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.model.citagora.RatingType;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.Reply;
import org.idiginfo.docsvc.model.citagora.Review;
import org.idiginfo.docsvc.model.citagora.Tag;

/**
 * Class to support Citagora object creation and persistence management
 * 
 * @author griccardi
 * 
 */
public class CitagoraFactoryImpl extends CitagoraFactory {
    private EntityManagerFactory emf;
    @PersistenceContext
    private EntityManager em;

    public CitagoraFactoryImpl() {
	factory = this;
    }

    public CitagoraFactoryImpl(boolean initEntityManager) {
	this();
	if (initEntityManager)
	    init();
    }

    @Override
    public boolean commitTransaction() {
	EntityTransaction t = em.getTransaction();
	if (!t.isActive())
	    return false;
	try {
	    t.commit();
	    return true;
	} catch (RollbackException e) {
	    return false;
	}
    }

    @Override
    public Author createAuthor() {
	Author person = new PersonImpl(Author.class);
	return person;
    }

    @Override
    public CitagoraAgent createCitagoraAgent() {
	CitagoraAgent agent = new PersonImpl(CitagoraAgent.class);
	return agent;
    }

    @Override
    public CitagoraDocument createCitagoraDocument() {
	return new CitagoraDocumentImpl();
    }

    @Override
    public Comment createComment() {
	return new CommentImpl();
    }

    @Override
    public Person createPerson() {
	return new PersonImpl();
    }

    @Override
    public Person createPerson(Class<?> subclass) {
	// TODO Auto-generated method stub
	return new PersonImpl(subclass);
    }

    @Override
    public RatingType createRatingType(String type) {
	return new RatingType(type);
    }

    @Override
    public Reference createReference() {
	return new ReferenceImpl();
    }

    @Override
    public Reply createReply() {
	return new ReplyImpl();
    }

    @Override
    public Review createReview() {
	return new ReviewImpl();
    }

    @Override
    public Tag createTag() {
	return new TagImpl();
    }

    @Override
    public Author findAuthor(int key) {
	PersonImpl person = (PersonImpl) findPerson(key);
	if (person == null || !person.getIsAuthor())
	    return null;
	return person;
    }

    @Override
    public CitagoraAgent findCitagoraAgent(int key) {
	Person person = findPerson(key);
	if (person == null)
	    return null;
	if (person.getIsAgent())
	    return (CitagoraAgent) person;
	return null;
    }

    @Override
    public CitagoraDocument findCitagoraDocument(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    /**
     * Find an object in the repository of unknown subtype of CitagoraObject
     */
    public CitagoraObject findCitagoraObject(int key) {
	CitagoraObject obj = findObject(CitagoraObjectImpl.class, key);
	return obj;
    }

    @Override
    public Comment findComment(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    /**
     * Get an object from the repository of a specific type.
     */
    protected <K> K findObject(Class<K> type, int key) {
	getEntityManager();
	K obj = em.find(type, key);
	return obj;
    }

    @Override
    public Person findPerson(int key) {
	PersonImpl obj = findObject(PersonImpl.class, key);
	return obj;
    }

    @Override
    public RatingType findRatingType(int key) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Reference findReference(int key) {
	return findObject(ReferenceImpl.class, key);
    }

    static final String REFERENCE_CLASS_NAME = ReferenceImpl.class
	    .getCanonicalName();

    @SuppressWarnings("unchecked")
    // we know that the query returns the correct class
    @Override
    public List<Reference> findReferences(String doi) {
	getEntityManager();
	Query q = em.createQuery("SELECT e FROM "
		+ ReferenceImpl.class.getCanonicalName()
		+ " e WHERE e.doi=:doi");
	q.setParameter("doi", doi);
	List<Reference> references = q.getResultList();
	return references;
    }

    @Override
    public Reply findReply(int key) {
	return findObject(ReplyImpl.class, key);
    }

    @Override
    public Review findReview(int key) {
	return findObject(ReviewImpl.class, key);
    }

    @Override
    public Tag findTag(int key) {
	return findObject(TagImpl.class, key);
    }

    public EntityManager getEntityManager() {
	if (em == null)
	    init();
	return em;
    }

    @Override
    public void init() {
	if (emf == null)
	    emf = Persistence.createEntityManagerFactory("repositories");
	if (em != null)
	    em.close();
	em = emf.createEntityManager();
    }

    @Override
    public boolean openTransaction() {
	getEntityManager();
	EntityTransaction t = em.getTransaction();
	if (!t.isActive()) {
	    t.begin();
	}
	return true;
    }

    /**
     * Persist the object. If the transaction is not active, open it
     */
    @Override
    public boolean merge(Object obj) {
	// test to see if object is already persistent

	boolean localTransaction = false;
	EntityManager em = getEntityManager();
	EntityTransaction t = em.getTransaction();
	if (!t.isActive()) {
	    localTransaction = true;
	    t.begin();
	}
	em.merge(obj);
	if (localTransaction) {
	    try {
		t.commit();
	    } catch (Exception e) {
		if (t.isActive())
		    t.rollback();
	    }
	}
	return true;
    }

    @Override
    public boolean rollbackTransaction() {
	EntityTransaction t = em.getTransaction();
	if (!t.isActive())
	    return false;
	try {
	    t.rollback();
	    return true;
	} catch (PersistenceException e) {
	    return false;
	}
    }

}
