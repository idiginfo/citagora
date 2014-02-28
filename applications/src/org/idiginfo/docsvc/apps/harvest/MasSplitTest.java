package org.idiginfo.docsvc.apps.harvest;

import java.io.File;

import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiSplit;

/**
 * Class to separate MAS content into individual files
 * 
 * @author griccardi
 * 
 */

public class MasSplitTest {

//	private static final int FIRST_FILE = 1;
//
//	private static final int MAX_FILES = 300;

	private void run(String[] args) {
		int numFiles;
		ApiSplit apiSplit = ServiceFactory.getFactory().createApiSplit(
				ServiceFactory.COLLECTION_MAS);
		File dir = new File(ParameterConstants.Mas.SPLIT_FILE_PREFIX);
		@SuppressWarnings("unused")
		boolean res = false;
		if(! dir.exists()){
			res = dir.mkdir();
		}
		numFiles = apiSplit.splitFiles(ParameterConstants.Mas.FILE_DIR, ParameterConstants.Mas.SPLIT_FILE_PREFIX);
		System.out.println("Number of files processed: " + numFiles);
	}

	public static void main(String[] args) {
		MasSplitTest splitter = new MasSplitTest();
		splitter.run(args);
		return;
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
