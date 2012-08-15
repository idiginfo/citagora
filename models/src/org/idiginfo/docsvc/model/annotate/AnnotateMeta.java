package org.idiginfo.docsvc.model.annotate;

import java.util.Date;

import org.idiginfo.docsvc.model.model.Annotation;
import org.idiginfo.docsvc.model.model.Document;

public class AnnotateMeta implements Document {
	String code;
	String date;
	String name;
	String owner;
	String type;
	String parentId;
	String notes; // may be string?
	String title;
	String created;
	String tags;
	String pages;
	String cost;
	String srcext;
	String srcname;
	String srcsize;
	String imageformat;
	String imagequant;
	String thumb;
	String hasindex;
	String tinycode;
	String authors;

	static class Reader {
		String email;
		String role;
		String sig;
	}

	Reader[] readers;
	String status;

	static class Payment {
		String date;
		String type;
		String cost;
	}

	Payment[] payments;
	String expiry;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getSrcext() {
		return srcext;
	}
	public void setSrcext(String srcext) {
		this.srcext = srcext;
	}
	public String getSrcname() {
		return srcname;
	}
	public void setSrcname(String srcname) {
		this.srcname = srcname;
	}
	public String getSrcsize() {
		return srcsize;
	}
	public void setSrcsize(String srcsize) {
		this.srcsize = srcsize;
	}
	public String getImageformat() {
		return imageformat;
	}
	public void setImageformat(String imageformat) {
		this.imageformat = imageformat;
	}
	public String getImagequant() {
		return imagequant;
	}
	public void setImagequant(String imagequant) {
		this.imagequant = imagequant;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getHasindex() {
		return hasindex;
	}
	public void setHasindex(String hasindex) {
		this.hasindex = hasindex;
	}
	public String getTinycode() {
		return tinycode;
	}
	public void setTinycode(String tinycode) {
		this.tinycode = tinycode;
	}
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}
	public Reader[] getReaders() {
		return readers;
	}
	public void setReaders(Reader[] readers) {
		this.readers = readers;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Payment[] getPayments() {
		return payments;
	}
	public void setPayments(Payment[] payments) {
		this.payments = payments;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
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
	public Date getDateObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDate(Date date) {
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
		return null;
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
	public String getDoi() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getGUID() {
		// TODO Auto-generated method stub
		return null;
	}


}
