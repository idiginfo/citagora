package org.idiginfo.docsvc.svcapi.citagora;

import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;

public class AnnotationBodyImpl implements AnnotationBody {

    static int objectId = 0;
    transient int myId = objectId++;
    transient String myCollection = AnnotationBody.COLLECTION;

    String type;
    String id;
    String uri;
    String characterEncoding;
    String chars;

    public AnnotationBodyImpl() {
	setType(AnnotationBody.TYPE);
	// setCollection(AnnotationBody.COLLECTION);
	initId();
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
