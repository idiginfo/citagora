package org.idiginfo.docsvc.svcapi.scopus;

import com.google.api.client.util.Key;

/**
 * Class to facilitate Api Client access to Scopus
 * 
 * @author griccardi
 * 
 */

public class ScopusLink {
	@Key("@href")
	String href; // : "http://www.sciencedirect.com/science/article/pii/S0022395608001143",
	@Key("@rel")
	String rel; // : "scidir"
}
