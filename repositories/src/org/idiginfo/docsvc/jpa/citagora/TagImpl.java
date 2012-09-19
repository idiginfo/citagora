package org.idiginfo.docsvc.jpa.citagora;

import javax.persistence.Entity;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Tag;
import org.idiginfo.docsvc.svcapi.citagora.AnnotationImpl;

@Entity(name = "tags")
public class TagImpl extends AnnotationImpl implements Tag {

    public static final String TYPE = "oax:tag";

    public TagImpl() {
	type = Tag.TYPE;
	setCollection(Tag.COLLECTION);
	initId();
    }

    @Override
    public CitagoraDocument getDocumentTagged() {
	return (CitagoraDocument) getTarget();
    }
}
