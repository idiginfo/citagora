package org.idiginfo.docsvc.svcapi.medline;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;

/**
 * Class to implement Document for PubMed MEDLINE
 * 
 * @author griccardi
 * 
 */

public class MedlineDocumentSet extends ArrayList<Document> {

	/**
     * 
     */
	private static final long serialVersionUID = -1877141984638992405L;

	MedlineDocumentSet(BufferedReader in) {
		init(in);
	}

	void init(BufferedReader in) {
		MedlineArticle article = MedlineArticle.getNextArticle(in);
		while (article != null) {
			add(article);
			article = MedlineArticle.getNextArticle(in);
		}

	}

}
