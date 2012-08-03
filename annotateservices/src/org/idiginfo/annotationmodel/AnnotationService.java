package org.idiginfo.annotationmodel;

public interface AnnotationService {

	public String format(String content);

	public Users getUsers(ApiParams params);

	public Document getDocument(ApiParams params);

	public Document getDocument(String code, String date);

	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes);

	public Documents getDocuments(ApiParams params);

	public Documents getDocuments(String user);

	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes);

	// public Document getNotes(String documentCode);

	public Document getAnnotations(ApiParams params);

	public Document getAnnotations(Document document);

	public Document getAnnotations(String code, String date);

}
