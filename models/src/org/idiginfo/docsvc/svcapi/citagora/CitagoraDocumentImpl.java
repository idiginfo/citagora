package org.idiginfo.docsvc.svcapi.citagora;

import java.util.List;

import org.idiginfo.docsvc.model.citagora.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraDocument;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Reference;

public class CitagoraDocumentImpl extends CitagoraObjectImpl implements
		CitagoraDocument {

	Reference isAbout;

	@Override
	public List<Annotation> getRatings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getTags() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Annotation> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	public Reference getIsAbout() {
		return isAbout;
	}

	public void setIsAbout(Reference isAbout) {
		this.isAbout = isAbout;
	}

}
