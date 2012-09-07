package org.idiginfo.docsvc.svcapi.citagora;

import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;

public  class CitagoraObjectImpl implements CitagoraObject {

	String type;
	String id;
	String uri;
	String wasAttributedTo;
	Date created;
	String source;
	String rights;
	CitagoraAgent generator;
	Date generated;
	List<Annotation> annotations;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
		return annotations;
	}
}
