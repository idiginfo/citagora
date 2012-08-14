package org.idiginfo.docservices.rdf;

import org.idiginfo.docservices.model.Document;
import org.idiginfo.docservices.rdf.vocabulary.BIBO;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Resource;

public class MapAnnotation {

	Model addDocument(Model model, Document document) {
		String doi = document.getDoi();
		Resource docResource;
		if (doi != null) {
			docResource = model.createResource(doi);
		} else {
			docResource = model.createResource(document.getId());
		}
		model.add(docResource, BIBO.authorList, document.getAuthors());
		model.add(docResource, BIBO.doi, doi);
		return model;
	}
}
