package org.idiginfo.docsvc.model;

import java.util.HashMap;
import java.util.Map;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.BaseApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.svcapi.altmetric.AltmetricService;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateApiParams;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;
import org.idiginfo.docsvc.svcapi.crossref.CrossrefRdfService;
import org.idiginfo.docsvc.svcapi.entrez.EntrezService;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;
import org.idiginfo.docsvc.svcapi.msrc.MsrcService;
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
public class ServiceFactory {

	// Defined collections
	public static final String COLLECTION_ANNOTATE = "annotate";
	public static final String COLLECTION_SPRINGER = "springer";
	public static final String COLLECTION_ELSEVIER = "elsevier";
	public static final String COLLECTION_ALTMETRIC = "altmetric";
	public static final String COLLECTION_CROSSREF = "crossref";
	public static final String COLLECTION_MAS = "mas";
	public static final String COLLECTION_ENTREZ = "entrez";
	public static final String COLLECTION_MSRC = "msrc";
	public static final String COLLECTION_NATURE = "nature";
	public static final String COLLECTION_MENDELEY = "mendeley";
	public static final String[] SERVICE_COLLECTIONS = { COLLECTION_ANNOTATE,
			COLLECTION_SPRINGER, COLLECTION_ELSEVIER, COLLECTION_ALTMETRIC,
			COLLECTION_CROSSREF, COLLECTION_MAS, COLLECTION_ENTREZ,
			COLLECTION_MENDELEY, COLLECTION_MSRC, COLLECTION_NATURE };

	// non-service collections
	public static final String COLLECTION_CITAGORA = "citagora";

	// services is used to keep a copy of each service available for shared use.
	static Map<String, DocService> services;

	static void initializeServices() {
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
	public static DocService getSharedService(String collection) {
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
	public static DocService createService(String collection) {
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
	public static ApiParams createApiParams(String collection) {
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
			// if (collection.equals(COLLECTION_CITAGORA)){
			// return new CitagoraApiParams();
			// }
		}
		return new BaseApiParams();
	}
}
