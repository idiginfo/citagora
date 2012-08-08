package org.idiginfo.springer.services;

import java.util.Date;

import org.idiginfo.annotationmodel.Annotation;
import org.idiginfo.annotationmodel.Document;
import org.apache.commons.lang.StringUtils;

public class SpringerRecord implements Document {
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

		public String toString() {
			return creator;
		}
	}

	@Override
	public String getId() {
		return doi;
	}

	@Override
	public void setId(String id) {
		doi = id;
	}

	@Override
	public String getDate() {
		return null;
	}

	@Override
	public Date getDateObject() {
		return null;
	}

	@Override
	public void setDate(Date date) {
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public String getOwner() {
		return null;
	}

	@Override
	public void setOwner(String owner) {
	}

	@Override
	public String getType() {
		return null;
	}

	@Override
	public void setType(String type) {
	}

	@Override
	public Document getParent() {
		return null;
	}

	@Override
	public void setParent(Document parent) {
	}

	@Override
	public Annotation[] getAnnotations() {
		return null;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getAuthors() {
		String authors = StringUtils.join(creators, ", ");
		return authors;
	}

	@Override
	public void setAuthors(String authors) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumAnnotations() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Annotation getAnnotation(int i) {
		// TODO Auto-generated method stub
		return null;
	}

}
