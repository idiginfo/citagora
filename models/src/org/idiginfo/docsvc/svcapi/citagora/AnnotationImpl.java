package org.idiginfo.docsvc.svcapi.citagora;

import java.util.Date;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Person;

public class AnnotationImpl extends CitagoraObjectImpl implements Annotation {

	transient CitagoraObject target;
	Person annotator;
	Date annotated;
	AnnotationBody body;
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
		return annotated;
	}

	public void setAnnotated(Date annotated) {
		this.annotated = annotated;
	}

	public AnnotationBody getBody() {
		return body;
	}

	public void setBody(AnnotationBody body) {
		this.body = body;
	}

	public String getModelVersion() {
		return modelVersion;
	}

	public void setModelVersion(String modelVersion) {
		this.modelVersion = modelVersion;
	}
}
