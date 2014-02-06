package org.idiginfo.docsvc.svcapi.harvest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.idiginfo.docsvc.model.harvest.ApiHarvest;
import org.idiginfo.docsvc.svcapi.msrc.MsrcService;

/**
 * Class to Harvest content via MSRC source
 * 
 * @author griccardi
 * 
 */

public class MsrcHarvest implements ApiHarvest {

	@Override
	public String harvestFiles(String keywords, String filePrefix,
			int numPerFile) {
		MsrcService service = new MsrcService();
		List<Integer> documentIds = service.getMsrcDocumentIds();
		int totalResults = documentIds.size();
		System.out.println("Total results: " + totalResults);
		for (int i = 0; i < totalResults; i++) {
			int documentId = documentIds.get(i);
			String fileName = filePrefix + String.format("%06d", documentId)
					+ ".json";
			File outFile = new File(fileName);
			if (outFile.exists()) {
				// System.out.println("Index: " + i + " id: " + documentId
				// + " already present");
				continue;
			}
			System.out.print("getting index " + i + " document id "
					+ documentId);

			String contents = service.getJsonDocument(documentId);
			if (contents == null || contents.length() < 10) {
				System.out.println(" no document returned");
				continue;
			}
			try {
				FileWriter out = new FileWriter(outFile);
				out.write(contents);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
			System.out.println("");
		}

		return null;
	}

}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
