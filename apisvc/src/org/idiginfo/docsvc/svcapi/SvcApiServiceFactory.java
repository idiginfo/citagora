package org.idiginfo.docsvc.svcapi;

import java.util.HashMap;
import java.util.Map;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiHarvest;
import org.idiginfo.docsvc.model.harvest.ApiLoad;
import org.idiginfo.docsvc.model.harvest.ApiSplit;
import org.idiginfo.docsvc.svcapi.altmetric.AltmetricApiParams;
import org.idiginfo.docsvc.svcapi.altmetric.AltmetricService;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateApiParams;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefApiParams;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefRdfService;
import org.idiginfo.docsvc.svcapi.entrez.EntrezApiParams;
import org.idiginfo.docsvc.svcapi.entrez.EntrezService;
import org.idiginfo.docsvc.svcapi.harvest.MasSplit;
import org.idiginfo.docsvc.svcapi.harvest.MendeleyHarvest;
import org.idiginfo.docsvc.svcapi.harvest.MendeleyLoad;
import org.idiginfo.docsvc.svcapi.harvest.SpringerHarvest;
import org.idiginfo.docsvc.svcapi.harvest.SpringerLoad;
import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyApiParams;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;
import org.idiginfo.docsvc.svcapi.msrc.MsrcApiParams;
import org.idiginfo.docsvc.svcapi.msrc.MsrcService;
import org.idiginfo.docsvc.svcapi.nature.NatureApiParams;
import org.idiginfo.docsvc.svcapi.nature.NatureService;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseApiParams;
import org.idiginfo.docsvc.svcapi.sciverse.SciVerseService;
import org.idiginfo.docsvc.svcapi.springer.SpringerApiParams;
import org.idiginfo.docsvc.svcapi.springer.SpringerService;

/**
 * Class that creates objects that implement the annotation interfaces We may
 * want to replace this static strategy with a dynamic service registration, as
 * with JDBC
 * 
 * @author griccardi
 * 
 */
public class SvcApiServiceFactory extends ServiceFactory {

	// services is used to keep a copy of each service available for shared use.
	Map<String, DocService> services;

	void initializeServices() {
		services = new HashMap<String, DocService>();
		for (String serviceName : SERVICE_COLLECTIONS) {
			services.put(serviceName, createService(serviceName));
		}
	}

	/**
	 * Return the shared service object. Be careful that any state is managed.
	 * 
	 * Not thread safe
	 * 
	 * @param collection
	 * @return
	 */
	public DocService getSharedService(String collection) {
		if (services == null)
			initializeServices();
		return services.get(collection);
	}

	/**
	 * create a new AnnotationService for the collection
	 * 
	 * @param collection
	 * @return
	 */
	public DocService createService(String collection) {
		if (collection == null)
			return null;
		if (collection.equals(COLLECTION_ANNOTATE)) {
			return new AnnotateService();
		}
		if (collection.equals(COLLECTION_SPRINGER)) {
			return new SpringerService();
		}
		if (collection.equals(COLLECTION_ELSEVIER)) {
			return new SciVerseService();
		}
		if (collection.equals(COLLECTION_ALTMETRIC)) {
			return new AltmetricService();
		}
		if (collection.equals(COLLECTION_CROSSREF)) {
			return new CrossrefRdfService();
		}
		if (collection.equals(COLLECTION_MAS)) {
			return new MasService();
		}
		if (collection.equals(COLLECTION_MENDELEY)) {
			return new MendeleyService();
		}
		if (collection.equals(COLLECTION_MSRC)) {
			return new MsrcService();
		}
		if (collection.equals(COLLECTION_NATURE)) {
			return new NatureService();
		}
		if (collection.equals(COLLECTION_ENTREZ)) {
			return new EntrezService();
		}
		System.err.println("unrecognized service: " + collection);
		return null;
	}

	/**
	 * Create a new ApiParams object of the proper type for the collection
	 * 
	 * @param collection
	 * @return
	 */
	public ApiParams createApiParams(String collection) {
		if (collection != null) {
			if (collection.equals(COLLECTION_ANNOTATE)) {
				return new AnnotateApiParams();
			}
			if (collection.equals(COLLECTION_SPRINGER)) {
				return new SpringerApiParams();
			}
			if (collection.equals(COLLECTION_ELSEVIER)) {
				return new SciVerseApiParams();
			}
			if (collection.equals(COLLECTION_ALTMETRIC)) {
				return new AltmetricApiParams();
			}
			if (collection.equals(COLLECTION_CROSSREF)) {
				return new CrossrefApiParams();
			}
			if (collection.equals(COLLECTION_MAS)) {
				return new MasApiParams();
			}
			if (collection.equals(COLLECTION_MENDELEY)) {
				return new MendeleyApiParams();
			}
			if (collection.equals(COLLECTION_MSRC)) {
				return new MsrcApiParams();
			}
			if (collection.equals(COLLECTION_NATURE)) {
				return new NatureApiParams();
			}
			if (collection.equals(COLLECTION_ENTREZ)) {
				return new EntrezApiParams();
			}

			// if (collection.equals(COLLECTION_CITAGORA)){
			// return new CitagoraApiParams();
			// }
		}
		return new BaseApiParams();
	}

	@Override
	public ApiHarvest createApiHarvest(String collection) {
		if (collection == null)
			return null;
		if (collection.equals(COLLECTION_SPRINGER)) {
			return new SpringerHarvest();
		}

		if (collection.equals(COLLECTION_MENDELEY)) {
			return new MendeleyHarvest();
		}
		return null;
	}

	@Override
	public ApiSplit createApiSplit(String collection) {
		if (collection == null)
			return null;
		if (collection.equals(ServiceFactory.COLLECTION_MAS)) {
			return new MasSplit();
		}
		return null;
	}

	@Override
	public ApiLoad createApiLoad(String collection) {
		if (collection == null)
			return null;
		if (collection.equals(COLLECTION_SPRINGER)) {
			return new SpringerLoad();
		}
		if (collection.equals(COLLECTION_MENDELEY)) {
			return new MendeleyLoad();
		}
		return null;
	}
}
