package org.idiginfo.docservices.model;

import java.util.Date;

public class BaseDocument implements Document {

	String id;
	Date date;
	String name;
	String owner;
	String type;
	BaseDocument parent;
	String notes; // may be string?
	String title;
	String created;
	String tags;
	Integer pages;
	String srcext;
	String srcname;
	String srcsize;
	String imageformat;
	String imagequant;
	String thumb;
	String hasindex;
	String tinycode;
	String authors;

	String status;
	String json;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDate() {
		//TODO return formatted date
		return null;
	}
	public void setDate(Date date) {
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
	public BaseDocument getParent() {
		return parent;
	}
	public void setParent(BaseDocument parent) {
		this.parent = parent;
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
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
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
	public Date getDateObject() {
		// TODO Auto-generated method stub
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
}