package org.idiginfo.docsvc.svcapi.annotate.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.idiginfo.docsvc.model.model.Annotation;
import org.idiginfo.docsvc.model.model.Document;

public class AnnotateDocument implements Document {
	String code;
	String date;
	String name;
	String owner;
	String type;
	String parentId;
	AnnotateNote[] notes;
	AnnotateMeta meta;

	AnnotateNote getNote(int i) {
		if (notes == null || i < 0 || i >= notes.length) return null;
		// TODO exception!
		return notes[i];
	}

	AnnotateMeta getMeta() {
		return meta;

	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String getId() {
		return code;
	}

	@Override
	public void setId(String id) {
		setCode(id);
	}

	@Override
	public void setDate(Date date) {
		if (meta != null) meta.setDate(date);
	}

	@Override
	public Document getParent() {
		if (meta != null) return meta.getParent();
		return null;
	}

	@Override
	public void setParent(Document parent) {
		if (meta != null) meta.setParent(parent);

	}

	@Override
	public Annotation[] getAnnotations() {
		return notes;
	}

	@Override
	public String getTitle() {
		if (meta != null) return meta.getTitle();
		return name;
	}

	@Override
	public void setTitle(String title) {
		if (meta != null) meta.setTitle(title);
	}

	static DateFormat formatter = new SimpleDateFormat("yyyy-MM.dd");

	@Override
	public Date getDateObject() {
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
		}
		return null;
	}

	@Override
	public int getNumAnnotations() {
		if (notes == null) return 0;
		return notes.length;
	}

	@Override
	public Annotation getAnnotation(int i) {
		if (notes == null || i < 0 || i >= notes.length) return null;
		return notes[i];
	}

	@Override
	public String getAuthors() {
		if (meta != null) return meta.getAuthors();
		return null;
	}

	@Override
	public void setAuthors(String authors) {
		if (meta != null) meta.setAuthors(authors);
	}

	@Override
	public String getDoi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getGUID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIsbn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIssn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublicationName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVolume() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCopyright(String copyright) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsbn(String isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIssn(String issn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPublicationName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPublisher(String publisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVolume(String volume) {
		// TODO Auto-generated method stub
		
	}
}
