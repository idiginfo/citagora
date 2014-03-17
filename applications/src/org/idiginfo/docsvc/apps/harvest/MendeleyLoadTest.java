package org.idiginfo.docsvc.apps.harvest;

import java.io.File;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.harvest.ApiLoad;

import com.google.gson.Gson;

/**
 * Class to load from MendeleyRecord json files
 * 
 * @author griccardi
 * 
 */
public class MendeleyLoadTest {

	DocService service = ServiceFactory.getFactory().createService(
			ServiceFactory.COLLECTION_MENDELEY);
	Gson gson = service.getGson();
	LoadDocuments loader = new LoadDocuments();



	public static void main(String[] args) {
		MendeleyLoadTest MendeleyLoader = new MendeleyLoadTest();
		MendeleyLoader.run(args);
		return;
	}

	private void run(String[] args) {
		int numFiles;
		ApiLoad apiLoad = ServiceFactory.getFactory().createApiLoad(
				ServiceFactory.COLLECTION_MENDELEY);
		File baseDirectory = new File(ParameterConstants.Mendeley.BASE_DIR);
		Container containerFields = loader.getFactory().createContainer();
		CitagoraAgent agent = loader.getFactory().getServiceAgent(
				ServiceFactory.COLLECTION_MENDELEY);
		containerFields.setGenerator(agent);
		containerFields.setRights("copyright 2013 idiginfo.com");
		containerFields.setSource(ServiceFactory.COLLECTION_MENDELEY);
		containerFields.setWasAttributedTo("sflager");
		numFiles = apiLoad.loadFiles(containerFields, baseDirectory);
		System.out.println("Number of files processed: " + numFiles);
	}

}