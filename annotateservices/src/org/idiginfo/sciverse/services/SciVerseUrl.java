package org.idiginfo.sciverse.services;

import java.util.List;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class SciVerseUrl extends GenericUrl {

	public SciVerseUrl(String function) {
		super(SciVerseApiParams.API_URL + function);
	}

	@Key("view")
	protected String view;

	@Key
	List<String> field;

	// view, field

	/**
	 * Get the URL ready for execution
	 */
	public void prepare() {

	}

	public static boolean isError(String content) {
		if (content.startsWith("XXX")) {
			return true;
		}
		return false;
	}

	protected void mapParams(SciVerseApiParams params) {
		if (params == null) return;
		// TODO finish method
	}

}
