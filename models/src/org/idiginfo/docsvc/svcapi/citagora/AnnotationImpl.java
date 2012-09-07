package org.idiginfo.docsvc.svcapi.citagora;

import java.util.Date;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Person;

public class AnnotationImpl extends CitagoraObjectImpl implements Annotation {

	CitagoraObject target;
	String characterEncoding;
	String chars;
	Person annotator;
	Date annotated;

	public CitagoraObject getTarget() {
		return target;
	}

	public void setTarget(CitagoraObject target) {
		this.target = target;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public String getChars() {
		return chars;
	}

	public void setChars(String chars) {
		this.chars = chars;
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
}
