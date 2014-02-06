package org.idiginfo.docsvc.controller.harvest;

import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiHarvest;

/**
 * Class to Harvest content via MSRC source
 * 
 * @author griccardi
 * 
 */

public class MsrcHarvestTest {
	// TODO modify this class to gather documents from the MSRC repository
	final static String FILE_DIR = "c:/dev/harvest/msrc/";
	final static String FILE_PREFIX = FILE_DIR + "doc_";
	private static final int START_INDEX = 0;

	private void run(String[] args) {
		String test;
		String filePrefix = FILE_PREFIX;
		if (args != null && args.length > 0) {
			filePrefix = args[0];
		}
		ApiHarvest apiHarvest = ServiceFactory.getFactory().createApiHarvest(
				ServiceFactory.COLLECTION_MSRC);
		test = apiHarvest.harvestFiles(null, filePrefix, 20);
	}

	public static void main(String[] args) {
		MsrcHarvestTest harvester = new MsrcHarvestTest();
		harvester.run(args);
		return;
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
