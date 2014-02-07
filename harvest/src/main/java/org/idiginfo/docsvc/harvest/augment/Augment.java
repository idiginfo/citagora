package org.idiginfo.docsvc.harvest.augment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;
import org.idiginfo.docsvc.model.apisvc.Document;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Reference;

/**
 * Class to support fetching and loading from all sources
 * 
 * @author griccardi
 * 
 */

public class Augment {

	CitagoraFactory citagoraFactory = CitagoraFactory.getFactory();
	LoadDocuments loader = new LoadDocuments();

	public Map<String, Reference> fetchAllFromDoi(String doi) {
		Map<String, Reference> documentMap = new HashMap<String, Reference>();
		if (doi == null)
			return documentMap;
		List<Reference> references = citagoraFactory.findReferencesByDoi(doi);
		for (Reference ref : references) {
			documentMap.put(ref.getSource(), ref);
		}
		return documentMap;
	}

	public Map<String, Reference> addMissingSources(String doi) {
		Map<String, Reference> documentMap = fetchAllFromDoi(doi);
		ApiParams params = new BaseApiParams();
		params.setDoi(doi);
		for (String source : ServiceFactory.SERVICE_COLLECTIONS) {
			if (source.equals(ServiceFactory.COLLECTION_MSRC)) {
				// do not fetch from MSRC
				continue;
			}
			if (!documentMap.containsKey(source)) {
				Document newDoc = ServiceFactory.getFactory()
						.getSharedService(source).getDocument(params);
				if (newDoc == null) {
					// no document found by service
					citagoraFactory.createHarvestResult(source, "doi:" + doi,
							null, "no such document in service", false);
					continue;
				}
				// map newDoc to citagora reference
				// TODO connect this new document to an existing container
				Reference ref = loader.loadDocument(newDoc);
				citagoraFactory.createHarvestResult(source,
						"doi:" + doi, ref, "created by augment", true);

				documentMap.put(source, ref);
			}
		}
		return documentMap;
	}
}
