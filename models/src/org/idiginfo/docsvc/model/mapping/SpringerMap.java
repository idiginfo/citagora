package org.idiginfo.docsvc.model.mapping;

import org.idiginfo.docsvc.model.model.BaseDocument;
import org.idiginfo.docsvc.model.springer.SpringerRecord;
import org.modelmapper.PropertyMap;

public class SpringerMap extends PropertyMap<SpringerRecord, BaseDocument> {
	@Override
	protected void configure() {
		map().setName(source.getName());
	}
}
