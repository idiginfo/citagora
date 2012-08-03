package org.idiginfo.springer.services;

public class SpringerRecord {
	String identifier;
	String title;
	Creator[] creators;
	String publicationName;
	String issn;
	String isbn;
	String doi;
	String publisher;
	String publicationDate;
	String volume;
	String number;
	String startingPage;
	String url;
	String copyright;

	class Creator {
		String creator;
	}

}
