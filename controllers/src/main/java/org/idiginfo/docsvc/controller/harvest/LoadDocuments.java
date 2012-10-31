package org.idiginfo.docsvc.controller.harvest;

import java.io.StringWriter;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

public class LoadDocuments {

    CitagoraFactoryImpl factory = new CitagoraFactoryImpl();


    Container loadDocument(Document document) {
	factory.openTransaction();
	MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();
	Container container = documentMapper
		.createContainer(document);
	factory.commitTransaction();
	return container;
    }

    public String writeCitagoraRdf(UriObject document, String version) {
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document);
	Model model = mapper.getModel();
	StringWriter out = new StringWriter();
	model.write(out, version);
	return out.toString();
    }
}
