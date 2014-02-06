package org.idiginfo.docsvc.controller.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiResult;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Header;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiHarvest;

/**
 * Class that implements the harvest of Mendeley API content
 * 
 * @author griccardi
 * 
 */
public class MendeleyHarvestTest {

	final static String FILE_DIR = "c:/dev/split/mendeleyTitle/";
	final static String FILE_PREFIX = FILE_DIR + "suicide_";
	private final static int MAX_PER_PAGE = 20;
	private static final int FIRST_PAGE = 1;

	@SuppressWarnings("unused")
	private static void run(String[] args) {
		String test;
		String searchText = "suicide";
		String filePrefix = FILE_PREFIX;
		int numPerFile = MAX_PER_PAGE;
		if (args != null && args.length > 0) {
			searchText = args[0];
		}
		if (args != null && args.length > 1) {
			filePrefix = args[1];
		}
		if (args != null && args.length > 2) {
			numPerFile = Integer.parseInt(args[2]);
		}
		ApiHarvest apiHarvest = ServiceFactory.getFactory().createApiHarvest(
				ServiceFactory.COLLECTION_MENDELEY);
		test = apiHarvest.harvestFiles(searchText, filePrefix, numPerFile);
	}

	public static void main(String[] args) {
		run(args);
		return;
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
