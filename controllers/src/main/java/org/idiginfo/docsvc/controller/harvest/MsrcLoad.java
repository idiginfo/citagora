package org.idiginfo.docsvc.controller.harvest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.idiginfo.docsvc.harvest.load.LoadDocuments;
import org.idiginfo.docsvc.model.ServiceFactory;
import org.idiginfo.docsvc.model.apisvc.Annotation;
import org.idiginfo.docsvc.model.citagora.CitagoraAgent;
import org.idiginfo.docsvc.model.citagora.CitagoraFactory;
import org.idiginfo.docsvc.model.citagora.Container;
import org.idiginfo.docsvc.svcapi.annotate.AnnotateDocumentNotes;
import org.idiginfo.docsvc.svcapi.annotate.svc.AnnotateService;
import org.idiginfo.docsvc.svcapi.msrc.MsrcAnnotationRef;
import org.idiginfo.docsvc.svcapi.msrc.MsrcRecord;
import org.idiginfo.docsvc.svcapi.msrc.MsrcResult;
import org.idiginfo.docsvc.svcapi.msrc.MsrcService;

import com.google.gson.Gson;

/**
 * Class to load from MsrcDocument json files
 * 
 * @author griccardi
 * 
 */
public class MsrcLoad {

    public MsrcLoad() {
	CitagoraFactory.setPersistence("local");
	loader = new LoadDocuments();
    }

    Gson gson = MsrcService.getGson();
    LoadDocuments loader = new LoadDocuments();
    CitagoraFactory factory = loader.getFactory();
    AnnotateService annotateService = new AnnotateService();

    public static final String BASE_DIR = "c:/dev/harvest/msrc/";

    public static void main(String[] args) {
	MsrcLoad loader = new MsrcLoad();
	loader.run(args);
	return;
    }

    private void run(String[] args) {
	int numFiles;
	File baseDirectory = new File(BASE_DIR);
	Container containerFields = loader.getFactory().createContainer();
	CitagoraAgent msrcAgent = loader.getFactory().getServiceAgent("msrc");
	containerFields.setGenerator(msrcAgent);
	containerFields.setRights("copyright 2013 idiginfo.com");
	containerFields.setSource(ServiceFactory.COLLECTION_MSRC);
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
	boolean localTransaction = false;
	System.out.print("loading file: " + file.getName());
	try {
	    FileReader in = new FileReader(file);
	    MsrcResult result = gson.fromJson(in, MsrcResult.class);
	    if (result == null) {
		System.out.println(" no result");
		return 0;
	    }
	    MsrcRecord document = result.getRecord();
	    containerFields.setGenerated(new Date(file.lastModified()));
	    if (!factory.isTransactionActive()) {
		factory.openTransaction();
		localTransaction = true;
	    }

	    Container container = loader.load(containerFields, document);
	    // get annotations
	    List<MsrcAnnotationRef> annotationNotes = document
		    .getAnnotationRefs();
	    if (annotationNotes != null) {
		for (MsrcAnnotationRef annotationRef : annotationNotes) {
		    AnnotateDocumentNotes notes = annotateService
			    .getAnnotations(annotationRef.c, annotationRef.d);
		    if (notes == null || notes.getNotes() == null)
			continue;
		    for (Annotation annotation : notes.getNotes()) {
			loader.getDocumentMapper().map(container, annotation);
		    }
		}
	    }
	    if (localTransaction) {
		factory.commitTransaction();
	    }
	    return 1;
	} catch (IOException e) {
	    e.printStackTrace();
	    if (localTransaction) {
		factory.rollbackTransaction();
	    }
	    return 0;
	}
    }
}