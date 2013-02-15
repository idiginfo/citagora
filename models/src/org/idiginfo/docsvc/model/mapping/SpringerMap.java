package org.idiginfo.docsvc.model.mapping;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.springer.SpringerRecord;
import org.modelmapper.PropertyMap;

public class SpringerMap extends PropertyMap<SpringerRecord, Document> {
	@Override
	protected void configure() {
		//map().setName(source.getName());
	}
}
