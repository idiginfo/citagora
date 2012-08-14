package org.idiginfo.docservices.scopus;

import com.google.api.client.util.Key;

public class ScopusLink {
	@Key("@href")
	String href; //: "http://www.sciencedirect.com/science/article/pii/S0022395608001143",
    @Key("@rel")
    String rel; //: "scidir"
}
