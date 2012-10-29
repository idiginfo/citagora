package org.idiginfo.docsvc.svcapi.sciverse;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class ScopusAuthUrl extends GenericUrl {

    public ScopusAuthUrl() {
	super(SciVerseApiParams.AUTH_URL);
    }

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

}
