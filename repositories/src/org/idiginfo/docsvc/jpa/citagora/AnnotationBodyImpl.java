package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;

@Embeddable
public class AnnotationBodyImpl implements AnnotationBody {

    String characterEncoding;
    String chars;

    @Transient
    static int objectId = 0;
    @Transient
    transient int myId = objectId++;
    @Transient
    transient String myCollection = AnnotationBody.COLLECTION;
    @Transient
    String type;
    @Transient
    String id;
    @Transient
    String uri;

    public AnnotationBodyImpl() {
	setType(AnnotationBody.TYPE);
	// setCollection(AnnotationBody.COLLECTION);
	//initId();
    }

    public String getType() {
	return type;
    }

    public static String makeId(String collection, int myId) {
	String id = CitagoraObject.NAMESPACE + collection + "/" + myId;
	return id;
    }

    public void initId() {
	id = makeId(myCollection, myId);
	if (uri == null)
	    uri = id;
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

    @Override
    public String getUri() {
	return uri;
    }

}
