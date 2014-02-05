package org.idiginfo.docsvc.svcapi.scopus;

import java.util.List;

import org.idiginfo.docsvc.model.apisvc.ApiParams;
import org.idiginfo.docsvc.model.apisvc.DocService;
import org.idiginfo.docsvc.model.apisvc.Document;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

/**
 * Class to implement DocService object for Scopus
 * 
 * @author griccardi
 * 
 */

public class ScopusService implements DocService {

	@Override
	public String format(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<? extends Document> getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getAnnotations(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getAnnotations(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(List<String> refs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gson getGson() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsonElement matchService(JsonArray refsArray) {
		// TODO Auto-generated method stub
		return null;
	}

}
