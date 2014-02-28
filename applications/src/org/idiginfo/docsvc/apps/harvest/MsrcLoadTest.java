package org.idiginfo.docsvc.apps.harvest;

import java.io.File;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.harvest.ApiLoad;

/**
 * Class to load from MsrcDocument json files
 * 
 * @author griccardi
 * 
 */
public class MsrcLoadTest {

	LoadDocuments loader = new LoadDocuments();


	public static void main(String[] args) {
		MsrcLoadTest loadTest = new MsrcLoadTest();
		loadTest.run(args);
		return;
	}

	private void run(String[] args) {
		int numFiles;
		ApiLoad apiLoad = ServiceFactory.getFactory().createApiLoad(
				ServiceFactory.COLLECTION_MSRC);

		File baseDirectory = new File(ParameterConstants.Msrc.BASE_DIR);
		Container containerFields = loader.getFactory().createContainer();
		CitagoraAgent msrcAgent = loader.getFactory().getServiceAgent(
				ServiceFactory.COLLECTION_MSRC);
		containerFields.setGenerator(msrcAgent);
		containerFields.setRights("copyright 2013 idiginfo.com");
		containerFields.setSource(ServiceFactory.COLLECTION_MSRC);
		containerFields.setWasAttributedTo("riccardi");
		numFiles = apiLoad.loadFiles(containerFields, baseDirectory);
		System.out.println("Number of files processed: " + numFiles);
	}

}