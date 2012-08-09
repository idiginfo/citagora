package org.idiginfo.docservices;

import java.util.HashMap;
import java.util.Map;

import org.idiginfo.docservices.annotate.AnnotateApiParams;
import org.idiginfo.docservices.annotate.AnnotateService;
import org.idiginfo.docservices.model.AnnotationService;
import org.idiginfo.docservices.model.ApiParams;
import org.idiginfo.docservices.sciverse.SciVerseApiParams;
import org.idiginfo.docservices.sciverse.SciVerseService;
import org.idiginfo.docservices.springer.SpringerApiParams;
import org.idiginfo.docservices.springer.SpringerService;

/**
 * Class that creates objects that implement the annotation interfaces
 * 
 * @author griccardi
 * 
 */
public class AnnotationFactory {

	// Defined collections
	public static String COLLECTION_ANNOTATE = "annotate";
	public static String COLLECTION_SPRINGER = "springer";
	public static String COLLECTION_ELSEVIER = "elsevier";
	public static String[] COLLECTIONS = { COLLECTION_ANNOTATE,
			COLLECTION_SPRINGER, COLLECTION_ELSEVIER };

	// services is used to keep a copy of each service available for shared use.
	static Map<String, AnnotationService> services;

	static void initializeServices() {
		services = new HashMap<String, AnnotationService>();
		services.put(AnnotationFactory.COLLECTION_ANNOTATE, AnnotationFactory
				.createService(AnnotationFactory.COLLECTION_ANNOTATE));
		services.put(AnnotationFactory.COLLECTION_SPRINGER, AnnotationFactory
				.createService(AnnotationFactory.COLLECTION_SPRINGER));
		services.put(AnnotationFactory.COLLECTION_ELSEVIER, AnnotationFactory
				.createService(AnnotationFactory.COLLECTION_ELSEVIER));
	}

	/**
	 * Return the shared service object. Be careful that any state is managed.
	 * 
	 * Not thread safe
	 * 
	 * @param collection
	 * @return
	 */
	public static AnnotationService getSharedService(String collection) {
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
	public static AnnotationService createService(String collection) {
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
		return null;
	}

	/**
	 * Create a new ApiParams object of the proper type for the collection
	 * 
	 * @param collection
	 * @return
	 */
	public static ApiParams createApiParams(String collection) {
		if (collection == null)
			return null;
		if (collection.equals(COLLECTION_ANNOTATE)) {
			return new AnnotateApiParams();
		}
		if (collection.equals(COLLECTION_SPRINGER)) {
			return new SpringerApiParams();
		}
		if (collection.equals(COLLECTION_ELSEVIER)) {
			return new SciVerseApiParams();
		}
		return null;
	}
}
