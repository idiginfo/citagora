package org.idiginfo.docsvc.model.citagora;


public interface Tag extends Annotation {

    CitagoraDocument getDocumentTagged(); // same as getTarget

    void setDocumentTagged(CitagoraDocument document);
}
