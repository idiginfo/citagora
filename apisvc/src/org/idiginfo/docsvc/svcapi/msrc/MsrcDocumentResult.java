package org.idiginfo.docsvc.svcapi.msrc;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.annotations.SerializedName;

/**
 * Class to map results of MSRC API calls
 * 
 * @author griccardi
 * 
 */

public class MsrcDocumentResult extends MsrcResult {

	@SerializedName("document_ids")
	List<Integer> documentIds;

	public List<Integer> getDocumentIds() {
		return documentIds;
	}

	public List<Document> getDocuments() {
		// TODO Auto-generated method stub

		return null; // documents.values();
	}

}
