package org.idiginfo.docsvc.view;

import java.io.StringWriter;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.view.rdf.BiboModel;
import org.idiginfo.docsvc.view.rdf.MapDocument;
import org.idiginfo.docsvc.view.rdf.MapDocuments;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;
import org.openjena.riot.RIOT;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 * Class to generate RDF output from Citagora objects
 * 
 * @author griccardi
 * 
 */

public class RdfWriter implements ObjectWriter {
	BiboModel biboModel = new BiboModel();
	Model model = biboModel.createSample();

	static {
		RIOT.init();// initialize the I/O tools
	}

	public static String writeContainer(Container document) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		mapper.addContainer(document, -1);
		Model model = mapper.getModel();
		return null;
	}

	@Override
	public String writeDocument(Document document) {
		Model model = ModelFactory.createDefaultModel();
		MapDocument mapDocument = new MapDocument();
		model = mapDocument.addDocument(model, document);
		return writeModel(model);
	}

	@Override
	public String writeDocuments(List<Document> documents) {
		Model model = ModelFactory.createDefaultModel();
		MapDocuments mapDocuments = new MapDocuments();
		model = mapDocuments.addDocuments(model, documents);
		return writeModel(model);
	}

	private String writeModel(Model model) {
		return writeModel(model, "RDF/XML");
		// return writeModel(model, "RDF/XML");
		// return writeModel(model, "Turtle");
	}

	private String writeModel(Model model, String version) {
		StringWriter out = new StringWriter();
		model.write(out, version);
		return out.toString();
	}

	@Override
	public String write(Object objects) {
		if (objects instanceof List<?>) {
			return writeDocuments((List<Document>) objects);
		}
		if (objects instanceof Document) {
			return writeDocument((Document) objects);
		}
		if (objects instanceof List) {
			return writeCitagora((List<?>) objects, null);
		}
		return null;
	}

	private String writeCitagora(List<?> objects, String version) {
		MapCitagoraObject mapper = new MapCitagoraObject();
		StringWriter out = new StringWriter();
		for (Object obj : objects) {
			if (obj instanceof CitagoraObject) {
				mapper.add((UriObject) obj, -1);
			}
		}
		mapper.getModel().write(out, version);
		return out.toString();
	}
}