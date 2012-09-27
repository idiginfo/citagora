package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.Person;

@Entity
@Table(name = "annotations", schema = "citagora")
abstract public class AnnotationImpl extends CitagoraObjectImpl implements Annotation {

    @ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
    Person annotator;
    @Embedded
    AnnotationBodyImpl body;
    String modelVersion;

    public AnnotationImpl() {
	body = new AnnotationBodyImpl();
	setType(Annotation.TYPE);
	setCollection(Annotation.COLLECTION);
	// initId();
    }

    @Override
    public String getCharacterEncoding() {
	return body.getCharacterEncoding();
    }

    @Override
    public void setCharacterEncoding(String characterEncoding) {
	body.setCharacterEncoding(characterEncoding);
    }

    @Override
    public String getChars() {
	return body.getChars();
    }

    @Override
    public void setChars(String chars) {
	body.setChars(chars);
    }

    @Override
    public Person getAnnotator() {
	return annotator;
    }

    @Override
    public void setAnnotator(Person annotator) {
	this.annotator = annotator;
    }

    @Override
    public Date getAnnotated() {
	return created;
    }

    @Override
    public void setAnnotated(Date annotated) {
	this.created = annotated;
    }

    public AnnotationBody getBody() {
	return body;
    }

    @Override
    public String getModelVersion() {
	return modelVersion;
    }

    @Override
    public void setModelVersion(String modelVersion) {
	this.modelVersion = modelVersion;
    }

}
