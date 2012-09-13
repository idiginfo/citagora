package org.idiginfo.docsvc.svcapi.citagora;

import org.idiginfo.docsvc.model.citagora.AnnotationBody;

public class AnnotationBodyImpl  implements
		AnnotationBody {

	public static final String TYPE = "od:nbody";
	String type;
	String id;

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

	public AnnotationBodyImpl() {
		setType(AnnotationBody.TYPE);
		//setCollection(AnnotationBody.COLLECTION);
		initId();
	}

	private void initId() {
	    id = "urn:body id";
	    
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

	@Override
	public String getUri() {
	    // TODO Auto-generated method stub
	    return id;
	}

}
