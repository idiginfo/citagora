package org.idiginfo.docsvc.controller.harvest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.harvest.ApiLoad;

import com.google.gson.Gson;

/**
 * Class to load from SpringerDocument json files
 * 
 * @author griccardi
 * 
 */
public class SpringerLoadTest {

	LoadDocuments loader = new LoadDocuments();

	public static final String BASE_DIR = "c:/dev/split/springer/";

	public static void main(String[] args) {
		SpringerLoadTest loader = new SpringerLoadTest();
		loader.run(args);
		return;
	}

	private void run(String[] args) {
		int numFiles;
		ApiLoad apiLoad = ServiceFactory.getFactory().createApiLoad(
				ServiceFactory.COLLECTION_SPRINGER);

		File baseDirectory = new File(BASE_DIR);
		Container containerFields = loader.getFactory().createContainer();
		CitagoraAgent agent = loader.getFactory().getServiceAgent("springer");
		containerFields.setGenerator(agent);
		containerFields.setRights("copyright 2012 idiginfo.com");
		containerFields.setSource(ServiceFactory.COLLECTION_SPRINGER);
		containerFields.setWasAttributedTo("riccardi");
		numFiles = apiLoad.loadFiles(containerFields, baseDirectory);
		System.out.println("Number of documents processed: " + numFiles);
	}
}