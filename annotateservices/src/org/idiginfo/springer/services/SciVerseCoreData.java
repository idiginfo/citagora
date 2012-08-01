package org.idiginfo.springer.services;

import com.google.gson.annotations.SerializedName;

public class SciVerseCoreData {
	 @SerializedName("prism:url")
	String url;// :
				// "http://api.elsevier.com/content/article/DOI:10.1016/j.jpsychires.2008.05.001",
	
	SciVerseLink link;

	 @SerializedName("dc:identifier")
	String id; // : "DOI:10.1016/j.jpsychires.2008.05.001",
	 @SerializedName("prism:doi")
	String doi; // : "10.1016/j.jpsychires.2008.05.001",
	 @SerializedName("pii")
	String pii; // : "S0022-3956(08)00114-3",
	 @SerializedName("dc:title")
	String title; // "Substance use disorders and ...",
	 @SerializedName("prism:publicationName")
	String pubName; // : "Journal of Psychiatric Research",
	 @SerializedName("prism:aggregationType")
	String aggregationType; // : "Journal",
	 @SerializedName("prism:issn")
	String issn; // : "00223956",
	 @SerializedName("prism:coverDate")
	String coverDate;// : "2009-01-31"
}
