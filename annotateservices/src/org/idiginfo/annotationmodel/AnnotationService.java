package org.idiginfo.annotationmodel;

/*
 * Copyright (c) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.idiginfo.annotate.services.AnnotateApiParams;
import org.idiginfo.annotate.webapp.ResponseFormatter;
import org.idiginfo.annotate.webapp.ServiceParams;

public interface AnnotationService {

	public String format(String content);

	public Users getUsers(ApiParams params);

	public Document getDocument(ApiParams params);

	public Document getDocument(String code, String date);

	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes);

	public Documents getDocuments(ApiParams params);

	public Documents getDocuments(String user);

	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes);

	// public Document getNotes(String documentCode);

	public Document getAnnotations(ApiParams params);

	public Document getAnnotations(Document document);

	public Document getAnnotations(String code, String date);

}
