package org.idiginfo.docsvc.model.apisvc;

import org.idiginfo.docsvc.model.harvest.ApiHarvest;
import org.idiginfo.docsvc.model.harvest.ApiLoad;
import org.idiginfo.docsvc.model.harvest.ApiSplit;

public abstract class ServiceFactory {

	// Defined collections (also used for value of getSource())
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

	static ServiceFactory serviceFactory;

	static {
		try {
			Class<?> factoryClass = Class
					.forName("org.idiginfo.docsvc.svcapi.SvcApiServiceFactory");
			serviceFactory = (ServiceFactory) factoryClass.getConstructor()
					.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServiceFactory getFactory() {
		return serviceFactory;
	}

	public abstract DocService getSharedService(String collection);

	public abstract DocService createService(String collection);

	public abstract ApiParams createApiParams(String collection);

	public abstract ApiHarvest createApiHarvest(String collection);

	public abstract ApiSplit createApiSplit(String collection);

	public abstract ApiLoad createApiLoad(String collection);

}
