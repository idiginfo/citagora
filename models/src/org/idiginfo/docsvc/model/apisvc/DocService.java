package org.idiginfo.docsvc.model.apisvc;

import java.util.List;

/**
 * Interface to implement the DocService object
 * 
 * @author griccardi
 * 
 */

public interface DocService {

	public String format(String content);

	public Document getDocument(ApiParams params);

	public List<? extends Document> getDocuments(ApiParams params);

	public Document getAnnotations(ApiParams params);

	public Document getAnnotations(Document document);

}
