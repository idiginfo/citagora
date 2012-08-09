package org.idiginfo.docservices.annotate;

import java.util.Date;

import org.idiginfo.docservices.annotate.AnnotateMeta.Payment;
import org.idiginfo.docservices.annotate.AnnotateMeta.Reader;
import org.idiginfo.docservices.model.Annotation;
import org.idiginfo.docservices.model.Document;

public class AnnotateDocumentNotes implements Document {
	public AnnotateMeta meta;
	public AnnotateNote[] notes;

	public AnnotateNote[] getNotes() {
		// TODO Auto-generated method stub
		return notes;
	}

	public String getCode() {
		return meta.code;
	}

	public void setCode(String code) {
		meta.code = code;
	}

	public String getDate() {
		return meta.date;
	}

	public void setDate(String date) {
		meta.date = date;
	}

	public String getName() {
		return meta.name;
	}

	public void setName(String name) {
		meta.name = name;
	}

	public String getOwner() {
		return meta.owner;
	}

	public void setOwner(String owner) {
		meta.owner = owner;
	}

	public String getType() {
		return meta.type;
	}

	public void setType(String type) {
		meta.type = type;
	}

	public String getParentId() {
		return meta.parentId;
	}

	public void setParentId(String parentId) {
		meta.parentId = parentId;
	}

	public String getTitle() {
		return meta.title;
	}

	public void setTitle(String title) {
		meta.title = title;
	}

	public String getCreated() {
		return meta.created;
	}

	public void setCreated(String created) {
		meta.created = created;
	}

	public String getTags() {
		return meta.tags;
	}

	public void setTags(String tags) {
		meta.tags = tags;
	}

	public String getPages() {
		return meta.pages;
	}

	public void setPages(String pages) {
		meta.pages = pages;
	}

	public String getCost() {
		return meta.cost;
	}

	public void setCost(String cost) {
		meta.cost = cost;
	}

	public String getSrcext() {
		return meta.srcext;
	}

	public void setSrcext(String srcext) {
		meta.srcext = srcext;
	}

	public String getSrcname() {
		return meta.srcname;
	}

	public void setSrcname(String srcname) {
		meta.srcname = srcname;
	}

	public String getSrcsize() {
		return meta.srcsize;
	}

	public void setSrcsize(String srcsize) {
		meta.srcsize = srcsize;
	}

	public String getImageformat() {
		return meta.imageformat;
	}

	public void setImageformat(String imageformat) {
		meta.imageformat = imageformat;
	}

	public String getImagequant() {
		return meta.imagequant;
	}

	public void setImagequant(String imagequant) {
		meta.imagequant = imagequant;
	}

	public String getThumb() {
		return meta.thumb;
	}

	public void setThumb(String thumb) {
		meta.thumb = thumb;
	}

	public String getHasindex() {
		return meta.hasindex;
	}

	public void setHasindex(String hasindex) {
		meta.hasindex = hasindex;
	}

	public String getTinycode() {
		return meta.tinycode;
	}

	public void setTinycode(String tinycode) {
		meta.tinycode = tinycode;
	}

	public String getAuthors() {
		return meta.authors;
	}

	public void setAuthors(String authors) {
		meta.authors = authors;
	}

	public Reader[] getReaders() {
		return meta.readers;
	}

	public void setReaders(Reader[] readers) {
		meta.readers = readers;
	}

	public String getStatus() {
		return meta.status;
	}

	public void setStatus(String status) {
		meta.status = status;
	}

	public Payment[] getPayments() {
		return meta.payments;
	}

	public void setPayments(Payment[] payments) {
		meta.payments = payments;
	}

	public String getExpiry() {
		return meta.expiry;
	}

	public void setExpiry(String expiry) {
		meta.expiry = expiry;
	}

	@Override
	public String getId() {
		return meta.code;
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
		if (meta == null) return null;
		return meta.getParent();
	}

	@Override
	public void setParent(Document parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public Annotation[] getAnnotations() {
		return notes;
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

}
