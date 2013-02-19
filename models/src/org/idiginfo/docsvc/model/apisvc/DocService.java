package org.idiginfo.docsvc.model.apisvc;

public interface DocService {

    public String format(String content);

    public Document getDocument(ApiParams params);

    public Documents getDocuments(ApiParams params);

    public Document getAnnotations(ApiParams params);

    public Document getAnnotations(Document document);

}
