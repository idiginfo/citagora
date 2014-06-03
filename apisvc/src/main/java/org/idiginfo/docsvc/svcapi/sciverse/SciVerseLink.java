package org.idiginfo.docsvc.svcapi.sciverse;

import com.google.gson.annotations.SerializedName;

/**
 * Class to process API result for Sciverse
 * 
 * @author griccardi
 * 
 */

public class SciVerseLink {
	@SerializedName("@ref")
	String ref;
	@SerializedName("@href")
	String href;
	@SerializedName("@type")
	String type;

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
