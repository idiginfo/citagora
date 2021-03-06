package org.idiginfo.docsvc.svcapi.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiHarvest;
import org.idiginfo.docsvc.svcapi.mas.model.MasPublication;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponse;
import org.idiginfo.docsvc.svcapi.mas.model.MasResponseObject;
import org.idiginfo.docsvc.svcapi.mas.model.MasResultObject;
import org.idiginfo.docsvc.svcapi.mas.svc.MasApiParams;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;

import com.google.api.client.http.HttpTransport;

/**
 * Class to Harvest content from MAS source
 * 
 * @author griccardi
 * 
 */
public class MasHarvest implements ApiHarvest {

	public String harvestFiles(String keywords, String filePrefix,
			int numPerFile) {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		MasService service = new MasService();
		MasApiParams params = new MasApiParams();
		params.setKeyword(keywords);
		params.setFirstResult(1);
		params.setNumResults(1);
		MasResponse response = service.getResponse(params);
		MasResponseObject responseObj = response.getResultObject();
		MasResultObject<MasPublication> resultObject = responseObj
				.getPublication();
		int totalResults = resultObject.getTotalItem();
		int totalPages = (int) Math.ceil(totalResults / numPerFile) + 1;
		System.out.println("Total results: " + totalResults + " pages: "
				+ totalPages + " per file: " + numPerFile);
		for (int pageNum = 0; pageNum < totalPages; pageNum++) {
			params.setFirstResult(pageNum * numPerFile);
			params.setNumResults(numPerFile);
			String fileName = filePrefix + String.format("%05d", pageNum)
					+ ".json";
			System.out.println("getting file " + fileName);
			String contents = service.getMasContents("search", params);
			try {
				FileWriter out = new FileWriter(fileName);
				out.write(contents);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
		return null;
	}
}

// http://annotate.msrc.fsu.edu/php/listUsers.php?api-auth=yKOfIUFmwDxk21FWkn2X0Ets9fY%3D&api-requesttime=1343244291737&api-user=casey.mclaughlin@cci.fsu.edu&api_key=giqfrstIk9b6CddDL3ogGTUac6Lr3II9
// http://annotate.msrc.fsu.edu/php/listUsers.php?api-user=casey.mclaughlin%40cci.fsu.edu&api-requesttime=1343244209&api-auth=43F6TiOpvCVhDs4CnNRZHvvPMa0%3D&api-annotateuser=casey.mclaughlin%40cci.fsu.edu
