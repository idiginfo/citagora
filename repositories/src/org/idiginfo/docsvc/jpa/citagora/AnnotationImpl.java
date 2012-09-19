package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Person;
import org.idiginfo.docsvc.svcapi.citagora.AnnotationBodyImpl;
import org.idiginfo.docsvc.svcapi.citagora.CitagoraObjectImpl;

@Entity(name = "annotations")
public class AnnotationImpl extends CitagoraObjectImpl implements Annotation {

    @ManyToOne(targetEntity = CitagoraObjectImpl.class, cascade = CascadeType.PERSIST)
    // target is transient because it is the inverse of a non-transient
    // relationship (json serialization)
    transient CitagoraObjectImpl target;
    @OneToMany(targetEntity = Person.class, cascade = CascadeType.PERSIST)
    Person annotator;
    @Embedded
    AnnotationBodyImpl body;
    String modelVersion;

    public AnnotationImpl() {
	body = new AnnotationBodyImpl();
	setType(Annotation.TYPE);
	setCollection(Annotation.COLLECTION);
	initId();
    }

    public CitagoraObject getTarget() {
	return target;
    }

    public void setTarget(CitagoraObject target) {
	this.target = target;
    }

    public String getCharacterEncoding() {
	return body.getCharacterEncoding();
    }

    public void setCharacterEncoding(String characterEncoding) {
	// TODO
    }

    public String getChars() {
	return body.getChars();
    }

    public void setChars(String chars) {
	// TODO this.chars = chars;
    }

    public Person getAnnotator() {
	return annotator;
    }

    public void setAnnotator(Person annotator) {
	this.annotator = annotator;
    }

    public Date getAnnotated() {
	return created;
    }

    public void setAnnotated(Date annotated) {
	this.created = annotated;
    }

    public AnnotationBody getBody() {
	return body;
    }

    public String getModelVersion() {
	return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
	this.modelVersion = modelVersion;
    }
}
