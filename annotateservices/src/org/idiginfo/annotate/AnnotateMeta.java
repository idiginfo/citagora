package org.idiginfo.annotate;

public class AnnotateMeta {
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

}
