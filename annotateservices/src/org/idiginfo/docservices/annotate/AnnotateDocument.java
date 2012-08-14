package org.idiginfo.docservices.annotate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.idiginfo.docservices.model.Annotation;
import org.idiginfo.docservices.model.Document;

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
}
