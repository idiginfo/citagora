package org.idiginfo.docsvc.harvest.load;

import java.io.StringWriter;
import java.util.List;
import java.util.Vector;

import org.idiginfo.docsvc.jpa.citagora.CitagoraFactoryImpl;
import org.idiginfo.docsvc.jpa.citagora.HarvestResultImpl;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraObject;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.citagora.HarvestResult;
import org.idiginfo.docsvc.model.citagora.Reference;
import org.idiginfo.docsvc.model.citagora.UriObject;
import org.idiginfo.docsvc.model.mapping.MapSvcapiToCitagora;
import org.idiginfo.docsvc.view.rdf.citagora.MapCitagoraObject;

import com.hp.hpl.jena.rdf.model.Model;

/**
 * Class to load Citagora objects into Persistence model
 * 
 * @author griccardi
 * 
 */

public class LoadDocuments {

	CitagoraFactoryImpl factory = new CitagoraFactoryImpl();
	MapSvcapiToCitagora documentMapper = new MapSvcapiToCitagora();

	public List<Container> load(Container containerFields,
			List<Document> documents) {
		List<Container> containers = new Vector<Container>();
		if (documents == null)
			return null;
		for (Document document : documents) {
			Container container = load(containerFields, document);
			containers.add(container);
		}
		return containers;
	}

	public Container load(Container containerFields, Document document) {
		boolean localTransaction = false;
		String doi = document.getDoi();
		String id = document.getId();
		String source = document.getSource();
		if (doi != null && !doi.startsWith("10.")) {
			// not a valid doi
			doi = null;
		}
		if (doi != null) {
			Reference ref = factory.findReferenceBySourceDoi(source, doi);
			if (ref != null) {
				System.out.println(" doi: " + doi + " already present");
				HarvestResult harvestResult = HarvestResultImpl
						.createHarvestResult(source, doi, ref,
								"duplicate entry", false);
				System.out.println("Harvest Result " + harvestResult.getMyId()
						+ " created");
				List<Container> containers = ref.getContainers();
				if (containers != null && containers.size() > 0)
					return containers.get(0); // there is already a document
				return null; // no container
			}
		}
		// TODO decide what to do with harvest result in absence of DOI
		String uri = document.getUri();
		if (uri != null && !uri.equals(doi)) {
			CitagoraObject obj = factory.findCitagoraObjectByURI(uri);
			if (obj != null) {
				System.out.println(" uri: " + uri + " already present");
				return null;
			}
		}
		if (!factory.isTransactionActive()) {
			factory.openTransaction();
			localTransaction = true;
		}
		Container container = documentMapper.createContainer(containerFields,
				document);
		HarvestResult harvestResult = HarvestResultImpl.createHarvestResult(
				source, id, container.getIsAbout(), "success", true);
		System.out.println(" uri: " + uri + " created");
		System.out.println("Harvest Result " + harvestResult.getMyId()
				+ " created");
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

	public Reference loadDocument(Document fromDocument) {
		boolean localTransaction = false;
		if (!factory.isTransactionActive()) {
			factory.openTransaction();
			localTransaction = true;
		}

		Reference toReference = documentMapper.map(fromDocument);
		if (localTransaction) {
			factory.commitTransaction();
		}

		return toReference;
	}

	public MapSvcapiToCitagora getDocumentMapper() {
		return documentMapper;
	}

}
