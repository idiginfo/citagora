package org.idiginfo.docsvc.svcapi.harvest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.ApiResult;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.harvest.ApiHarvest;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyHeader;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyResult;
import org.idiginfo.docsvc.svcapi.mendeley.MendeleyService;

/**
 * Class that implements the harvest of Mendeley API content
 * 
 * @author griccardi
 * 
 */
public class MendeleyHarvest implements ApiHarvest {

	@Override
	public String harvestFiles(String keywords, String filePrefix,
			int numPerFile) {
		ServiceFactory serviceFactory = ServiceFactory.getFactory();
		MendeleyService service = new MendeleyService();
		ApiParams params = serviceFactory
				.createApiParams(ServiceFactory.COLLECTION_MENDELEY);
		params.setKeyword(keywords);
		params.setFirstResult(0);
		params.setNumResults(1);
		ApiResult result0 = service.getApiResult("search", keywords, params);
		int totalResults = result0.getTotalResults();
		int totalPages = (int) Math.ceil(totalResults / numPerFile) + 1;
		System.out.println("Total results: " + totalResults + " pages: "
				+ totalPages + " per file: " + numPerFile);
		params.setNumResults(numPerFile);
		for (int pageNum = 0; pageNum < totalPages; pageNum++) {
			params.setFirstResult(pageNum - 1);
			MendeleyResult result = service.getMendeleyResult("search",
					keywords, params);
			List<MendeleyHeader> headers = result.getHeaders();
			for (int i = 0; i < headers.size(); i++) {
				String uuid = headers.get(i).getUuid();
				String uText = service.getMendeleyUUID("details", uuid, params);
				// System.out.println(uText);
				String fileName = filePrefix + uuid + ".json";
				System.out.println("getting file " + fileName);
				try {
					FileWriter out = new FileWriter(fileName);
					out.write(uText);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
			// Mendeley rate limiting: 500 "detail" requests per hour === 25
			// sets of 20 uuid's per hour
			// :. sleep for 2.5 minutes between outer loop iterations
			try {
				Thread.sleep(150000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

}