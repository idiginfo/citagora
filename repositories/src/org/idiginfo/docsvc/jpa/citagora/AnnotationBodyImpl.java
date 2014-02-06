package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.idiginfo.docsvc.model.citagora.AnnotationBody;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.apisvc.GsonTransient;

import com.google.gson.annotations.SerializedName;

/**
 * Class to implement the Citagora persistence AnnotationBody object
 * 
 * @author griccardi
 * 
 */

@Embeddable
public class AnnotationBodyImpl implements AnnotationBody {

	@SerializedName("characterEncoding")
	String characterEncoding;
	@Column(length = 2000)
	@SerializedName("chars")
	String chars;

	@Transient
	@GsonTransient
	static int objectId = 0;
	@Transient
	@GsonTransient
	transient int myId = objectId++;
	@Transient
	@GsonTransient
	transient String myCollection = AnnotationBody.COLLECTION;
	@Transient
	@SerializedName("type")
	String type;
	@Transient
	String id;
	@Transient
	@SerializedName("uri")
	String uri;

	public AnnotationBodyImpl() {
		setType(AnnotationBody.TYPE);
		// setCollection(AnnotationBody.COLLECTION);
		// initId();
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

	@Override
	public Integer getMyId() {
		return myId;
	}

	public void setMyId(int myId) {
		this.myId = myId;
	}

}
