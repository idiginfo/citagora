package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "citagora_objects")
public abstract class CitagoraObjectImpl implements CitagoraObject {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer myId;
    String type;
    String uri;
    String wasAttributedTo;
    @Temporal(TemporalType.TIMESTAMP)
    Date created;
    @Temporal(TemporalType.TIMESTAMP)
    Date updated;
    String source;
    String rights;
    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.PERSIST)
    CitagoraAgent generator;
    @Temporal(TemporalType.TIMESTAMP)
    Date generated;
    // TODO still need to decide whether there is a set of annotations, or only
    // tags and comments
    // This property will not work for existing implementation, as is
    // @OneToMany(mappedBy = "target", targetEntity = AnnotationImpl.class,
    // cascade = CascadeType.PERSIST)
    @Transient
    List<Annotation> annotations;

    // Non-persistent members
    @Transient
    transient static int objectId = 0;
    @Transient
    transient String myCollection;

    // @Transient
    // String id = null;

    /**
     * Perform operations required before persisting an object
     */
    @PrePersist
    protected void onCreate() {
	updated = new Date();
	// created may not be the database timestamp. It may be set by the
	// creator
	if (created == null)
	    created = updated;

    }

    /**
     * after the object has been inserted, update the uri field with the new
     * value of myId
     */
    @PostPersist
    protected void afterCreate() {
	if (uri == null) {
	    String id = makeId(myCollection, myId);
	    uri = id;
	}
    }

    /**
     * Perform operations required before updating an object
     */
    @PreUpdate
    protected void onUpdate() {
	updated = new Date();
    }

    public String getCollecton() {
	return myCollection;
    }

    void setCollection(String collection) {
	myCollection = collection;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public static String makeId(String collection, int myId) {
	String id = CitagoraObject.NAMESPACE + collection + "/" + myId;
	return id;
    }

    /*
     * public void initId() { id = makeId(myCollection, myId); if (uri == null)
     * uri = id; }
     * 
     * public String getId() { if (id == null) { // initId(); } return id; }
     * 
     * public void setId(String id) { this.id = id; }
     */
    public String getUri() {
	return uri;
    }

    public void setUri(String uri) {
	this.uri = uri;
    }

    public String getWasAttributedTo() {
	return wasAttributedTo;
    }

    public void setWasAttributedTo(String wasAttributedTo) {
	this.wasAttributedTo = wasAttributedTo;
    }

    public Date getCreated() {
	return created;
    }

    public void setCreated(Date created) {
	this.created = created;
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getRights() {
	return rights;
    }

    public void setRights(String rights) {
	this.rights = rights;
    }

    public CitagoraAgent getGenerator() {
	return generator;
    }

    public void setGenerator(CitagoraAgent generator) {
	this.generator = generator;
    }

    public Date getGenerated() {
	return generated;
    }

    public void setGenerated(Date generated) {
	this.generated = generated;
    }

    public List<Annotation> getAnnotations() {
	if (annotations == null) {
	    annotations = new Vector<Annotation>();
	}
	return annotations;
    }

    public void addAnnotation(Annotation annotation) {
	if (annotations == null) {
	    annotations = new Vector<Annotation>();
	}
	annotations.add(annotation);
    }

    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return uri;
    }
}
