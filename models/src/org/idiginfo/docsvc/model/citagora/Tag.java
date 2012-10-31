package org.idiginfo.docsvc.model.citagora;

public interface Tag extends Annotation {

    public static final String TYPE = "oax:tag";

    Container getDocumentTagged(); // same as getTarget

    void setDocumentTagged(Container document);
}
