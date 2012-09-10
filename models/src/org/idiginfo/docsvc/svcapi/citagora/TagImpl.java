package org.idiginfo.docsvc.svcapi.citagora;

import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.Tag;

public class TagImpl extends AnnotationImpl implements Tag {

	public String TYPE = "oax:tag";

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
