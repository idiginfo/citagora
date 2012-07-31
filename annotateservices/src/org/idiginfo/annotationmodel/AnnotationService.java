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

import org.idiginfo.annotate.services.AnnotateApiParams;

public interface AnnotationService {

	public String format(String content);

	public Users getUsers(AnnotateApiParams params);

	public Document getDocument(AnnotateApiParams params);

	public Document getDocument(String code, String date);

	public Document getDocument(String code, String date, boolean withMeta,
			boolean withNotes);

	public Documents getDocuments(AnnotateApiParams params);

	public Documents getDocuments(String user);

	public Documents getDocuments(String user, boolean withMeta,
			boolean withNotes);

	// public Document getNotes(String documentCode);

	public Document getAnnotations(AnnotateApiParams params);

	public Document getAnnotations(Document document);

	public Document getAnnotations(String code, String date);

}
