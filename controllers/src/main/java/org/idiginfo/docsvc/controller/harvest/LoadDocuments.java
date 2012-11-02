package org.idiginfo.docsvc.controller.harvest;

import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.Documents;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

public class LoadDocuments {

    CitagoraFactoryImpl factory = new CitagoraFactoryImpl();
    MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();

    List<Container> load(CitagoraObject containerFields, Documents documents) {
	List<Container> containers = new Vector<Container>();
	if (documents == null)
	    return null;
	Iterator<Document> documentIterator = documents.iterator();
	while (documentIterator.hasNext()) {
	    Container container = load(containerFields, documentIterator.next());
	    containers.add(container);
	}
	return containers;

    }

    Container load(CitagoraObject containerFields, Document document) {
	boolean localTransaction = false;
	List<Reference> ref = factory.findReferences(document.getDoi());
	if (ref!=null&&ref.size()>0) {
	    return null; // there is already a document
	}
	if (!factory.isTransactionActive()) {
	    factory.openTransaction();
	    localTransaction = true;
	}
	Container container = documentMapper.createContainer(containerFields, document);
	if (localTransaction) {
	    factory.commitTransaction();
	}
	return container;
    }

    public String writeCitagoraRdf(UriObject document, String version, int level) {
	MapCitagoraObject mapper = new MapCitagoraObject();
	mapper.add(document, level);
	Model model = mapper.getModel();
	StringWriter out = new StringWriter();
	model.write(out, version);
	return out.toString();
    }

    public CitagoraFactory getFactory() {
        return factory;
    }
}
