package org.idiginfo.docsvc.svcapi.springer;

import com.google.api.client.util.Key;

/**
 * Class to facilitate Api client access to Springer
 * 
 * @author griccardi
 * 
 */

public class SpringerLink {
	@Key("@href")
	String href; // :
					// "http://www.sciencedirect.com/science/article/pii/S0022395608001143",
	@Key("@rel")
	String rel; // : "scidir"
}
