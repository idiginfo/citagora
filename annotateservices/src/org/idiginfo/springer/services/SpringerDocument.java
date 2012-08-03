package org.idiginfo.springer.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.idiginfo.annotationmodel.Annotation;
import org.idiginfo.annotationmodel.Document;

import com.google.gson.annotations.SerializedName;

public class SpringerDocument implements Document {

	class FullTextRetrievalResponse {
		@SerializedName("coredata")
		public CoreData coreData;
	}

	class CoreData {
		@SerializedName("prism:url")
		String url; // "http://api.elsevier.com/content/article/DOI:10.1016/j.jpsychires.2008.05.001",

		SpringerLink link;

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

	@SerializedName("full-text-retrieval-response")
	FullTextRetrievalResponse fullTextRetrievalResponse;

	@Override
	public String getId() {
		return fullTextRetrievalResponse.coreData.id;
	}

	@Override
	public void setId(String id) {
		fullTextRetrievalResponse.coreData.id = id;

	}

	@Override
	public String getDate() {
		return fullTextRetrievalResponse.coreData.coverDate;
	}

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

	@Override
	public Date getDateObject() {
		// try {
		// return formatter.parse(date);
		// } catch (ParseException e) {
		// }
		return null;
	}

	public void setDate(String date) {
		fullTextRetrievalResponse.coreData.coverDate = date;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getOwner() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOwner(String owner) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public Document getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParent(Document parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public Annotation[] getAnnotations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		return fullTextRetrievalResponse.coreData.title;
	}

	@Override
	public void setTitle(String title) {
		fullTextRetrievalResponse.coreData.title = title;

	}

	@Override
	public String getAuthors() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public void setDate(Date date) {
		// TODO Auto-generated method stub

	}

	public String getPubName() {
		return fullTextRetrievalResponse.coreData.pubName;
	}

}
