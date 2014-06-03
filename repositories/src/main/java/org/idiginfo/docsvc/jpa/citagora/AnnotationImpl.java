package org.idiginfo.docsvc.jpa.citagora;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.Person;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence Annotation object
 * 
 * @author griccardi
 * 
 */

@Entity
@Table(name = "annotations")
abstract public class AnnotationImpl extends CitagoraObjectImpl implements
		Annotation {

	@ManyToOne(targetEntity = PersonImpl.class, cascade = CascadeType.ALL)
	@SerializedName("annotator")
	Person annotator;
	@Embedded
	AnnotationBodyImpl body;
	@SerializedName("modelVersion")
	String modelVersion;
	@SerializedName("specifier")
	String specifier;
	@SerializedName("tags")
	String tags;
	@Column(length = 2000)
	@SerializedName("context")
	String context;

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
		// TODO check this field
		// List<Annotation> annotators = annotator.get;
		// if (annotator != null && annotator != this.annotator) {
		//
		// }
		// this.annotator = annotator;
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

	public String getSpecifier() {
		return specifier;
	}

	public void setSpecifier(String specifier) {
		this.specifier = specifier;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

}
