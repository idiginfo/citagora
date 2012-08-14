package org.idiginfo.docservices.model;

import java.util.Date;

public interface Document {

	public String getId();

	public void setId(String id);

	public String getDate();

	public Date getDateObject();

	public void setDate(Date date);

	public String getName();

	public void setName(String name);

	public String getOwner();

	public void setOwner(String owner);

	public String getType();

	public void setType(String type);

	public Document getParent();

	public void setParent(Document parent);

	public Annotation[] getAnnotations();

	public String getTitle();

	public void setTitle(String title);

	public String getAuthors();

	public void setAuthors(String authors);

	public int getNumAnnotations();

	public Annotation getAnnotation(int i);

	public String getDoi();

	public String getGUID();
}
