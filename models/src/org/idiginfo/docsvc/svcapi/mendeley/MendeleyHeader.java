package org.idiginfo.docsvc.svcapi.mendeley;

import org.idiginfo.docsvc.svcapi.mendeley.MendeleyRecord.Author;

import com.google.gson.annotations.SerializedName;

/**
 * Class that implements the Mendeley "search" results Header
 *  containing the uuid necessary for retrieval of the "details" 
 * 
 * @author sflager
 * 
 */

public class MendeleyHeader {
	@SerializedName("uuid")
	String uuid;
	@SerializedName("title")
	String title;
	@SerializedName("publication_outlet")
	String publicationoutlet;
	@SerializedName("year")
	String year;
	
	@SerializedName("mendeley_url")
	String mendeleyurl;
	@SerializedName("doi")
	String doi;
	@SerializedName("authors")
	Author[] authors;

	public String getUuid() {
		return uuid;
	}
	public String getTitle() {
		return title;
	}
	public String getPublicationoutlet() {
		return publicationoutlet;
	}
	public String getYear() {
		return year;
	}
	public String getMendeleyurl() {
		return mendeleyurl;
	}
	public String getDoi() {
		return doi;
	}
	public Author[] getAuthors() {
		return authors;
	}
	
}
