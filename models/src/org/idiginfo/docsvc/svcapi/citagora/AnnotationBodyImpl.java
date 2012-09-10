package org.idiginfo.docsvc.svcapi.citagora;

import org.idiginfo.docsvc.model.citagora.AnnotationBody;

public class AnnotationBodyImpl extends CitagoraObjectImpl implements
		AnnotationBody {

	public static final String TYPE = "od:nbody";

	public AnnotationBodyImpl() {
		setType(AnnotationBody.TYPE);
		setCollection(AnnotationBody.COLLECTION);
		initId();
	}

	String characterEncoding;

	String chars;

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

}
