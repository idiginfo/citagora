package org.idiginfo.docsvc.svcapi.mapping;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.svcapi.springer.SpringerRecord;
import org.modelmapper.PropertyMap;

/**
 * Class to map Springer object onto Document object
 * 
 * @author griccardi
 * 
 */

public class SpringerMap extends PropertyMap<SpringerRecord, Document> {
	@Override
	protected void configure() {
		// map().setName(source.getName());
	}
}
