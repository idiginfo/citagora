package org.idiginfo.docsvc.svcapi.harvest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.idiginfo.docsvc.model.apisvc.ServiceFactory;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.model.harvest.ApiLoad;
import org.idiginfo.docsvc.svcapi.LoadDocuments;
import org.idiginfo.docsvc.svcapi.springer.SpringerRecord;

import com.google.gson.Gson;

/**
 * Class to load from SpringerDocument json files
 * 
 * @author griccardi
 * 
 */
public class SpringerLoad implements ApiLoad {

	Gson gson = ServiceFactory.getFactory()
			.createService(ServiceFactory.COLLECTION_SPRINGER).getGson();
	LoadDocuments loader = new LoadDocuments();

	@Override
	public int loadFiles(Container containerFields, File baseDir) {
		System.out.println("Loading directory " + baseDir.getPath());
		int numLoaded = 0;
		File[] files = baseDir.listFiles();
		// System.out.println("number of files: " + files.length);
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				numLoaded += loadFiles(containerFields, file);
			} else {
				numLoaded += loadFile(containerFields, file);
			}
		}
		return numLoaded;
	}

	@Override
	public int loadFile(Container containerFields, File file) {
		if (!file.getName().endsWith(".json")) {
			System.out.println("skipping file: " + file.getName());
			return 0;
		}
		System.out.print("loading file: " + file.getName());
		try {
			FileReader in = new FileReader(file);
			SpringerRecord document = gson.fromJson(in, SpringerRecord.class);
			containerFields.setGenerated(new Date(file.lastModified()));
			loader.load(containerFields, document);
			return 1;
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
}