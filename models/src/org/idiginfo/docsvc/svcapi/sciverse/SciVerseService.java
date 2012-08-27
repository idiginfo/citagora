package org.idiginfo.docsvc.svcapi.sciverse;

import org.idiginfo.docsvc.model.model.ApiParams;
import org.idiginfo.docsvc.model.model.DocService;
import org.idiginfo.docsvc.model.model.Document;
import org.idiginfo.docsvc.model.model.Documents;
import org.idiginfo.docsvc.model.model.Users;

public class SciVerseService implements DocService {

	@Override
	public String format(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users getUsers(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(String code, String date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(ApiParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes) {
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
	public Document getAnnotations(String code, String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
