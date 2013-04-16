package org.idiginfo.docsvc.controller.harvest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.svcapi.mas.model.MasPublication;
import org.idiginfo.docsvc.svcapi.mas.svc.MasService;

import com.google.gson.Gson;

/**
 * Class to load from MasDocument json files
 * 
 * @author griccardi
 * 
 */
public class MasLoad {

    Gson gson = MasService.getGson();
    LoadDocuments loader = new LoadDocuments();

    public static final String BASE_DIR = "c:/dev/split/mas/";

    public static void main(String[] args) {
	MasLoad masLoader = new MasLoad();
	masLoader.run(args);
	return;
    }

    private void run(String[] args) {
	int numFiles;
	File baseDirectory = new File(BASE_DIR);
	Container containerFields = loader.getFactory().createContainer();
	CitagoraAgent agent = loader.getFactory().getServiceAgent("mas");
	containerFields.setGenerator(agent);
	containerFields.setRights("copyright 2013 idiginfo.com");
	containerFields.setSource("mas api");
	containerFields.setWasAttributedTo("riccardi");
	numFiles = loadFiles(containerFields, baseDirectory);
	System.out.println("Number of files processed: " + numFiles);
    }

    private int loadFiles(Container containerFields, File baseDir) {
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

    private int loadFile(Container containerFields, File file) {
	System.out.print("loading file: " + file.getName());
	try {
	    FileReader in = new FileReader(file);
	    MasPublication document = gson.fromJson(in, MasPublication.class);
	    if (document == null) {
		System.out.println(" no document");
		return 0;
	    }
	    containerFields.setGenerated(new Date(file.lastModified()));
	    loader.load(containerFields, document);
	    return 1;
	} catch (IOException e) {
	    e.printStackTrace();
	    return 0;
	}
    }
}